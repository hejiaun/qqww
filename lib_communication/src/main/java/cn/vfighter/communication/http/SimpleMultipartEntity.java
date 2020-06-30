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

package cn.vfighter.communication.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.TextUtils;

/**
 * 支持发送一个或多个文件的复合实体
 * 
 * @author konlg
 */
class SimpleMultipartEntity implements HttpEntity {

    private static final String STR_CR_LF = "\r\n";
    private static final byte[] CR_LF = STR_CR_LF.getBytes();
    private static final byte[] TRANSFER_ENCODING_BINARY = ("Content-Transfer-Encoding: binary"
            + STR_CR_LF).getBytes();

    private final static char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();

    private final String boundary;
    private final byte[] boundaryLine;
    private final byte[] boundaryEnd;
    private boolean isRepeatable;

    private final List<FilePart> fileParts = new ArrayList<FilePart>();

    // The buffer we use for building the message excluding files and the last
    // boundary
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final ProgressHandlerInterface progressHandler;

    private int bytesWritten;

    private int totalSize;

    /**
     * 构造一个复合实体
     * 
     * @param progressHandler 进度处理句柄
     */
    public SimpleMultipartEntity(ProgressHandlerInterface progressHandler) {
        final StringBuilder buf = new StringBuilder();
        final Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            buf.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }

        boundary = buf.toString();
        boundaryLine = ("--" + boundary + STR_CR_LF).getBytes();
        boundaryEnd = ("--" + boundary + "--" + STR_CR_LF).getBytes();

