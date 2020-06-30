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

import java.util.Collection;

/**
 * 响应一个集合
 * 
 * @author Konlg
 */
public class ResponseCollection<T> extends VFighterResponse<T> {
    private static final long serialVersionUID = 9074900171833592075L;

    private Collection<T> data;

    public ResponseCollection() {
        super();
    }

    public ResponseCollection(VFighterRequest<?> req) {
        super(req);
    }

    /**
     * 获取响应结果集合
     *
     * @return data 响应结果集合
     */
    public Collection<T> getData() {
        return data;
    }

    /**
     * 设置响应结果集合
     *
     * @param data 要设置的 响应结果集合
     */
    public void setData(Collection<T> data) {
        this.data = data;
    }

}
