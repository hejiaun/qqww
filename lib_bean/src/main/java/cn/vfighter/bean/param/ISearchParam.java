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

package cn.vfighter.bean.param;

/**
 * 搜索参数接口
 * 
 * @author Konlg
 */
public interface ISearchParam {
    /**
     * 获取搜索内容
     *
     * @return search 搜索内容
     */
    String getSearch();

    /**
     * 设置搜索内容
     *
     * @param search 要设置的 搜索内容
     */
    void setSearch(String search);

}
