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

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 合并若干个输入流为一个输入流，按照流的添加次序，依序读取子流的内容，凑成一个完整的流
 * 
 * @author konlg
 */
class CombinatedInputStream extends InputStream {

    private final Queue<InputStream> inputs;

    private InputStream curStream;

    public CombinatedInputStream(InputStream... streams) {
        super();
        inputs = new LinkedList<InputStream>();
        for (InputStream stream : streams) {
            inputs.add(stream);
        }
    }

    /**
     * 添加一个输入流到联合流中
     * 
     * @param stream 输入流
     */
    public void append(InputStream stream) {
        inputs.add(stream);
    }

    private InputStream current() throws InterruptedException {
        if (curStream == null) {
            curStream = inputs.poll();
        }
        return curStream;
    }

    @Override
    public int read() throws IOException {
        int data = -1;
        try {
            InputStream stream;
            while ((stream = current()) != null && data == -1) {
                data = stream.read();
                if (data == -1) {
                    curStream.close();
                    curStream = null;
                }
            }
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
        return data;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int data = -1;
        try {
            InputStream stream;
            while ((stream = current()) != null && data == -1) {
                data = stream.read(b);
                if (data == -1) {
                    curStream.close();
                    curStream = null;
                }
            }
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
        return data;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int data = -1;
        try {
            InputStream stream;
            while ((stream = current()) != null && data == -1) {
                data = stream.read(b, off, len);
                if (data == -1) {
                    curStream.close();
                    curStream = null;
                }
            }
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
        return data;
    }

    @Override
    public int available() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        try {
            InputStream stream;
            while ((stream = current()) != null) {
                stream.close();
            }
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }

    @Override
    public synchronized void mark(int readlimit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public long skip(long n) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

}
