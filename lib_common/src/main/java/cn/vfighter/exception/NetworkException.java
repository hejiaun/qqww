/* Copyright (c) vfighter.cn, All Rights Reserved
 *              ____     __    __
 *   _   ______/ __/____/ / __/ /_________
 *  | | / /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | |/ / / / / /__  / // // /_/ ___/ /
 *  |___/_/ /_/  __/ /_//_/ \__/\___/_/
 *              \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.exception;

/**
 * 网络异常
 * 
 * @author konlg
 */
public class NetworkException extends OSException {
    private static final long serialVersionUID = -6835146521397000266L;

    /**
     * 创建一个网络异常
     * 
     * @param message 异常消息
     */
    public NetworkException(String message) {
        super(ERROR_OS_NET, message);
    }

    /**
     * 创建一个网络异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     */
    public NetworkException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个网络异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public NetworkException(String message, Throwable cause) {
        super(ERROR_OS_NET, message, cause);
    }

    /**
     * 创建一个网络异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public NetworkException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
