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

package cn.vfighter.communication.auth;

import java.io.Serializable;

/**
 * 会话键接口
 * 
 * @author konlg
 */
public interface ISessionKey extends Serializable {

    /**
     * 获取安全会话key
     *
     * @return authKey xxx
     */
    String getSessionKey();

    /**
     * 设置安全会话key
     *
     * @param authKey 要设置的 安全会话key
     */
    void setSessionKey(String authKey);
}
