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

package cn.vfighter.communication.exception;

import org.apache.http.Header;

/**
 * HTTP响应错误
 * 
 * @author konlg
 */
public class HttpResponseException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3889442296862229684L;

    private int errorcode;

    private String errmsg;

    private Header[] headers;

    public HttpResponseException(int errorcode, Header[] headers, String requestUrl,
            String errmsg) {
        super(requestUrl + " reason " + errmsg);
        this.errorcode = errorcode;
        this.errmsg = errmsg;
        this.headers = headers;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public Header[] getHeaders() {
        return headers;
    }

}
