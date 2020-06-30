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

/**
 * HTTP 客户端工厂错误
 * 
 * @author konlg
 */
public class HttpClientException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3697402875999892770L;

    public HttpClientException() {
        super();
    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

}
