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

import com.google.gson.Gson;
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
public class PacketReader {

    private InputStream input;
    protected DataInputStream dis;
    private Gson gson;

    /**
     * 构造一个包解析器
     * 
     * @param input
     * @param gson
     */
    public PacketReader(InputStream input, Gson gson) {
        this.input = input;
        this.gson = gson;
    }

    /**
     * 读取包头
     * 
     * @return 包头
     * @throws IOException 读取错误
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
     * @throws IOException 读取错误
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     */
    public String readAsJson() throws IOException, IllegalArgumentException {
        String str = new String(readData(), MIME.UTF8_CHARSET);
        System.out.println(str);
        return str;
    }

    /**
     * 读取一个JSON对象
     * 
     * @param token 转换类型
     * @param <T> 待转换的类型
     * @return 反序列化后的实例
     * @throws IOException 读取异常
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     * @throws JsonSyntaxException 解析异常
     */
    public <T> T readAsJson(TypeToken<T> token)
            throws JsonSyntaxException, IllegalArgumentException, IOException {
        return gson().fromJson(readAsJson(), token.getType());
    }

    /**
     * 从流中读取本包内容
     * 
     * @return 内容
     * @throws IOException 读取错误
     */
    protected byte[] readData() throws IOException {
        if (PacketType.JSON.ordinal() != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet type of json");
        }

        int len = Long.valueOf(dis.readLong()).intValue();
        long checksum = dis.readLong();
        if (PacketConstants.STX != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet format, expect for start flag");
        }

        int pos = 0;
        int read = -1;
        byte[] buf = new byte[len];
        while (pos < len && ((read = dis.read(buf, pos, len - pos)) != -1)) {
            pos += read;
        }
        if (checksum != ChecksumHelper.calculateChecksum(buf)) {
            throw new IllegalArgumentException("illegal data content");
        }
        return buf;
    }

    private Gson gson() {
        if (gson == null)
            gson = GsonFactory.SingleTon.create();
        return gson;
    }

    /**
     * 读取二进制数据
     * 
     * @return 数据流
     * @throws IOException 读取错误
     * @throws IllegalArgumentException 解析报文头错误，格式不正确
     */
    public FileTypeBinary readAsStream() throws IOException, IllegalArgumentException {
        if (PacketType.BINARY.ordinal() != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet type of binary");
        }
        long len = dis.readLong();
        byte filetype = dis.readByte();
        if (PacketConstants.STX != dis.readByte()) {
            throw new IllegalArgumentException("illegal packet format, expect for start flag");
        }
        return new FileTypeBinary(PacketUploadFileType.values()[filetype],
                new BackToBackInputStream(dis, len), len);
    }
}
