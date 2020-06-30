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

package cn.vfighter.account.param;

/**
 * @author konlg
 */
public class BindAccountParam {
    long accountId;
    String loginName;
    String loginType;
    String endType;

    /**
     * 获取
     * 
     * @return accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * 设置
     * 
     * @param accountId 要设置的 accountId
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取
     * 
     * @return loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置
     * 
     * @param loginName 要设置的 loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取
     * 
     * @return loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * 设置
     * 
     * @param loginType 要设置的 loginType
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * 获取
     * 
     * @return endType
     */
    public String getEndType() {
        return endType;
    }

    /**
     * 设置
     * 
     * @param endType 要设置的 endType
     */
    public void setEndType(String endType) {
        this.endType = endType;
    }

}
