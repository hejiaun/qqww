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

package cn.vfighter.usercenter.bean;

/**
 * 用户积分通讯实体
 * 
 * @author konlg
 */
public class UserPoints {
    private long id;
    private long accountId;
    private int code;
    private long value;

    /**
     * 获取 ID，自增编号
     * 
     * @return id 自增编号
     */
    public long getId() {
        return id;
    }

    /**
     * 设置 自增编号
     * 
     * @param id 要设置的 自增编号
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取 用户帐号ID
     * 
     * @return accountId 用户帐号ID
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * 设置 用户帐号ID
     * 
     * @param accountId 要设置的 用户帐号ID
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取 积分类型编码
     * 
     * @return code 积分类型编码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置 积分类型编码
     * 
     * @param code 要设置的 积分类型编码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取 积分值
     * 
     * @return value 积分值
     */
    public long getValue() {
        return value;
    }

    /**
     * 设置 积分值
     * 
     * @param value 要设置的 积分值
     */
    public void setValue(long value) {
        this.value = value;
    }
}
