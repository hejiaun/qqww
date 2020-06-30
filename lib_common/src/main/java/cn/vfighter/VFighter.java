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

package cn.vfighter;

import java.util.regex.Pattern;

/**
 * 公共全局常量定义
 * 
 * @author konlg
 */
public class VFighter {
    /** 默认版本号 */
    public static final String VERSION = "1.0.0";
    /** 默认字符集 */
    public static final String DEFAULT_CHARACTER = "utf-8";
    ////////////////////////////////////////
    // default
    /** 默认整数值 */
    public static final int DEFAULT_INT_VALUE = 0;
    /** 默认字符串值 */
    public static final String DEFAULT_VALUE = "default";

    /** 框架名称 */
    public static final String FRAMEWORK_NAME = "xingfu";

    public static final String PATH_SEPARATOR = "/";
    public static final String COMMA_SEPARATOR = ",";
    public static final Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

    /**
     * 默认的consistent的hash的数量
     */
    public static final int DEFAULT_CONSISTENT_HASH_BASE_LOOP = 1000;

    ////////////////////////////////////////
    // date time
    public static final int SECOND_MILLS = 1000;
    public static final int MINUTE_MILLS = 60 * SECOND_MILLS;

}
