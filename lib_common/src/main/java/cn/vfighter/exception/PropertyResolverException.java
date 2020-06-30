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
 * 属性文件异常
 * 
 * @author konlg
 */
public class PropertyResolverException extends IOException {
    private static final long serialVersionUID = 6850992604003665551L;

    /**
     * 创建一个属性文件异常
     * 
     * @param message 异常消息
     */
    public PropertyResolverException(String message) {
        super(ERROR_OS_IO_PROPERTY, message);
    }

    /**
     * 创建一个属性文件异常
     * 
     * @param message 产生异常的原因（源异常）
     */
    public PropertyResolverException(Throwable cause) {
        super(ERROR_OS_IO_PROPERTY, cause);
    }

    /**
     * 创建一个属性文件异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     */
    public PropertyResolverException(short code, String message) {
        super(code, message);
    }

    /**
     * 创建一个属性文件异常
     * 
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public PropertyResolverException(String message, Throwable cause) {
        super(ERROR_OS_IO_PROPERTY, message, cause);
    }

    /**
     * 创建一个属性文件异常
     * 
     * @param code 异常编码
     * @param message 异常消息
     * @param cause 产生异常的原因（源异常）
     */
    public PropertyResolverException(short code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
