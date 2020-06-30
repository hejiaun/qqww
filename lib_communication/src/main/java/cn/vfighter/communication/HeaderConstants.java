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

package cn.vfighter.communication;

/**
 * 公共常量定义
 * 
 * @author Konlg
 */
public interface HeaderConstants {
    /**
     * 命名空间识别码
     */
    String HEADER_APPDOMAIN = "VF_APPDOMAIN";

    /**
     * 终端类型
     */
    String HEADER_ENDTYPE = "VF_ENDTYPE";

    /**
     * 终端ID
     */
    String HEADER_ENDID = "VF_ENDID";

    /**
     * 终端用户ID
     */
    String HEADER_USERID = "VF_USERID";

    /**
     * 授权请求
     */
    String HEADER_AUTHREQ = "VF_AUTHREQUEST";

    /**
     * api版本号
     */
    String HEADER_APIVERSION = "VF_APIVERSION";

    /**
     * 语言
     */
    String HEADER_LANGUAGE = "VF_LANGUAGE";
}
