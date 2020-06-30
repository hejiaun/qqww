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

package cn.vfighter.bean.exception;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * 异常信息实体
 * 
 * @author Konlg
 */
public class ExceptionInfo implements Serializable {
    private static final long serialVersionUID = -2775640441532615862L;

    @SerializedName("code")
    private short code = 0;

    @SerializedName("message")
    private String message;

    @SerializedName("level")
    private int level = 1; // 默认业务异常

    @SerializedName("category")
    private String category = "";

    /**
     * 创建一个异常信息
     */
    public ExceptionInfo() {
    }

    /**
     * 创建一个异常信息
     * 
     * @param message 异常消息
     */
    public ExceptionInfo(String message) {
        this.code = 0;
        this.message = message;
    }

    /**
     * 创建一个异常信息
     * 
     * @param code 异常代码
     * @param message 异常消息
     */
    public ExceptionInfo(short code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取异常代码
     *
     * @return code 异常代码
     */
    public short getCode() {
        return code;
    }

    /**
     * 设置异常代码
     *
     * @param code 要设置的 异常代码
     */
    public void setCode(short code) {
        this.code = code;
    }

    /**
     * 获取 错误级别
     *
     * @return level 错误级别
     *         <ul>
     *         <li>致命错误: 0</li>
     *         <li>业务错误: 1</li>
     *         </ul>
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置 错误级别
     *
     * @param level 要设置的 错误级别
     *            <ul>
     *            <li>致命错误: 0</li>
     *            <li>业务错误: 1</li>
     *            </ul>
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取 错误分类
     *
     * @return category 错误分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置 错误分类
     *
     * @param category 要设置的 错误分类
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取异常消息
     *
     * @return message 异常消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置异常消息
     *
     * @param message 要设置的 异常消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
