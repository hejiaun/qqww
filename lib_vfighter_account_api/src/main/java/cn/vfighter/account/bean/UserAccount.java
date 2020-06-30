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

/**
 * 用户帐号信息
 * 
 * @author konlg
 */
public class UserAccount {
    private long id;
    private boolean isTest;
    private String currentLogin;
    private AccountStatus status;

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

    /**
     * 获取 当前使用的登录名
     * 
     * @return currentLogin 当前使用的登录名
     */
    public String getCurrentLogin() {
        return currentLogin;
    }

    /**
     * 设置 当前使用的登录名
     * 
     * @param currentLogin 要设置的 当前使用的登录名
     */
    public void setCurrentLogin(String currentLogin) {
        this.currentLogin = currentLogin;
    }

    /**
     * 获取 帐号状态
     * 
     * @return status 帐号状态
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * 设置 帐号状态
     * 
     * @param status 要设置的 帐号状态
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

}
