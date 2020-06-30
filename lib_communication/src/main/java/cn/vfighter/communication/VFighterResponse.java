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

import cn.vfighter.bean.exception.ExceptionInfo;
import cn.vfighter.communication.auth.ISessionKey;

/**
 * 通讯响应对象
 * 
 * @author Konlg
 */
public class VFighterResponse<U> implements ISessionKey, Serializable {
    private static final long serialVersionUID = 1245236737816748571L;

    private String requestNo; // 请求编号
    private boolean error = false; // 是否有错误
    private ExceptionInfo exception; // 异常对象

    @Expose
    private transient String sessionKey;

    public VFighterResponse() {
        super();
    }

    /**
     * 从请求中复制请求序列号和会话KEY
     * 
     * @param req 对应的请求
     */
    public VFighterResponse(VFighterRequest<?> req) {
        requestNo = req.getRequestNo();
        sessionKey = req.getSessionKey();
    }

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
     * 获取是否有错误
     *
     * @return error 是否有错误
     */
    public boolean hasException() {
        return error;
    }

    /**
     * 获取异常对象
     *
     * @return exception 异常对象
     */
    public ExceptionInfo getException() {
        return exception;
    }

    /**
     * 设置异常对象
     *
     * @param exception 要设置的 异常对象
     */
    public void setException(ExceptionInfo exception) {
        this.error = true;
        this.exception = exception;
    }
}
