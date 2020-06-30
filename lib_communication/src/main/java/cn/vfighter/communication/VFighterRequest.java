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

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import cn.vfighter.communication.auth.ISessionKey;

/**
 * 通讯请求对象
 * 
 * @author konlg
 */
public class VFighterRequest<T> implements ISessionKey, Serializable {
    private static final long serialVersionUID = 6260742821326141494L;

    private String requestNo; // 请求编号 string
    private long requestTime; // 请求时间
    private T param; // 请求参数

    @Expose
    private transient String sessionKey;

    /**
     * 获取安全会话key
     *
     * @return authKey xxx
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * 设置安全会话key
     *
     * @param authKey 要设置的 安全会话key
     */
    public void setSessionKey(String authKey) {
        this.sessionKey = authKey;
    }

    /**
     * 创建一个请求对象
     */
    public VFighterRequest() {
        this.requestTime = System.currentTimeMillis();
        requestNo = requestTime + generateRandomCode(3);
    }

    /**
     * 创建一个请求对象
     * 
     * @param rno 请求编号
     * @param param 请求参数实体
     */
    public VFighterRequest(String rno, T param) {
        this.requestNo = rno;
        this.param = param;
    }

    /**
     * 创建一个请求对象
     * 
     * @param param 请求参数实体
     */
    public VFighterRequest(T param) {
        this();
        this.param = param;
    }

    /**
     * 获取请求编号
     *
     * @return requestNo 请求编号
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * 设置请求编号
     *
     * @param requestNo 要设置的 请求编号
     */
    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    /**
     * 获取请求时间
     *
     * @return requestTime 请求时间
     */
    public long getRequestTime() {
        return requestTime;
    }

    /**
     * 设置请求时间
     *
     * @param requestTime 要设置的 请求时间
     */
    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 获取请求参数
     *
     * @return param 请求参数
     */
    public T getParam() {
        return param;
    }

    /**
     * 设置请求参数
     *
     * @param param 要设置的 请求参数
     */
    public void setParam(T param) {
        this.param = param;
    }

    /**
     * 生成随机码，一次最多生成10个随机数
     */
    private static String generateRandomCode(int length) {
        double code = Math.random();
        for (int i = 0; i < 10; i++) {
            if (code > 0.1 && code < 1) {
                break;
            } else {
                code = Math.random();
            }
        }
        int js = 1;
        for (int i = 0; i < length; i++) {
            js *= 10;
        }
        return String.valueOf(Math.round((code * js)));
    }
}
