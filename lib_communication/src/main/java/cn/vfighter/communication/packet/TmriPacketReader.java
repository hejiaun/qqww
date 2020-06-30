/* Copyright (c) vfighter.cn, All Rights Reserved
 *             ____     __    __
 *   _  ______/ __/____/ / __/ /_________
 *  | |/ /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | / / / / / /__  / // // /_/ ___/ /
 *  |__/_/ /_/  __/ /_//_/ \__/\___/_/
 *             \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.communication.packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.entity.mime.MIME;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.utils.GsonFactory;

/**
 * 解析PACKET 私有协议工具。 内容包部分。 本协议支持两种内容格式（一般而言，先发送一个JSON文本，后续可跟随0或多个二进制内容)：
 * 内容包的主结构为：
 * 
 * <pre>
 * byte[0] : 包格式，参考 {@link com.xingfu.app.communication.packet.PacketType}
 * byte[1] ~ byte[x] : 内容实际长度的 long，比如数据内容长度是 1024
 * byte[x+1] ~ byte[y] : 内容的校验码，值为 long 型， 如上。
 * byte[y+1] : 内容起始标识  {@link com.xingfu.app.communication.packet.PacketConstants#STX}
 * byte[y+2] ~ byte[z] : 实际数据内容(分为 JSON 文本或图像文件内容）
 * byte[z+1] : 内容结束标识 {@link com.xingfu.app.communication.packet.PacketConstants#ETX}
 * </pre>
 * 
 * @author konlg
 */
public class TmriPacketReader {

    private InputStream input;
    private DataInputStream dis;
    private volatile boolean cancel;
    private Thread listenerThread;

    /**
     * 构造一个包解析器
     * 
     * @param input
     */
    public TmriPacketReader(InputStream input) {
        this.input = input;
    }

    /**
     * 读取包头
     * 
     * @return
     * @throws IOException
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     */
    public PacketHeader readHeader() throws IOException, IllegalArgumentException {
        // byte data = 0;
        int read = -1;
        int pos = 0;
        byte[] data = new byte[8];
        while (pos < data.length && (read = input.read(data, pos, data.length - pos)) != -1) {
            pos += read;
        }
        dis = new DataInputStream(input);
        return new PacketHeader(data);
    }

    /**
     * 读取一个内容为JSON的字符串
     * 
     * @return JSON字符串
     * @throws IOException
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     */
    public String readAsJson() throws IOException, IllegalArgumentException {
        if (PacketType.JSON.ordinal() != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet type of json");
        }

        String lentxt = dis.readUTF();
        String checksumtxt = dis.readUTF();

        long len = Long.valueOf(lentxt);
        long checksum = Long.valueOf(checksumtxt);

        if (PacketConstants.STX != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet format, expect for start flag");
        }

        int pos = 0;
        int read = -1;
        byte[] buf = new byte[Long.valueOf(len).intValue()];
        while (pos < len
                && ((read = dis.read(buf, pos, Long.valueOf(len - pos).intValue())) != -1)) {
            pos += read;
        }
        if (checksum != ChecksumHelper.calculateChecksum(buf)) {
            throw new IllegalArgumentException("illegal data content");
        }
        if (PacketConstants.ETX != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet format, expect for end flag");
        }
        String str = new String(buf, MIME.UTF8_CHARSET);
        System.out.println(str);
        return str;
    }

    /**
     * 读取一个JSON对象
     * 
     * @param token
     * @return
     * @throws IOException
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     * @throws JsonSyntaxException
     */
    public <T> T readAsJson(TypeToken<T> token)
            throws JsonSyntaxException, IllegalArgumentException, IOException {
        return GsonFactory.SingleTon.create().fromJson(readAsJson(), token.getType());
    }

    /**
     * 读取二进制数据
     * 
     * @return 数据流
     * @throws IOException
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     */
    public FileTypeBinary readAsStream() throws IOException, IllegalArgumentException {
        if (PacketType.BINARY.ordinal() != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet type of binary");
        }
        String lentxt = dis.readUTF();
        String checksumtxt = dis.readUTF();

        long len = Long.valueOf(lentxt);
        long checksum = Long.valueOf(checksumtxt);

        if (PacketConstants.STX != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet format, expect for start flag");
        }

        String filename = dis.readUTF();
        System.out.println("filename = " + filename);
        byte filetype = dis.readByte();

        FileTypeBinary output = new FileTypeBinary(PacketUploadFileType.values()[filetype],
                new BackToBackInputStream(dis, len), len);
        return output;
    }

    public boolean isCancel() throws IOException {
        return cancel;
    }

    public void close() {
        if (listenerThread == null || listenerThread.isInterrupted()) {
            return;
        }
        listenerThread.interrupt();
    }

    public void startListenerCancel() {
        if (listenerThread != null) {
            return;
        }
        listenerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("start listener");
                try {
                    while (dis.available() <= 0) {
                        Thread.sleep(300);
                    }
                    if (PacketType.STRING.ordinal() != dis.readByte()) {
                        throw new IllegalArgumentException("illegal packet type of cancel");
                    }
                    int pos = 0;
                    int read = -1;
                    byte[] buf = new byte[] { 0xF, 0xF, 0xF, 0xF };
                    while (pos < buf.length && ((read = dis.read(buf, pos,
                            Long.valueOf(buf.length - pos).intValue())) != -1)) {
                        pos += read;
                    }
                    for (pos = 0; pos < buf.length; pos++) {
                        if (PacketConstants.CTX[pos] != buf[pos]) {
                            throw new IllegalArgumentException("illegal packet type of cancel");
                        }
                    }
                    cancel = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("stop listener");
            }
        });
        listenerThread.start();
    }
}
