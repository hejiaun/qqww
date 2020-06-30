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

import org.apache.http.entity.ContentType;

public class FileWrapper {
    public final File file;
    public final ContentType contentType;
    public final String customFileName;

    public FileWrapper(File file, ContentType contentType, String customFileName) {
        this.file = file;
        this.contentType = contentType;
        this.customFileName = customFileName;
    }
}
