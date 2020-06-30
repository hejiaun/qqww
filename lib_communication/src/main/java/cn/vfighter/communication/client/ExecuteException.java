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
 * Json service excute failed
 * 
 * @author Konlg
 */
public class ExecuteException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8894037428024829863L;

    public ExecuteException() {
        super();
    }

    public ExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecuteException(String message) {
        super(message);
    }

    public ExecuteException(Throwable cause) {
        super(cause);
    }

}
