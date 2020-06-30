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

import cn.vfighter.usercenter.bean.UserFavorite;

/**
 * 收藏夹接口
 * 
 * @author konlg
 */
public interface IUserFavoriteApi {
    /**
     * 添加收藏
     * 
     * @param accountId 帐号ID
     * @param srcModule 收藏的来源模块
     * @param value 收藏的内容目标
     * @param title 收藏的标题
     * @param author 内容的作者
     * @param authorImg 作者的缩略图
     * @param category 收藏分类
     * @return 收藏的ID
     */
    long addFavorite(long accountId, String srcModule, String value, String title, String author,
            String authorImg, String category);

    /**
     * 获取收藏内容
     * 
     * @param favoriteId 收藏ID
     * @return 收藏内容实体
     */
    UserFavorite getUserFavorite(long favoriteId);

    /**
     * 获取用户收藏列表
     * 
     * @param accountId 帐号ID
     * @param pageIndex 分页页码
     * @param pageLength 每页大小
     * @return 收藏列表
     */
    Collection<UserFavorite> getUserFavorites(long accountId, int pageIndex, int pageLength);

    /**
     * 按照分类获取收藏列表
     * 
     * @param accountId 帐号ID
     * @param category 收藏分类
     * @param pageIndex 分页页码
     * @param pageLength 每页大小
     * @return
     */
    Collection<UserFavorite> getUserFavoritesByCategory(long accountId, String category,
            int pageIndex, int pageLength);

    /**
     * 按照模块获取收藏列表
     * 
     * @param accountId 帐号ID
     * @param srcModule 来源模块
     * @param pageIndex 分页页码
     * @param pageLength 每页大小
     * @return
     */
    Collection<UserFavorite> getUserFavoritesByModule(long accountId, String srcModule,
            int pageIndex, int pageLength);
}
