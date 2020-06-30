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
 * 解码异常
 * 
 * @author konlg
 */
public class DecodeException extends CodecException {
    private static final long serialVersionUID = -1907571879192397421L;

    /**
     * 创建一个解码异常
     * 
     * @param message 异常消息
     */
    public DecodeException(String message) {
        super(ERROR_FW_DECODE, message);
    }

    /**
     * 创建一个解码异常
     * 
     * @param code 异常解码
     * @param message 异常消息
     */
    public DecodeException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个解码异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public DecodeException(String message, Throwable cause) {
        super(ERROR_FW_DECODE, message, cause);
    }

    /**
     * 创建一个解码异常
     * 
     * @param code 异常解码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public DecodeException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
