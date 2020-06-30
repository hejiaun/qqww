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

import com.google.gson.annotations.SerializedName;

/**
 * 分页参数
 * 
 * @author Konlg
 */
public class PagingParam implements IPagingParam {
    @SerializedName("pageIndex")
    int pageIndex;

    @SerializedName("pageLength")
    int pageLength;

    /**
     * 分页参数
     */
    public PagingParam() {
    }

    /**
     * 分页参数
     * 
     * @param pageIndex 分页页码
     * @param pageLength 每页长度
     */
    public PagingParam(int pageIndex, int pageLength) {
        this.pageIndex = pageIndex;
        this.pageLength = pageLength;
    }

    /**
     * 获取分页页码
     *
     * @return pageIndex 分页页码
     */
    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置分页页码
     *
     * @param pageIndex 要设置的 分页页码
     */
    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 获取每页长度
     *
     * @return pageLength 每页长度
     */
    @Override
    public int getPageLength() {
        return pageLength;
    }

    /**
     * 设置每页长度
     *
     * @param pageLength 要设置的 每页长度
     */
    @Override
    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

}
