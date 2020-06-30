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

package cn.vfighter.account;

/**
 * REST路径定义
 *
 * @author Konlg
 */
public interface RestInterfaceUrl {
    /**
     * 注册帐号
     */
    String account_registerAccount = "open/account/registerAccount";
    /**
     * 检测登录名是否存在
     */
    String account_existsLoginName = "open/account/existsLoginName";

    String account_login = "account/login";
    String account_logout = "account/logout";

    /**
     * 设置为测试帐号
     */
    String account_setTest = "service/account/setTest";
    /**
     * 批量设置测试帐号
     */
    String account_setTests = "service/account/setTests";
    /**
     * 取消设置测试帐号
     */
    String account_unsetTest = "service/account/unsetTest";
    /**
     * 批量取消测试帐号
     */
    String account_unsetTests = "service/account/unsetTests";

    /**
     * 绑定新的登陆名
     */
    String account_bindAccount = "service/account/bindAccount";
    /**
     * 取消绑定登录名
     */
    String account_unbindAccount = "service/account/unbindAccount";
    /**
     * 获取用户账户数据
     */
    String account_getUserAccount = "service/open/getUserAccount";

    /**
     * 检测登录名是否存在
     */
    String account_existsLoginName_internal = "internal/account/existsLoginName";
    /**
     * 设置为测试帐号
     */
    String account_setTest_internal = "internal/account/setTest";
    /**
     * 批量设置测试帐号
     */
    String account_setTests_internal = "internal/account/setTests";
    /**
     * 取消设置测试帐号
     */
    String account_unsetTest_internal = "internal/account/unsetTest";
    /**
     * 批量取消测试帐号
     */
    String account_unsetTests_internal = "internal/account/unsetTests";
    /**
     * 注册帐号
     */
    String account_registerAccount_internal = "internal/account/registerAccount";
    /**
     * 绑定新的登陆名
     */
    String account_bindAccount_internal = "internal/account/bindAccount";
    /**
     * 取消绑定登录名
     */
    String account_unbindAccount_internal = "internal/account/unbindAccount";
    /**
     * 获取用户账户数据
     */
    String account_getUserAccount_internal = "internal/open/getUserAccount";
}
