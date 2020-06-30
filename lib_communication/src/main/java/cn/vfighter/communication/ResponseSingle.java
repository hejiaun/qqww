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

/**
 * 响应单个对象
 * 
 * @author Konlg
 */
public class ResponseSingle<T> extends VFighterResponse<T> {
    private static final long serialVersionUID = -2537641530352003584L;

    private T data;

    public ResponseSingle() {
        super();
    }

    public ResponseSingle(VFighterRequest<?> req) {
        super(req);
    }

    /**
     * 获取响应结果对象
     * 
     * @return data 响应结果对象
     */
    public T getData() {
        return data;
    }

    /**
     * 设置响应结果对象
     * 
     * @param data 响应结果对象
     */
    public void setData(T data) {
        this.data = data;
    }
}
