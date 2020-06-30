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

package cn.vfighter.account.bean;

import java.util.Date;

/**
 * 用户帐号信息(管理用)
 * 
 * @author konlg
 */
public class AccountInfo {
    private long id;
    private Date regTime;
    private String regIp;
    private String regDomain;
    private int loginNumber;
    private boolean isTest;
    private int state;

    /**
     * 获取 帐号的ID
     * 
     * @return id 帐号的ID
     */
    public long getId() {
        return id;
    }

    /**
     * 设置 帐号的ID
     * 
     * @param id 要设置的 帐号的ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取
     * 
     * @return regTime
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * 设置
     * 
     * @param regTime 要设置的 regTime
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * 获取
     * 
     * @return regIp
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * 设置
     * 
     * @param regIp 要设置的 regIp
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    /**
     * 获取
     * 
     * @return regDomain
     */
    public String getRegDomain() {
        return regDomain;
    }

    /**
     * 设置
     * 
     * @param regDomain 要设置的 regDomain
     */
    public void setRegDomain(String regDomain) {
        this.regDomain = regDomain;
    }

    /**
     * 获取
     * 
     * @return loginNumber
     */
    public int getLoginNumber() {
        return loginNumber;
    }

    /**
     * 设置
     * 
     * @param loginNumber 要设置的 loginNumber
     */
    public void setLoginNumber(int loginNumber) {
        this.loginNumber = loginNumber;
    }

    /**
     * 获取
     * 
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * 设置
     * 
     * @param state 要设置的 state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取 是否是测试帐号
     * 
     * @return isTest 是否是测试帐号
     */
    public boolean isTest() {
        return isTest;
    }

    /**
     * 设置 是否是测试帐号
     * 
     * @param isTest 要设置的 是否是测试帐号
     */
    public void setTest(boolean isTest) {
        this.isTest = isTest;
    }
}
