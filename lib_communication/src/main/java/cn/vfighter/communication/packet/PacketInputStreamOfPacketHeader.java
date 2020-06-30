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

import vfighter.android.util.Log;

/**
 * 输出报文头的流
 * 
 * @author konlg
 */
class PacketInputStreamOfPacketHeader extends InputStream {

    private static final String TAG = "PacketInputStreamOfPacketHeader";

    /**
     * 固定的报文头数据
     */
    private static byte[] headerData;
    static {
        ByteArrayOutputStream dataOutput = new ByteArrayOutputStream(8);
        DataOutputStream output = new DataOutputStream(dataOutput);
        try {
            try {
                output.writeByte(PacketConstants.SOH);
                output.writeInt(PacketConstants.MAGICNUMBER);
                output.writeByte(PacketConstants.VER_MAJOR);
                output.writeByte(PacketConstants.VER_MINOR);
                output.writeByte(PacketConstants.ENCODETYPE_NONE);
                output.flush();
                dataOutput.flush();
                headerData = dataOutput.toByteArray();
            } finally {
                dataOutput.close();
                output.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "generate packet header static bytes failure.", e);
        }
    }

    private final ByteArrayInputStream input;

    PacketInputStreamOfPacketHeader() throws IOException {
        input = new ByteArrayInputStream(headerData);
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
