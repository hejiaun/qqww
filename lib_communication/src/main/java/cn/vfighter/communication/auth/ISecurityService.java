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
 * 安全认证服务接口
 * 
 * @author Konlg
 */
public interface ISecurityService extends IDecrypt {

    /**
     * 终端握手认证
     * 
     * @param appdomain 应用域名
     * @param endId 终端唯一ID
     * @param sign 校验码
     * @return 鉴权结果
     * @throws Exception 发生错误
     */
    IAuthInfo authorize(String appdomain, String endId, String sign) throws Exception;

    /**
     * 检测是否已经授权
     * 
     * @param appdomain 应用域名
     * @param endId 终端唯一ID
     * @return <code>true</code> 已经授权
     * @throws Exception 发生错误
     */
    boolean isAuthorized(String appdomain, String endId) throws Exception;

    /**
     * 获取超时剩余时间
     * 
     * @param appdomain 应用域名
     * @param endId 终端唯一ID
     * @return 剩余时间
     * @throws Exception 发生错误
     */
    int timeout(String appdomain, String endId) throws Exception;

    /**
     * 响应编码
     * 
     * @param authKey 密钥
     * @param input 待加密数据
     * @return 加密后结果
     * @throws Exception 发生错误
     */
    byte[] encrypt(String authKey, byte[] input) throws Exception;

    /**
     * 获取分发给终端的加解密 KEY
     * 
     * @param appdomain 应用域名
     * @param endId 终端唯一ID
     * @return key
     * @throws Exception 发生错误
     */
    int[] getAuthKey(String appdomain, String endId) throws Exception;
}
