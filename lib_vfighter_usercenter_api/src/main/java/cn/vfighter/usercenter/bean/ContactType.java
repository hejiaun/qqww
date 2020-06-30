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
 * 联系方式类型持久化实体
 * 
 * @author konlg
 */
public class ContactType {

    /** ID，自增编号 */
    private long id;

    /** 联系方式类型标题 */
    private String title;

    /** 联系方式类型 */
    private String contactType;

    /**
     * 获取
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
     * 获取 联系方式类型标题
     * 
     * @return title 联系方式类型标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 联系方式类型标题
     * 
     * @param title 要设置的 联系方式类型标题
     */
    public void setTitle(String title) {
        this.title = title;
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

}
