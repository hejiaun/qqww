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
 * @date    2018年10月26日
 */

package cn.vfighter.communication.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/**
 * Http 返回结果
 * 
 * @author konlg
 */
public class HttpResponseResult {

    private final Header[] header;

    private final HttpEntity entity;

    // private final ICloseable executor;

    HttpResponseResult(Header[] header,
            HttpEntity entity/* , ICloseable executor */) {
        super();
        this.header = header;
        this.entity = entity;
        // this.executor = executor;
    }

    HttpResponseResult(HttpResponse response/* , ICloseable executor */) {
        this(response.getAllHeaders(), response.getEntity()/* , executor */);
    }

    /**
     * 结果的报文头
     * 
     * @return 报文头列表
     */
    public Header[] getHeader() {
        return header;
    }

    /**
     * 结果内容
     * 
     * @return 内容实体
     */
    public HttpEntity getEntity() {
        return entity;
    }

    // /**
    // * 可关闭的底层链接
    // *
    // * @return
    // */
    // public ICloseable getCloseable() {
    // return executor;
    // }

}
