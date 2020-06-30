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

import java.util.Collection;

import cn.vfighter.account.bean.AccountInfo;

/**
 * 帐号管理接口
 * 
 * @author konlg
 */
public interface IAccountManageApi {

    /**
     * 按照ID获取帐号
     * 
     * @param accountId 帐号ID
     * @return
     */
    AccountInfo getAccount(long accountId);

    /**
     * 使用登录名获取帐号
     * 
     * @param domain 所属域
     * @param loginName 登录名
     * @return
     */
    AccountInfo getAccountByLogin(String domain, String loginName);

    /**
     * 按照类型获取帐号
     * 
     * @param loginType 登录名类型
     * @param pageIndex 分页的页码
     * @param pageLength 每页的大小
     * @return
     */
    Collection<AccountInfo> getAccountsByType(String loginType, int pageIndex, int pageLength);

    /**
     * 按照时间获取帐号列表
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageIndex 分页的页码
     * @param pageLength 每页的大小
     * @return
     */
    Collection<AccountInfo> getAccountsByDate(long startTime, long endTime, int pageIndex,
            int pageLength);

    /**
     * 搜索帐号
     * 
     * @param search 要搜索的内容
     * @param pageIndex 分页的页码
     * @param pageLength 每页的大小
     * @return
     */
    Collection<AccountInfo> searchAccount(String search, int pageIndex, int pageLength);
}
