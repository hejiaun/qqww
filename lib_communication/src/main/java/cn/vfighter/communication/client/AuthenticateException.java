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

package cn.vfighter.communication.client;

/**
 * Json service Authenticate failed
 * 
 * @author Konlg
 */
public class AuthenticateException extends ExecuteException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8894037428024829864L;

    public AuthenticateException() {
        super();
    }

    public AuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticateException(String message) {
        super(message);
    }

    public AuthenticateException(Throwable cause) {
        super(cause);
    }

}
