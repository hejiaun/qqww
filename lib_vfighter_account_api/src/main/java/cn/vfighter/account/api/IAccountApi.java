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

package cn.vfighter.account.api;

import cn.vfighter.account.bean.UserAccount;
import cn.vfighter.communication.client.ExecuteException;

/**
 * 帐号服务接口
 *
 * @author konlg
 */
public interface IAccountApi {

    /**
     * 检测登录名是否存在
     *
     * @param domain    域
     * @param loginName 登录名
     * @return
     */
    boolean existsLoginName(String domain, String loginName) throws ExecuteException;

    /**
     * 设置为测试帐号
     *
     * @param accountId 帐号ID
     * @return
     */
    boolean setTest(long accountId);

    /**
     * 批量设置测试帐号
     *
     * @param accountIds 帐号ID
     * @return
     */
    boolean setTests(long accountIds[]);

    /**
     * 取消设置测试帐号
     *
     * @param accountId 帐号ID
     * @return
     */
    boolean unsetTest(long accountId);

    /**
     * 批量取消测试帐号
     *
     * @param accountIds 帐号ID
     * @return
     */
    boolean unsetTests(long accountIds[]);

    /**
     * 注册帐号
     *
     * @param domain    所属域
     * @param loginName 登录名
     * @param loginType 登录名类型
     * @param endType   终端类型
     * @param password  登录密码
     * @param clientIp  客户端IP
     * @return
     */
    UserAccount registerAccount(String domain, String loginName, String loginType, String endType,
                                String password, String clientIp) throws ExecuteException;

    /**
     * 登录帐号
     *
     * @param domain    所属域
     * @param loginName 登录名
     * @param endType   终端类型
     * @param password  登录密码
     * @param clientIp  登录密码
     * @return
     */
    UserAccount login(String domain, String loginName, String endType, String password,
                      String clientIp);

    /**
     * 绑定新的登陆名
     *
     * @param accountId 帐号ID
     * @param domain    域
     * @param loginName 新的登陆名
     * @param loginType 登陆账号类型
     * @param endType   终端
     * @return 如果绑定成功，返回true
     */
    boolean bindAccount(long accountId, String domain, String loginName, String loginType,
                        String endType);

    /**
     * 取消绑定登录名
     *
     * @param accountId 帐号ID
     * @param domain    域
     * @param loginName 登录名
     */
    void unbindAccount(long accountId, String domain, String loginName);

    /**
     * 获取用户账户数据
     *
     * @param accountId 帐号ID
     * @return
     */
    UserAccount getUserAccount(long accountId);
}
