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

package cn.vfighter.communication;

import com.google.gson.annotations.Expose;

import cn.vfighter.bean.PagingData;

/**
 * 响应一个分页集合
 * 
 * @author konlg
 * @param <T> 数据类型
 */
public class ResponsePaging<T> extends VFighterResponse<T> {
    private static final long serialVersionUID = 9074900171833592075L;

    @Expose
    private PagingData<T> data;

    public ResponsePaging() {
        super();
    }

    public ResponsePaging(VFighterRequest<?> req) {
        super(req);
    }

    /**
     * 获取响应结果集合
     *
     * @return data 响应结果集合
     */
    public PagingData<T> getData() {
        return data;
    }

    /**
     * 设置响应结果集合
     *
     * @param data 要设置的 响应结果集合
     */
    public void setData(PagingData<T> data) {
        this.data = data;
    }

}
