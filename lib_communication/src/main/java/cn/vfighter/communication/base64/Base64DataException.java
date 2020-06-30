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

package cn.vfighter.communication.base64;

import java.io.IOException;

/**
 * Base64编码或解码错误
 * 
 * @author Konlg
 */
public class Base64DataException extends IOException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4536059049301767112L;

    public Base64DataException(String detailMessage) {
        super(detailMessage);
    }
}