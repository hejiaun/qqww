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

/**
 * 背靠背的流。 从原始流中读出指定长度的数据内容
 * 
 * @author konlg
 */
public class BackToBackInputStream extends InputStream {

    private final InputStream origin;

    private final static int CONST_LEN = 4096;

    private final long len;

    private long readed;

    /**
     * 构造一个背靠背流对象
     * 
     * @param orgin 原始流
     * @param len 背靠背流的目标长度
     */
    public BackToBackInputStream(InputStream orgin, long len) {
        super();
        this.origin = orgin;
        this.len = len;
        readed = 0;
    }

    @Override
    public int read() throws IOException {
        if (readed >= len) {
            return -1;
        }
        int read = origin.read();
        if (read == -1) {
            readed = len;
        } else {
            readed++;
        }
        return read;
    }

    @Override
    public int read(byte[] b) throws IOException {
        if (readed >= len) {
            return -1;
        }
        int lenrest = b.length;
        int rest = Long.valueOf(len - readed).intValue();
        if (lenrest > rest) {
            lenrest = rest;
        }
        int read = origin.read(b, 0, lenrest);
        if (read == -1) {
            readed = len;
        } else {
            readed += read;
        }
        return read;
    }

    @Override
    public int read(byte[] b, int off, int len1) throws IOException {
        if (readed >= len) {
            return -1;
        }
        int lenrest = len1;
        int rest = Long.valueOf(len - readed).intValue();
        if (lenrest > rest) {
            lenrest = rest;
        }
        int read = origin.read(b, off, lenrest);
        if (read == -1) {
            readed = len;
        } else {
            readed += read;
        }
        return read;
    }

    @Override
    public int available() throws IOException {
        return Long.valueOf(len - readed).intValue();
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    /**
     * 关闭本流。实际上将消费掉原始流中剩余的内容，而不关闭原始流。
     */
    @Override
    public void close() throws IOException {
        if (readed >= len) {
            return;
        }
        skip(len - readed);
    }

    @Override
    public long skip(long n) throws IOException {
        if (readed >= len) {
            return -1;
        }
        int lenrest = Long.valueOf(n).intValue();
        int rest = Long.valueOf(len - readed).intValue();

        if (lenrest > rest) {
            lenrest = rest;
        }
        int actual = lenrest;

        byte[] buf = new byte[CONST_LEN];
        int len = -1;
        while ((len = origin.read(buf)) != -1 && lenrest > 0) {
            lenrest -= len;
        }

        return actual;
    }
}
