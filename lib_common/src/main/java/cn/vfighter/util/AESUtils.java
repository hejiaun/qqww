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

package cn.vfighter.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密工具。
 *
 * @author Konlg
 */
public class AESUtils {
    public static final byte[] DEFAULT_AESKEY = { -96, 66, 74, 96, -30, 120, -77, -118, -110, 2,
            -105, 42, -22, -10, 108, 125 };

    /**
     * AES加密
     * 
     * @param value 要被加密的字符串。
     * @return
     * @throws Exception
     */
    public static final String encrypt(String value) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(DEFAULT_AESKEY, "AES"));

        byte[] buffer = cipher.doFinal(value.getBytes("utf-8"));

        return Base64.encode(buffer);
    }

    /**
     * AES加密。
     * 
     * @param str 要被加密的字符串。
     * @param key 密钥, 长度必须为16。
     * @return 加密后的字符串
     * @throws Exception
     */
    public static final String encrypt(String str, String key) throws Exception {

        if (str == null || key == null)
            return null;

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));

        byte[] buffer = cipher.doFinal(str.getBytes("utf-8"));

        return Base64.encode(buffer);
    }

    /**
     * AES解密
     * 
     * @param value 要被解密的字符串。
     * @return
     * @throws Exception
     */
    public static final String decrypt(String value) throws Exception {

        byte[] buffer = Base64.decode(value);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(DEFAULT_AESKEY, "AES"));
        buffer = cipher.doFinal(buffer);

        return new String(buffer, "utf-8");
    }

    /**
     * AES解密。
     * 
     * @param str 要被解密的字符串。
     * @param key 密钥, 长度必须为16。
     * @return 解密后的字符串。
     * @throws Exception
     */
    public static final String decrypt(String str, String key) throws Exception {

        if (str == null || key == null)
            return null;

        byte[] buffer = Base64.decode(str);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        buffer = cipher.doFinal(buffer);

        return new String(buffer, "utf-8");
    }
}
