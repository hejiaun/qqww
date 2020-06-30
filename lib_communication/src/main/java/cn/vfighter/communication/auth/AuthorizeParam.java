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

package cn.vfighter.communication.auth;

/**
 * 安全授权参数
 * 
 * @author Konlg
 */
public class AuthorizeParam {
    String appdomain;
    String endId;
    String sign;

    /**
     * 创建一个授权参数
     * 
     * @param appdomain 域名
     * @param endId 终端ID
     * @param sign 签名
     */
    public AuthorizeParam(String appdomain, String endId, String sign) {
        this.appdomain = appdomain;
        this.endId = endId;
        this.sign = sign;
    }

    /**
     * 创建一个授权参数
     * 
     * @param appdomain 域名
     * @param endId 终端ID
     */
    public AuthorizeParam(String appdomain, String endId) {
        this.appdomain = appdomain;
        this.endId = endId;
    }

    /**
     * 获取 应用域名称
     *
     * @return appdomain 应用域名称
     */
    public String getAppdomain() {
        return appdomain;
    }

    /**
     * 设置 应用域名称
     *
     * @param appdomain 要设置的 应用域名称
     */
    public void setAppdomain(String appdomain) {
        this.appdomain = appdomain;
    }

    /**
     * 获取 终端ID
     * 
     * @return endId 终端ID
     */
    public String getEndId() {
        return endId;
    }

    /**
     * 设置 终端ID
     * 
     * @param endId 终端ID
     */
    public void setEndId(String endId) {
        this.endId = endId;
    }

    /**
     * 获取 安全签名
     *
     * @return sign 安全签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置 安全签名
     *
     * @param sign 要设置的 安全签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
}
