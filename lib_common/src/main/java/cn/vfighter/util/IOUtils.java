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

package cn.vfighter.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.vfighter.exception.FileException;

/**
 * IO工具类
 * 
 * @author konlg
 */
public class IOUtils {
    /** 文件结束标记 */
    private static final int EOF = -1;
    /** 默认缓冲区大小 */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 关闭<code>Closeable</code>,该方法等效于{@linkplain Closeable#close()}
     * <p>
     * 该方法主要用于finally块中，并且忽略所有的异常
     * </p>
     * Example code:
     * 
     * <pre>
     * Closeable closeable = null;
     * try {
     *     closeable = new FileReader("foo.txt");
     *     // process closeable
     *     closeable.close();
     * } catch (Exception e) {
     *     // error handling
     * } finally {
     *     IOUtils.closeQuietly(closeable);
     * }
     * </pre>
     *
     * @param closeable the object to close, may be null or already closed
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * 流的拷贝，超大流(超过2GB)拷贝返回的结果为-1。如果是超大流拷贝请使用{@linkplain #copyLarge(InputStream, OutputStream)}
     *
     * @param in 输入流
     * @param out 输出流
     * @return 返回流大小，如果拷贝失败或流过大均返回-1
     */
    public static int copy(InputStream in, OutputStream out) {
        long count = copyLarge(in, out);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 流的拷贝，如果拷贝流失败则返回-1.
     *
     * @param in 输入流
     * @param out 输出流
     * @return 返回流大小，如果拷贝失败则返回-1
     */
    public static long copyLarge(InputStream in, OutputStream out) {
        return copyLarge(in, out, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 流的拷贝，如果拷贝流失败则返回-1.
     *
     * @param in 输入流
     * @param out 输出流
     * @param buffer 缓冲区
     * @return 返回流大小，如果拷贝失败则返回-1
     */
    public static long copyLarge(InputStream in, OutputStream out, byte[] buffer) {
        Assert.notNull(in, "InputStream must not be null.");
        Assert.notNull(out, "OutputStream must not be null.");
        Assert.notEmpty(buffer, "The buffer array must not null or empty.");
        long count = 0;
        int n;
        try {
            while (EOF != (n = in.read(buffer))) {
                out.write(buffer, 0, n);
                count += n;
            }
            return count;
        } catch (IOException e) {
            throw new cn.vfighter.exception.IOException(
                    "Copy bytes from a large InputStream to an OutputStream error", e);
        }
    }

    /**
     * 打开件输出流
     *
     * @param file 文件
     * @param append 附加
     * @return {@link FileOutputStream}
     */
    private static FileOutputStream openFileOutputStream(File file, boolean append) {
        Assert.notNull(file, "File must not be null.");
        if (file.exists()) {
            if (file.isDirectory())
                throw new FileException("Destination [" + file + "] exists but is a directory.");
            if (!file.canWrite())
                throw new FileException(
                        String.format("Destination [%s] exists but cannot write.", file));
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory())
                    throw new FileException("Directory [" + parent + "] could not be created.");
            }
        }
        try {
            return new FileOutputStream(file, append);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    /**
     * 打开件输出流
     *
     * @param file 文件
     * @return {@link FileOutputStream}
     */
    public static FileOutputStream openFileOutputStream(File file) {
        return openFileOutputStream(file, false);
    }

    /**
     * 将输入流的数据输出到文件中
     *
     * @param in 输入流,非空
     * @param destFile 目标文件,非空
     */
    public static void copyStream(InputStream in, File destFile) {
        Assert.notNull(in, "The parameter[in] is null.");
        Assert.notNull(destFile, "The parameter[destFile] is null.");
        FileOutputStream fos = null;
        try {
            fos = openFileOutputStream(destFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            IOUtils.copy(in, bos);
            IOUtils.closeQuietly(bos);
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(in);
        }
    }
}
