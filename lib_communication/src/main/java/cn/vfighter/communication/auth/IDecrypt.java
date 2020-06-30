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

import java.io.InputStream;

/**
 * 解密支持接口
 * 
 * @author Konlg
 */
public interface IDecrypt {
    /**
     * 解密
     * 
     * @param appdomain 所属业务域
     * @param endId 终端ID
     * @param stream 待解密的输入
     * @return 解密结果
     * @throws Exception 发生错误
     */
    byte[] decrypt(String appdomain, String endId, InputStream stream) throws Exception;
}
