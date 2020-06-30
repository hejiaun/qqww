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
 * 联系方式持久化实体
 * 
 * @author konlg
 */
public class ContactInfo {

    /** ID，自增编号 */
    private long id;

    /** 用户帐号ID */
    private long accountId;

    /** 联系方式类型 */
    private String contactType;

    /** 联系方式内容 */
    private String value;

    /**
     * 获取 id
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * 
     * @param id 要设置的 id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取 帐号ID
     * 
     * @return accountId 帐号ID
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * 设置 帐号ID
     * 
     * @param accountId 要设置的 帐号ID
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取 联系方式类型
     * 
     * @return contactType 联系方式类型
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * 设置 联系方式类型
     * 
     * @param contactType 要设置的 联系方式类型
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * 获取 联系方式内容
     * 
     * @return value 联系方式内容
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 联系方式内容
     * 
     * @param value 要设置的 联系方式内容
     */
    public void setValue(String value) {
        this.value = value;
    }

}
