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

import com.google.gson.annotations.SerializedName;

/**
 * 认证信息
 * 
 * @author Konlg
 */
public class AuthInfo implements IAuthInfo {
    @SerializedName("authCodecKey")
    private String authCodecKey;

    @SerializedName("timeout")
    private int timeout;

    @Override
    public String getAuthCodecKey() {
        return authCodecKey;
    }

    /**
     * 设置 TEA 加解密key
     *
     * @param authCodecKey 要设置的 TEA 加解密key
     */
    public void setAuthCodecKey(String authCodecKey) {
        this.authCodecKey = authCodecKey;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    /**
     * 设置 认证超时时间
     *
     * @param timeout 要设置的 认证超时时间
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
