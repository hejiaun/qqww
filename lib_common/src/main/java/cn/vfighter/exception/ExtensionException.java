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
 * 扩展异常
 * 
 * @author konlg
 */
public class ExtensionException extends FrameworkException {
    private static final long serialVersionUID = 5556227580838219767L;

    /**
     * 创建一个扩展异常
     * 
     * @param message 异常消息
     */
    public ExtensionException(String message) {
        super(ERROR_FW_EXT, message);
    }

    /**
     * 创建一个扩展异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     */
    public ExtensionException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个扩展异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public ExtensionException(String message, Throwable cause) {
        super(ERROR_FW_EXT, message, cause);
    }

    /**
     * 创建一个扩展异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public ExtensionException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
