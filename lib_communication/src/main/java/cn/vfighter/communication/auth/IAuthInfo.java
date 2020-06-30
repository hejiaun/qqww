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

/**
 * 认证信息
 * 
 * @author Konlg
 */
public interface IAuthInfo {
    /**
     * 获取 加解密key, Base64解码后使用
     *
     * @return teaCodecKey TEA 加解密key
     */
    String getAuthCodecKey();

    /**
     * 获取 认证超时时间
     *
     * @return timeout 认证超时时间
     */
    int getTimeout();
}
