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

package cn.vfighter.usercenter.api;

import java.util.Collection;

import cn.vfighter.usercenter.bean.UserPoints;

/**
 * 用户积分接口
 * 
 * @author konlg
 */
public interface IUserPointsApi {
    /**
     * 为某一类型增加1积分
     * 
     * @param accountId 用户帐号ID
     * @param code 积分类型
     * @return 改变后的积分总值
     */
    long addUserPoints(long accountId, int code);

    /**
     * 为某一类型增加指定积分值
     * 
     * @param accountId 用户帐号ID
     * @param code 积分类型
     * @param value 要增加的积分值
     * @return 改变后的积分总值
     */
    long addUserPoints(long accountId, int code, int value);

    /**
     * 为某一类型减少1积分
     * 
     * @param accountId 用户帐号ID
     * @param code 积分类型
     * @return 改变后的积分总值
     */
    long subtractUserPoints(long accountId, int code);

    /**
     * 为某一类型减少制定积分值
     * 
     * @param accountId 用户帐号ID
     * @param code 积分类型
     * @param value 要减少的积分值
     * @return 改变后的积分总值
     */
    long subtractUserPoints(long accountId, int code, int value);

    /**
     * 获取某一类型的用户积分值
     * 
     * @param accountId 用户帐号ID
     * @param code 积分类型
     * @return 指定类型的积分总值
     */
    long getUserPoints(long accountId, int code);

    /**
     * 获取用户的所有类型的积分值列表
     * 
     * @param accountId 用户帐号ID
     * @return 指定用户的积分值列表
     */
    Collection<UserPoints> getAllUserPoints(long accountId);
}
