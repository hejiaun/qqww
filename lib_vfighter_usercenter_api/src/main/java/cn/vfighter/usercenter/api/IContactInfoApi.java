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

import cn.vfighter.usercenter.bean.ContactInfo;
import cn.vfighter.usercenter.bean.ContactType;

/**
 * 用户联系方式服务接口
 * 
 * @author konlg
 */
public interface IContactInfoApi {

    /**
     * 获取所有联系方式类型
     * 
     * @return 联系方式类型的集合
     */
    Collection<ContactType> getAllContactType();

    /**
     * 检测联系方式类型是否存在
     * 
     * @param accountId 用户帐号ID
     * @param contactType 联系方式类型
     * @return
     */
    boolean existsContactInfo(long accountId, String contactType);

    /**
     * 获取指定类型的联系方式
     * 
     * @param accountId 用户帐号ID
     * @param contactType 联系方式类型
     * @return
     */
    ContactInfo getContactInfo(long accountId, String contactType);

    /**
     * 获取用户的所有联系方式
     * 
     * @param accountId 用户帐号ID
     * @return
     */
    Collection<ContactInfo> getAllContactInfo(long accountId);

    /**
     * 添加用户的联系方式
     * 
     * @param accountId 用户帐号ID
     * @param contactType 联系方式类型
     * @param value 联系方式内容
     * @return
     */
    long addContactInfo(long accountId, String contactType, String value);

    /**
     * 删除用户的联系方式
     * 
     * @param accountId 用户帐号ID
     * @param contactType 联系方式类型
     * @return
     */
    boolean deleteContactInfo(long accountId, String contactType);
}
