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
 * 业务异常
 * 
 * @author konlg
 */
public class BizException extends VFighterException {
    private static final long serialVersionUID = 1936051276490169322L;

    /**
     * 创建一个业务异常
     * 
     * @param message 异常消息
     */
    public BizException(String message) {
        super(ERROR_BIZ, message);
    }

    /**
     * 创建一个业务异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     */
    public BizException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个业务异常
     * 
     * @param code 异常编码
     * @param cause 产生异常的原因（源异常）
     */
    public BizException(short code, Throwable cause) {
        super(code, cause.getMessage(), cause);
    }

    /**
     * 创建一个业务异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public BizException(String message, Throwable cause) {
        super(ERROR_BIZ, message, cause);
    }

    /**
     * 创建一个业务异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public BizException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
