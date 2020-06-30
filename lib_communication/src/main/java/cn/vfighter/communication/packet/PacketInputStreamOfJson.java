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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * JSON报文数据流
 * 
 * @author konlg
 */
class PacketInputStreamOfJson extends InputStream {

    private ByteArrayInputStream input;

    /**
     * 构造一个JSON报文流
     * 
     * @param data json 数据内容
     * @throws IOException
     */
    PacketInputStreamOfJson(byte[] data) throws IOException {
        input = new ByteArrayInputStream(write(data));
    }

    /**
     * 写入JSON数据 <br/>
     * 1.write packet type 2.write data length 3.write data checksum 4.write STX
     * 5.write data
     * 
     * @return
     * @throws IOException
     */
    private byte[] write(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(out);
        try {
            output.writeByte(PacketType.JSON.ordinal());
            int length = data.length;
            output.writeLong(length);
            long checksum = ChecksumHelper.calculateChecksum(data);
            output.writeLong(checksum);
            output.writeByte(PacketConstants.STX);
            output.write(data, 0, data.length);
            output.flush();
            out.flush();
            return out.toByteArray();
        } finally {
            output.close();
            out.close();
        }
    }

    @Override
    public int read() throws IOException {
        return input.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return input.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return input.read(b, off, len);
    }

    @Override
    public int available() throws IOException {
        return input.available();
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        input.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        return input.markSupported();
    }

    @Override
    public synchronized void reset() throws IOException {
        input.reset();
    }

    @Override
    public long skip(long n) throws IOException {
        return input.skip(n);
    }

}
