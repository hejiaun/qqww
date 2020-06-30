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
 * 未知异常
 * 
 * @author konlg
 */
public class UnkonwnException extends VFighterException {
    private static final long serialVersionUID = -1723522011496987927L;

    /**
     * 创建一个未知异常
     * 
     * @param message 异常消息
     */
    public UnkonwnException(String message) {
        super(ERROR_UNKNOWN, message);
    }

    /**
     * 创建一个未知异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     */
    public UnkonwnException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个未知异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public UnkonwnException(String message, Throwable cause) {
        super(ERROR_UNKNOWN, message, cause);
    }

    /**
     * 创建一个未知异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public UnkonwnException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
