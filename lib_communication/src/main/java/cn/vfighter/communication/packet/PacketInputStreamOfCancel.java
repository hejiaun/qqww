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
 * 二进制文件报文内容流
 * 
 * @author konlg
 */
class PacketInputStreamOfCancel extends InputStream {

    private InputStream input;

    PacketInputStreamOfCancel() throws IOException {
        input = new ByteArrayInputStream(write());
    }

    /**
     * 生产内容报文头 <br/>
     * 
     * @return
     * @throws IOException
     */
    private byte[] write() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(out);
        try {
            output.writeByte(PacketType.STRING.ordinal());
            output.write(PacketConstants.CTX);
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
