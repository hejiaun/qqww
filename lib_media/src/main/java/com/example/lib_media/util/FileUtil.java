package com.example.lib_media.util;

import java.util.UUID;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  文件工具类
 */
public class FileUtil {
    private static FileUtil fileUtil;

    private FileUtil() {
    }

    public static FileUtil getInstence() {
        if (fileUtil == null) {
            synchronized (FileUtil.class) {
                if (fileUtil == null) {
                    fileUtil = new FileUtil();
                }
            }
        }
        return fileUtil;
    }

    /**
     * 获取随机文件名字
     *
     * @return
     */
    public String getGenerateFileName() {
        return UUID.randomUUID().toString();
    }

}
