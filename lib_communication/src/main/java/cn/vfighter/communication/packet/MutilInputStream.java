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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 将多个输入流合并为一个输入
 * 
 * @author konlg
 */
public class MutilInputStream extends InputStream {

    private Lock lock;

    private Queue<InputStream> inQueue;

    private Condition condition;

    private InputStream curInputStream;

    private final boolean lazyStream;
    private boolean canceled = false;

    MutilInputStream() {
        inQueue = new LinkedList<InputStream>();
        lock = new ReentrantLock();
        condition = lock.newCondition();
        lazyStream = true;
    }

    MutilInputStream(InputStream... inputStreams) {
        inQueue = new LinkedList<InputStream>();
        lock = new ReentrantLock();
        for (InputStream in : inputStreams) {
            inQueue.add(in);
        }
        condition = lock.newCondition();
        lazyStream = false;
    }

    public void send(InputStream input) {
        if (!lazyStream || canceled) {
            throw new UnsupportedOperationException(
                    "can't add stream to non-lazy or canceled mutil inputstream");
        }
        lock.lock();
        try {
            inQueue.add(input);
            if (input instanceof PacketInputStreamOfCancel) {
                canceled = true;
            }
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private InputStream current() throws InterruptedException {
        if (curInputStream == null) {
            lock.lock();
            try {
                if (lazyStream && !canceled) {
                    while (inQueue.isEmpty()) {
                        condition.await();
                    }
                    curInputStream = inQueue.poll();
                } else {
                    if (inQueue.isEmpty()) {
                        return null;
                    } else {
                        curInputStream = inQueue.poll();
                    }
                }
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
        return curInputStream;
    }

    @Override
    public int read() throws IOException {
        int data = -1;
        try {
            InputStream in = current();
            while (data == -1 && in != null) {
                data = in.read();
                if (data == -1) {
                    in.close();
                    curInputStream = null;
                    in = current();
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
            InputStream in = current();
            while (data == -1 && in != null) {
                data = in.read(b);
                if (data == -1) {
                    in.close();
                    curInputStream = null;
                    in = current();
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
            InputStream in = current();
            while (data == -1 && in != null) {
                data = in.read(b, off, len);
                if (data == -1) {
                    in.close();
                    curInputStream = null;
                    in = current();
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
            InputStream in = current();
            while (in != null) {
                in.close();
                curInputStream = null;
                in = current();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public long skip(long n) throws IOException {
        throw new UnsupportedOperationException();
    }

}