        this.progressHandler = progressHandler;
    }

    /**
     * 添加一个字符串部分
     * 
     * @param key 成分的键名
     * @param value 成分的值
     * @param contentType 内容MIMETYPE
     */
    public void addPart(String key, String value, String contentType) {
        try {
            out.write(boundaryLine);
            out.write(createContentDisposition(key));
            out.write(createContentType(contentType));
            out.write(CR_LF);
            out.write(value.getBytes());
            out.write(CR_LF);
        } catch (final IOException e) {
            // Shall not happen on ByteArrayOutputStream
            System.err.println("addPart ByteArrayOutputStream exception");
            e.printStackTrace();
        }
    }

    /**
     * 添加一个字符串成分，可指定字符串的字符集
     * 
     * @param key 成分键名
     * @param value 值
     * @param charset 字符集
     */
    public void addPartWithCharset(String key, String value, String charset) {
        if (charset == null)
            charset = HTTP.UTF_8;
        addPart(key, value, "text/plain; charset=" + charset);
    }

    /**
     * 添加一个字符串成分，使用系统默认字符集
     * 
     * @param key 成分键名
     * @param value 值
     */
    public void addPart(String key, String value) {
        addPartWithCharset(key, value, null);
    }

    /**
     * 添加一个文件成分
     * 
     * @param key 成分键名
     * @param file 值（文件内容）
     */
    public void addPart(String key, File file) {
        addPart(key, file, null);
    }

    /**
     * 添加一个文件成分
     * 
     * @param key 成分键名
     * @param file 值（文件内容）
     * @param type 文件的MIME类型
     */
    public void addPart(String key, File file, String type) {
        fileParts.add(new FilePart(key, file, normalizeContentType(type)));
    }

    /**
     * 添加一个文件成分
     * 
     * @param key 成分键名
     * @param file 值（文件内容）
     * @param type MIME类型
     * @param customFileName 指定文件名
     */
    public void addPart(String key, File file, String type, String customFileName) {
        fileParts.add(new FilePart(key, file, normalizeContentType(type), customFileName));
    }

    /**
     * 添加一个二进制成分
     * 
     * @param key 键名
     * @param streamName 建议保存的文件名
     * @param inputStream 二进制流
     * @param type MIME类型
     * @throws IOException
     */
    public void addPart(String key, String streamName, InputStream inputStream, String type)
            throws IOException {

        out.write(boundaryLine);

        // Headers
        out.write(createContentDisposition(key, streamName));
        out.write(createContentType(type));
        out.write(TRANSFER_ENCODING_BINARY);
        out.write(CR_LF);

        // Stream (file)
        final byte[] tmp = new byte[4096];
        int l;
        while ((l = inputStream.read(tmp)) != -1) {
            out.write(tmp, 0, l);
        }

        out.write(CR_LF);
        out.flush();

        HttpServiceAgent.silentCloseOutputStream(out);
    }

    /**
     * MIME类型
     * 
     * @param type 待正规化的MIME类型
     * @return 默认是 {@link RequestParams#APPLICATION_OCTET_STREAM}
     */
    private String normalizeContentType(String type) {
        return type == null ? MimeType.APPLICATION_OCTET_STREAM : type;
    }

    /**
     * MIME类型二进制表示
     * 
     * @param type MIME类型
     * @return 二进制表示
     */
    private byte[] createContentType(String type) {
        String result = HttpServiceAgent.HEADER_CONTENT_TYPE + ": " + normalizeContentType(type)
                + STR_CR_LF;
        return result.getBytes();
    }

    /**
     * 上传文件建议文件名二进制表示
     * 
     * @param key 键名
     * @return 建议文件名二进制表示
     */
    private byte[] createContentDisposition(String key) {
        return (HttpServiceAgent.HEADER_CONTENT_DISPOSITION + ": form-data; name=\"" + key + "\""
                + STR_CR_LF).getBytes();
    }

    /**
     * 创建一个建议上传文件名的建议
     * 
     * @param key 部件键名
     * @param fileName 建议文件名
     * @return 上传文件名建议内容
     */
    private byte[] createContentDisposition(String key, String fileName) {
        return (HttpServiceAgent.HEADER_CONTENT_DISPOSITION + ": form-data; name=\"" + key + "\""
                + "; filename=\"" + fileName + "\"" + STR_CR_LF).getBytes();
    }

    /**
     * 通知进度处理器当前进度
     * 
     * @param count 完成的字节
     */
    private void updateProgress(int count) {
        bytesWritten += count;
        progressHandler.sendProgressMessage(bytesWritten, totalSize);
    }

    /**
     * 文件部件
     * 
     * @author sam.pan
     */
    private class FilePart {
        public File file;
        public byte[] header;

        public FilePart(String key, File file, String type, String customFileName) {
            header = createHeader(key,
                    TextUtils.isEmpty(customFileName) ? file.getName() : customFileName, type);
            this.file = file;
        }

        public FilePart(String key, File file, String type) {
            header = createHeader(key, file.getName(), type);
            this.file = file;
        }

        private byte[] createHeader(String key, String filename, String type) {
            ByteArrayOutputStream headerStream = new ByteArrayOutputStream();
            try {
                headerStream.write(boundaryLine);

                // Headers
                headerStream.write(createContentDisposition(key, filename));
                headerStream.write(createContentType(type));
                headerStream.write(TRANSFER_ENCODING_BINARY);
                headerStream.write(CR_LF);
            } catch (IOException e) {
                // Can't happen on ByteArrayOutputStream
                System.err.println("createHeader ByteArrayOutputStream exception");
                e.printStackTrace();
            }
            return headerStream.toByteArray();
        }

        public long getTotalLength() {
            long streamLength = file.length() + CR_LF.length;
            return header.length + streamLength;
        }

        public void writeTo(OutputStream out) throws IOException {
            out.write(header);
            updateProgress(header.length);

            FileInputStream inputStream = new FileInputStream(file);
            final byte[] tmp = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(tmp)) != -1) {
                out.write(tmp, 0, bytesRead);
                updateProgress(bytesRead);
            }
            out.write(CR_LF);
            updateProgress(CR_LF.length);
            out.flush();
            HttpServiceAgent.silentCloseInputStream(inputStream);
        }
    }

    // The following methods are from the HttpEntity interface

    @Override
    public long getContentLength() {
        long contentLen = out.size();
        for (FilePart filePart : fileParts) {
            long len = filePart.getTotalLength();
            if (len < 0) {
                return -1; // Should normally not happen
            }
            contentLen += len;
        }
        contentLen += boundaryEnd.length;
        return contentLen;
    }

    @Override
    public Header getContentType() {
        return new BasicHeader(HttpServiceAgent.HEADER_CONTENT_TYPE,
                "multipart/form-data; boundary=" + boundary);
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    public void setIsRepeatable(boolean isRepeatable) {
        this.isRepeatable = isRepeatable;
    }

    @Override
    public boolean isRepeatable() {
        return isRepeatable;
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void writeTo(final OutputStream outstream) throws IOException {
        bytesWritten = 0;
        totalSize = (int) getContentLength();
        out.writeTo(outstream);
        updateProgress(out.size());

        for (FilePart filePart : fileParts) {
            filePart.writeTo(outstream);
        }
        outstream.write(boundaryEnd);
        updateProgress(boundaryEnd.length);
    }

    @Override
    public Header getContentEncoding() {
        return null;
    }

    @Override
    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException(
                    "Streaming entity does not implement #consumeContent()");
        }
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "getContent() is not supported. Use writeTo() instead.");
    }
}