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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

import cn.vfighter.communication.base64.Base64;
import cn.vfighter.communication.base64.Base64OutputStream;

/**
 * 使用流来上传JSON数据对象的HTTP实体。 本实体具有很小的内存消耗；适合采用BASE64编码来上传大文件的情况
 * 
 * @author konlg
 */
public class JsonStreamerEntity implements HttpEntity {

    private static final UnsupportedOperationException ERR_UNSUPPORTED = new UnsupportedOperationException(
            "Unsupported operation in this implementation.");

    // Size of the byte-array buffer used in I/O streams.
    private static final int BUFFER_SIZE = 4096;

    // Buffer used for reading from input streams.
    private final byte[] buffer = new byte[BUFFER_SIZE];

    // Reusable StringBuilder used by escape() method.
    // Its size is just initial, if more space is needed, the system will
    // automatically enlarge the buffer.
    private static final StringBuilder BUILDER = new StringBuilder(128);

    private static final byte[] JSON_TRUE = "true".getBytes();
    private static final byte[] JSON_FALSE = "false".getBytes();
    private static final byte[] JSON_NULL = "null".getBytes();
    private static final byte[] STREAM_NAME = escape("name");
    private static final byte[] STREAM_TYPE = escape("type");
    private static final byte[] STREAM_CONTENTS = escape("contents");
    private static final byte[] STREAM_ELAPSED = escape("_elapsed");

    private static final Header HEADER_JSON_CONTENT = new BasicHeader(
            HttpServiceAgent.HEADER_CONTENT_TYPE, MimeType.APPLICATION_JSON);

    private static final Header HEADER_GZIP_ENCODING = new BasicHeader(
            HttpServiceAgent.HEADER_CONTENT_ENCODING, HttpServiceAgent.ENCODING_GZIP);

    // JSON data and associated meta-data to be uploaded.
    private final Map<String, Object> jsonParams = new HashMap<String, Object>();

    // Whether to use gzip compression while uploading
    private final Header contentEncoding;

    private final ProgressHandlerInterface progressHandler;

    public JsonStreamerEntity(ProgressHandlerInterface progressHandler,
            boolean useGZipCompression) {
        this.progressHandler = progressHandler;
        this.contentEncoding = useGZipCompression ? HEADER_GZIP_ENCODING : null;
    }

