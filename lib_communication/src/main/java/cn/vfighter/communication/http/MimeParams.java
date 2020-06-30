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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * 支持多种Mime格式的参数集合对象
 * 
 * @author konlg
 */
public class MimeParams implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2528136017007085700L;
    private Map<String, String> urlParams;
    private Map<String, StreamWrapper> streamParams;
    private Map<String, FileWrapper> fileParams;
    private Map<String, Object> urlParamsWithObjects;
    private boolean autoCloseInputStreams;

    private Lock lock;
    private Charset charset = MIME.UTF8_CHARSET;
    private HttpMultipartMode mode = HttpMultipartMode.STRICT;
    private ContentType stringContentType = ContentType.TEXT_PLAIN;

    /**
     * Constructs a new empty {@code MimeParams} instance.
     */
    public MimeParams() {
        this((Map<String, String>) null);
    }

    /**
     * Constructs a new MimeParams instance containing the key/value string
     * params from the specified map.
     *
     * @param source the source key/value string map to add.
     */
    public MimeParams(Map<String, String> source) {
        lock = new ReentrantLock();
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Constructs a new MimeParams instance and populate it with a single
     * initial key/value string param.
     *
     * @param key the key name for the intial param.
     * @param value the value string for the initial param.
     */
    public MimeParams(final String key, final String value) {
        this(new HashMap<String, String>() {

            /**
             * serialVersionUID
             */
            private static final long serialVersionUID = 4943704467807653075L;

            {
                put(key, value);
            }
        });
    }

    /**
     * Constructs a new MimeParams instance and populate it with multiple
     * initial key/value string param.
     *
     * @param keysAndValues a sequence of keys and values. Objects are
     *            automatically converted to Strings (including the value
     *            {@code null}).
     * @throws IllegalArgumentException if the number of arguments isn't even.
     */
    public MimeParams(Object... keysAndValues) {
        lock = new ReentrantLock();
        int len = keysAndValues.length;
        if (len % 2 != 0)
            throw new IllegalArgumentException("Supplied arguments must be even");
        for (int i = 0; i < len; i += 2) {
            String key = String.valueOf(keysAndValues[i]);
            Object value = keysAndValues[i + 1];
            if (value instanceof String) {
                String val = String.valueOf(value);
                put(key, val);
            } else if (value instanceof File) {
                File val = File.class.cast(value);
                try {
                    put(key, val);
                } catch (FileNotFoundException e) {
                    throw new IllegalArgumentException("file not found", e);
                }
            } else if (value instanceof InputStream) {
                InputStream val = InputStream.class.cast(value);
                put(key, val);
            }
        }
    }

    /**
     * Adds a key/value string pair to the request.
     *
     * @param key the key name for the new param.
     * @param value the value string for the new param.
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            getUrlParams().put(key, value);
        }
    }

    /**
     * Adds a file to the request.
     *
     * @param key the key name for the new param.
     * @param file the file to add.
     * @throws java.io.FileNotFoundException throws if wrong File argument was
     *             passed
     */
    public void put(String key, File file) throws FileNotFoundException {
        put(key, file, null, null);
    }

    /**
     * Adds a file to the request with custom provided file name
     *
     * @param key the key name for the new param.
     * @param file the file to add.
     * @param customFileName file name to use instead of real file name
     * @throws java.io.FileNotFoundException throws if wrong File argument was
     *             passed
     */
    public void put(String key, String customFileName, File file) throws FileNotFoundException {
        put(key, file, null, customFileName);
    }

    /**
     * Adds a file to the request with custom provided file content-type
     *
     * @param key the key name for the new param.
     * @param file the file to add.
     * @param contentType the content type of the file, eg. application/json
     * @throws java.io.FileNotFoundException throws if wrong File argument was
     *             passed
     */
    public void put(String key, File file, ContentType contentType) throws FileNotFoundException {
        put(key, file, contentType, null);
    }

    /**
     * Adds a file to the request with both custom provided file content-type
     * and file name
     *
     * @param key the key name for the new param.
     * @param file the file to add.
     * @param contentType the content type of the file, eg. application/json
     * @param customFileName file name to use instead of real file name
     * @throws java.io.FileNotFoundException throws if wrong File argument was
     *             passed
     */
    public void put(String key, File file, ContentType contentType, String customFileName)
            throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        }
        if (key != null) {
            getFileParams().put(key, new FileWrapper(file, contentType, customFileName));
        }
    }

    /**
     * Adds an input stream to the request.
     *
     * @param key the key name for the new param.
     * @param stream the input stream to add.
     */
    public void put(String key, InputStream stream) {
        put(key, stream, null);
    }

    /**
     * Adds an input stream to the request.
     *
     * @param key the key name for the new param.
     * @param stream the input stream to add.
     * @param name the name of the stream.
     */
    public void put(String key, InputStream stream, String name) {
        put(key, stream, name, null);
    }

    /**
     * Adds an input stream to the request.
     *
     * @param key the key name for the new param.
     * @param stream the input stream to add.
     * @param name the name of the stream.
     * @param contentType the content type of the file, eg. application/json
     */
    public void put(String key, InputStream stream, String name, ContentType contentType) {
        put(key, stream, name, contentType, autoCloseInputStreams);
    }

    /**
     * Adds an input stream to the request.
     *
     * @param key the key name for the new param.
     * @param stream the input stream to add.
     * @param name the name of the stream.
     * @param contentType the content type of the file, eg. application/json
     * @param autoClose close input stream automatically on successful upload
     */
    public void put(String key, InputStream stream, String name, ContentType contentType,
            boolean autoClose) {
        if (key != null && stream != null) {
            getStreamParams().put(key,
                    StreamWrapper.newInstance(stream, name, contentType, autoClose));
        }
    }

    /**
     * Adds param with non-string value (e.g. Map, List, Set).
     *
     * @param key the key name for the new param.
     * @param value the non-string value object for the new param.
     */
    public void put(String key, Object value) {
        if (key != null && value != null) {
            getUrlParamsWithObjects().put(key, value);
        }
    }

    /**
     * Adds a int value to the request.
     *
     * @param key the key name for the new param.
     * @param value the value int for the new param.
     */
    public void put(String key, int value) {
        if (key != null) {
            getUrlParams().put(key, String.valueOf(value));
        }
    }

    /**
     * Adds a long value to the request.
     *
     * @param key the key name for the new param.
     * @param value the value long for the new param.
     */
    public void put(String key, long value) {
        if (key != null) {
            getUrlParams().put(key, String.valueOf(value));
        }
    }

    /**
     * Adds string value to param which can have more than one value.
     *
     * @param key the key name for the param, either existing or new.
     * @param value the value string for the new param.
     */
    @SuppressWarnings("unchecked")
    public void add(String key, String value) {
        if (key != null && value != null) {
            Object params = getUrlParamsWithObjects().get(key);
            if (params == null) {
                // Backward compatible, which will result in "k=v1&k=v2&k=v3"
                params = new HashSet<String>();
                this.put(key, params);
            }
            if (params instanceof List) {
                ((List<Object>) params).add(value);
            } else if (params instanceof Set) {
                ((Set<Object>) params).add(value);
            }
        }
    }

    /**
     * Removes a parameter from the request.
     *
     * @param key the key name for the parameter to remove.
     */
    public void remove(String key) {
        getUrlParams().remove(key);
        getStreamParams().remove(key);
        getFileParams().remove(key);
        getUrlParamsWithObjects().remove(key);
    }

    /**
     * Check if a parameter is defined.
     *
     * @param key the key name for the parameter to check existence.
     * @return Boolean
     */
    public boolean has(String key) {
        return getUrlParams().get(key) != null || getStreamParams().get(key) != null
                || getFileParams().get(key) != null || getUrlParamsWithObjects().get(key) != null;
    }

    public boolean isAutoCloseInputStreams() {
        return autoCloseInputStreams;
    }

    public void setAutoCloseInputStreams(boolean autoCloseInputStreams) {
        this.autoCloseInputStreams = autoCloseInputStreams;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public void setStringContentType(ContentType stringContentType) {
        this.stringContentType = stringContentType;
    }

    public HttpEntity createEntity(ProgressHandlerInterface progressHandler) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(charset);
        builder.setMode(mode);
        lock.lock();
        try {
            if (urlParams != null) {
                for (Entry<String, String> entry : urlParams.entrySet()) {
                    builder.addTextBody(entry.getKey(), entry.getValue(), stringContentType);
                }
            }
            if (urlParamsWithObjects != null) {
                for (Entry<String, Object> entry : urlParamsWithObjects.entrySet()) {
                    builder.addTextBody(entry.getKey(), serialize(entry.getValue()),
                            stringContentType);
                }
            }
            if (streamParams != null) {
                for (Entry<String, StreamWrapper> entry : streamParams.entrySet()) {
                    builder.addBinaryBody(entry.getKey(), entry.getValue().inputStream,
                            entry.getValue().contentType, entry.getValue().name);
                }
            }
            if (fileParams != null) {
                for (Entry<String, FileWrapper> entry : fileParams.entrySet()) {
                    builder.addBinaryBody(entry.getKey(), entry.getValue().file,
                            entry.getValue().contentType, entry.getValue().customFileName);
                }
            }
        } finally {
            lock.unlock();
        }
        return builder.build();
    }

    public void setMode(HttpMultipartMode mode) {
        this.mode = mode;
    }

    protected String serialize(Object value) {
        return value.toString();
    }

    protected final Map<String, String> getUrlParams() {
        lock.lock();
        try {
            if (urlParams == null) {
                urlParams = new HashMap<String, String>();
            }
            return urlParams;
        } finally {
            lock.unlock();
        }
    }

    protected final Map<String, StreamWrapper> getStreamParams() {
        lock.lock();
        try {
            if (streamParams == null) {
                streamParams = new HashMap<String, StreamWrapper>();
            }
            return streamParams;
        } finally {
            lock.unlock();
        }
    }

    protected final Map<String, FileWrapper> getFileParams() {
        lock.lock();
        try {
            if (fileParams == null) {
                fileParams = new HashMap<String, FileWrapper>();
            }
            return fileParams;
        } finally {
            lock.unlock();
        }
    }

    protected final Map<String, Object> getUrlParamsWithObjects() {
        lock.lock();
        try {
            if (urlParamsWithObjects == null) {
                urlParamsWithObjects = new HashMap<String, Object>();
            }
            return urlParamsWithObjects;
        } finally {
            lock.unlock();
        }
    }
}
