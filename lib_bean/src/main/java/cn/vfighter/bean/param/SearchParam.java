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

import java.util.HashMap;
import java.util.Map;

/**
 * 搜索参数
 *
 * @author Konlg
 */
public class SearchParam extends PagingParam implements ISortParam, ISearchParam, IPagingParam {
    @SerializedName("sorts")
    Map<String, String> sorts;

    @SerializedName("search")
    String search;

    /**
     * 创建一个搜索参数
     */
    public SearchParam() {
        sorts = new HashMap<String, String>();
    }

    /**
     * 创建一个搜索参数
     *
     * @param search     搜索内容
     * @param sorts      排序字典
     * @param pageIndex  分页页码
     * @param pageLength 每页大小
     */
    public SearchParam(String search, Map<String, String> sorts, int pageIndex, int pageLength) {
        super(pageIndex, pageLength);
        this.search = search;
        this.sorts = sorts;
    }

    /**
     * 获取搜索内容
     *
     * @return search 搜索内容
     */
    @Override
    public String getSearch() {
        return search;
    }

    /**
     * 设置搜索内容
     *
     * @param search 要设置的 搜索内容
     */
    @Override
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * 获取排序字典
     *
     * @return sorts 排序字典
     */
    @Override
    public Map<String, String> getSorts() {
        return sorts;
    }

    @Override
    public void addAsc(String column) {
        this.sorts.put(column, "asc");
    }

    @Override
    public void addDesc(String column) {
        this.sorts.put(column, "desc");
    }
}
