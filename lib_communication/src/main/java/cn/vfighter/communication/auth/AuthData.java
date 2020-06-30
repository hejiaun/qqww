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
 * 安全数据
 * 
 * @author Konlg
 */
public class AuthData {

    @SerializedName("key")
    private String key;

    @SerializedName("authKey")
    private int[] authKey;

    @SerializedName("createdTime")
    private long createdTime;

    @SerializedName("timeout")
    private int timeout;

    /**
     * 获取 Key
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置 Key
     *
     * @param key 要设置的 key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取 安全密钥
     *
     * @return 安全密钥
     */
    public int[] getAuthKey() {
        return authKey;
    }

    /**
     * 设置 安全密钥
     *
     * @param authKey 要设置的 安全密钥
     */
    public void setAuthKey(int[] authKey) {
        this.authKey = authKey;
    }

    /**
     * 获取 创建时间
     *
     * @return createdTime 创建时间
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置 创建时间
     *
     * @param createdTime 要设置的 创建时间
     */
    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取 超时时间
     *
     * @return timeout 超时时间
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * 设置 超时时间
     *
     * @param timeout 要设置的 超时时间
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 是否超时
     * 
     * @return 如果超时，返回true。
     */
    public boolean isTimeout() {
        return System.currentTimeMillis() >= (this.createdTime + this.timeout);
    }
}
