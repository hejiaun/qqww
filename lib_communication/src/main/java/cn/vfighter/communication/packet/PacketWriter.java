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
 * @date    2018年10月26日
 */

package cn.vfighter.communication.packet;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 写包器，非线程安全
 * 
 * @author Konlg
 */
public class PacketWriter {
    private final OutputStream output;
    private int writing = 0;

    /**
     * 构造一个包写入
     * 
     * @param output 输出流
     */
    public PacketWriter(OutputStream output) {
        super();
        this.output = output;
    }

    /**
     * 写入数据
     * 
     * @param data 待写入的数据
     * @param type 包类型
     * @throws IOException
     */
    public void write(byte[] data, PacketType type) throws IOException {
        write(data.length, type);
        append(data);
    }

    /**
     * 要写入的数据的长度，紧接着调用 {@link #append(byte[])}追加数据
     * 
     * @param len 长度
     * @param type 包类型
     * @throws IOException
     */
    public void write(int len, PacketType type) throws IOException {
        output.write(intToByte(len));
        byte[] abyte = new byte[] { (byte) type.ordinal() };
        output.write(abyte);
        writing = len;
    }

    /**
     * 继续写入数据，直到写入 {@link #write(int)}长度的数据为止
     * 
     * @param data
     * @throws IOException
     */
    public void append(byte[] data) throws IOException {
        if (writing >= data.length) {
            writing -= data.length;
            output.write(data);
        } else {
            throw new IOException("out of len");
        }
    }

    /**
     * 继续写入数据
     * 
     * @param data 待写入的数据
     * @param offset 源数组的起始位置
     * @param length 写入的长度
     * @throws IOException
     */
    public void append(byte[] data, int offset, int length) throws IOException {
        if (writing >= length) {
            writing -= length;
            output.write(data, offset, length);
        } else {
            throw new IOException("out of len");
        }
    }

    public void flush() throws IOException {
        output.flush();
    }

    public static byte[] intToByte(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n >> 24);
        b[1] = (byte) (n >> 16);
        b[2] = (byte) (n >> 8);
        b[3] = (byte) (n);
        return b;
    }
}
