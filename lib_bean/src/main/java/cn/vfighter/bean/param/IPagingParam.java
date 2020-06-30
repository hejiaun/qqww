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
 * 分页参数接口
 * 
 * @author Konlg
 */
public interface IPagingParam {
    /**
     * 获取分页页码
     *
     * @return pageIndex 分页页码
     */
    int getPageIndex();

    /**
     * 设置分页页码
     *
     * @param pageIndex 要设置的 分页页码
     */
    void setPageIndex(int pageIndex);

    /**
     * 获取每页长度
     *
     * @return pageLength 每页长度
     */
    int getPageLength();

    /**
     * 设置每页长度
     *
     * @param pageLength 要设置的 每页长度
     */
    void setPageLength(int pageLength);

}
