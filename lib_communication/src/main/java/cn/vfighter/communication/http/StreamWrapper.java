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

import java.io.InputStream;

import org.apache.http.entity.ContentType;

public class StreamWrapper {
    public final InputStream inputStream;
    public final String name;
    public final ContentType contentType;
    public final boolean autoClose;

    public StreamWrapper(InputStream inputStream, String name, ContentType contentType,
            boolean autoClose) {
        this.inputStream = inputStream;
        this.name = name;
        this.contentType = contentType;
        this.autoClose = autoClose;
    }

    static StreamWrapper newInstance(InputStream inputStream, String name, ContentType contentType,
            boolean autoClose) {
        return new StreamWrapper(inputStream, name,
                contentType == null ? ContentType.DEFAULT_BINARY : contentType, autoClose);
    }
}