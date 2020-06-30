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

package cn.vfighter.communication.utils;

import java.nio.charset.Charset;

/**
 * 异或加密工具
 * 
 * @author Konlg
 */
public class XORUtil {
    private final static Charset utf8 = Charset.forName("UTF-8");

    /**
     * 异或加解密
     * 
     * @param data 要加密的数据
     * @param key 表示密钥的字符串
     * @return
     */
    public static byte[] codec(byte[] data, String key) {
        return codec(data, key.getBytes(utf8));
    }

    /**
     * 异或加解密
     * 
     * @param data 要加密的数据
     * @param keyBytes 表示密钥的字节数组
     * @return
     */
    public static byte[] codec(byte[] data, byte[] keyBytes) {
        for (int i = 0, size = data.length; i < size; i++) {
            for (byte keyBytes0 : keyBytes) {
                data[i] = (byte) (data[i] ^ keyBytes0);
            }
        }
        return data;
    }

}