    /**
     * Add content parameter, identified by the given key, to the request.
     *
     * @param key entity's name
     * @param value entity's value (Scalar, FileWrapper, StreamWrapper)
     */
    public void addPart(String key, Object value) {
        jsonParams.put(key, value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public long getContentLength() {
        return -1;
    }

    @Override
    public Header getContentEncoding() {
        return contentEncoding;
    }

    @Override
    public Header getContentType() {
        return HEADER_JSON_CONTENT;
    }

    @Override
    public void consumeContent() throws IOException, UnsupportedOperationException {
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw ERR_UNSUPPORTED;
    }

    @Override
    public void writeTo(final OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalStateException("Output stream cannot be null.");
        }

        // Record the time when uploading started.
        long now = System.currentTimeMillis();

        // Use GZIP compression when sending streams, otherwise just use
        // a buffered output stream to speed things up a bit.
        OutputStream os = null != contentEncoding ? new GZIPOutputStream(out, BUFFER_SIZE) : out;

        // Always send a JSON object.
        os.write('{');

        // Keys used by the HashMaps.
        Set<String> keys = jsonParams.keySet();

        boolean isFileWrapper;

        // Go over all keys and handle each's value.
        for (String key : keys) {
            // Evaluate the value (which cannot be null).
            Object value = jsonParams.get(key);

            // Bail out prematurely if value's null.
            if (value == null) {
                continue;
            }

            // Write the JSON object's key.
            os.write(escape(key));
            os.write(':');

            // Check if this is a FileWrapper.
            isFileWrapper = value instanceof FileWrapper;

            // If a file should be uploaded.
            if (isFileWrapper || value instanceof StreamWrapper) {
                // All uploads are sent as an object containing the file's
                // details.
                os.write('{');

                // Determine how to handle this entry.
                if (isFileWrapper) {
                    writeToFromFile(os, (FileWrapper) value);
                } else {
                    writeToFromStream(os, (StreamWrapper) value);
                }

                // End the file's object and prepare for next one.
                os.write('}');
            } else if (value instanceof JsonValueInterface) {
                os.write(((JsonValueInterface) value).getEscapedJsonValue());
            } else if (value instanceof org.json.JSONObject) {
                os.write(((org.json.JSONObject) value).toString().getBytes());
            } else if (value instanceof org.json.JSONArray) {
                os.write(((org.json.JSONArray) value).toString().getBytes());
            } else if (value instanceof Boolean) {
                os.write((Boolean) value ? JSON_TRUE : JSON_FALSE);
            } else if (value instanceof Long) {
                os.write((((Number) value).longValue() + "").getBytes());
            } else if (value instanceof Double) {
                os.write((((Number) value).doubleValue() + "").getBytes());
            } else if (value instanceof Float) {
                os.write((((Number) value).floatValue() + "").getBytes());
            } else if (value instanceof Integer) {
                os.write((((Number) value).intValue() + "").getBytes());
            } else {
                os.write(escape(value.toString()));
            }

            os.write(',');
        }

        // Include the elapsed time taken to upload everything.
        // This might be useful for somebody, but it serves us well since
        // there will almost always be a ',' as the last sent character.
        os.write(STREAM_ELAPSED);
        os.write(':');
        long elapsedTime = System.currentTimeMillis() - now;
        os.write((elapsedTime + "}").getBytes());

        // Flush the contents up the stream.
        os.flush();
        HttpServiceAgent.silentCloseOutputStream(os);
    }

    private void writeToFromStream(OutputStream os, StreamWrapper entry) throws IOException {

        // Send the meta data.
        writeMetaData(os, entry.name, entry.contentType.getMimeType());

        int bytesRead;

        // Upload the file's contents in Base64.
        Base64OutputStream bos = new Base64OutputStream(os, Base64.NO_CLOSE | Base64.NO_WRAP);

        // Read from input stream until no more data's left to read.
        while ((bytesRead = entry.inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        // Close the Base64 output stream.
        HttpServiceAgent.silentCloseOutputStream(bos);

        // End the meta data.
        endMetaData(os);

        // Close input stream.
        if (entry.autoClose) {
            // Safely close the input stream.
            HttpServiceAgent.silentCloseInputStream(entry.inputStream);
        }
    }

    private void writeToFromFile(OutputStream os, FileWrapper wrapper) throws IOException {

        // Send the meta data.
        writeMetaData(os, wrapper.file.getName(), wrapper.contentType.getMimeType());

        int bytesRead, bytesWritten = 0, totalSize = (int) wrapper.file.length();

        // Open the file for reading.
        FileInputStream in = new FileInputStream(wrapper.file);

        // Upload the file's contents in Base64.
        Base64OutputStream bos = new Base64OutputStream(os, Base64.NO_CLOSE | Base64.NO_WRAP);

        // Read from file until no more data's left to read.
        while ((bytesRead = in.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
            bytesWritten += bytesRead;
            progressHandler.sendProgressMessage(bytesWritten, totalSize);
        }

        // Close the Base64 output stream.
        HttpServiceAgent.silentCloseOutputStream(bos);

        // End the meta data.
        endMetaData(os);

        // Safely close the input stream.
        HttpServiceAgent.silentCloseInputStream(in);
    }

    private void writeMetaData(OutputStream os, String name, String contentType)
            throws IOException {
        // Send the streams's name.
        os.write(STREAM_NAME);
        os.write(':');
        os.write(escape(name));
        os.write(',');

        // Send the streams's content type.
        os.write(STREAM_TYPE);
        os.write(':');
        os.write(escape(contentType));
        os.write(',');

        // Prepare the file content's key.
        os.write(STREAM_CONTENTS);
        os.write(':');
        os.write('"');
    }

    private void endMetaData(OutputStream os) throws IOException {
        os.write('"');
    }

    // Curtosy of Simple-JSON: http://goo.gl/XoW8RF
    // Changed a bit to suit our needs in this class.
    static byte[] escape(String string) {
        // If it's null, just return prematurely.
        if (string == null) {
            return JSON_NULL;
        }

        // Surround with quotations.
        BUILDER.append('"');

        int length = string.length(), pos = -1;
        while (++pos < length) {
            char ch = string.charAt(pos);
            switch (ch) {
            case '"':
                BUILDER.append("\\\"");
                break;
            case '\\':
                BUILDER.append("\\\\");
                break;
            case '\b':
                BUILDER.append("\\b");
                break;
            case '\f':
                BUILDER.append("\\f");
                break;
            case '\n':
                BUILDER.append("\\n");
                break;
            case '\r':
                BUILDER.append("\\r");
                break;
            case '\t':
                BUILDER.append("\\t");
                break;
            default:
                // Reference: http://www.unicode.org/versions/Unicode5.1.0/
                if ((ch >= '\u0000' && ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F')
                        || (ch >= '\u2000' && ch <= '\u20FF')) {
                    String intString = Integer.toHexString(ch);
                    BUILDER.append("\\u");
                    int intLength = 4 - intString.length();
                    for (int zero = 0; zero < intLength; zero++) {
                        BUILDER.append('0');
                    }
                    BUILDER.append(intString.toUpperCase(Locale.US));
                } else {
                    BUILDER.append(ch);
                }
                break;
            }
        }

        // Surround with quotations.
        BUILDER.append('"');

        try {
            return BUILDER.toString().getBytes();
        } finally {
            // Empty the String buffer.
            // This is 20-30% faster than instantiating a new object.
            BUILDER.setLength(0);
        }
    }
}
