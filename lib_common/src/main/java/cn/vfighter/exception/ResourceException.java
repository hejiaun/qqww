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
 * 资源异常
 * 
 * @author konlg
 */
public class ResourceException extends OSException {
    private static final long serialVersionUID = -5888147635841396958L;

    /**
     * 创建一个资源异常
     * 
     * @param message 异常消息
     */
    public ResourceException(String message) {
        super(ERROR_OS_RES, message);
    }

    /**
     * 创建一个资源异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     */
    public ResourceException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个资源异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public ResourceException(String message, Throwable cause) {
        super(ERROR_OS_RES, message, cause);
    }

    /**
     * 创建一个资源异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public ResourceException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
