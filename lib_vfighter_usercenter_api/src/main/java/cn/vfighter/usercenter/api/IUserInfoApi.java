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

import java.util.Date;

import cn.vfighter.usercenter.bean.UserInfo;

/**
 * 用户资料服务接口
 * 
 * @author konlg
 */
public interface IUserInfoApi {
	/**
	 * 获取用户资料
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @return
	 */
	UserInfo getUserInfo(long accountId);

	/**
	 * 创建用户资料(用户注册时，从帐号模块调用)
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @return
	 */
	long createUserInfo(long accountId);

	/**
	 * 更新昵称
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param nickname
	 *            新的昵称
	 * @return
	 */
	boolean updateNickname(long accountId, String nickname);

	/**
	 * 更新岁数
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param age
	 *            年龄
	 * @return
	 */
	boolean updateAge(long accountId, int age);

	/**
	 * 更新生日
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param birthday
	 *            出生日期
	 * @return
	 */
	boolean updateBirthday(long accountId, Date birthday);

	/**
	 * 更新身高
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param height
	 *            身高
	 * @return
	 */
	boolean updateHeight(long accountId, int height);

	/**
	 * 更新性格描述
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param nature
	 *            性格描述
	 * @return
	 */
	boolean updateNature(long accountId, String nature);

	/**
	 * 更新自我说明
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param desc
	 *            自我说明
	 * @return
	 */
	boolean updateDesc(long accountId, String desc);

	/**
	 * 更新用户资料
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param nickName
	 *            昵称
	 * @param age
	 *            年龄
	 * @param birthday
	 *            出生日期
	 * @param height
	 *            身高(cm)
	 * @param nature
	 *            性格描述
	 * @param desc
	 *            自我说明
	 * @return
	 */
	boolean updateUserInfo(long accountId, String nickName, int age, Date birthday, int height, String nature,
			String desc);
}
