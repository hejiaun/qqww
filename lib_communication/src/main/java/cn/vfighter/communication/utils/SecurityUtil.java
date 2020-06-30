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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 安全工具
 * 
 * @author Konlg
 */
public class SecurityUtil {
    private static Random random = new Random(System.currentTimeMillis());

    private static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(data);
        return md.digest();
    }

    private static String encodeMD5X16(String data, String encoding)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes = encodeMD5(data.getBytes(encoding));
        StringBuilder sha1StrBuff = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (Integer.toHexString(0xFF & bytes[i]).length() == 1)
                sha1StrBuff.append("0").append(Integer.toHexString(0xFF & bytes[i]));
            else
                sha1StrBuff.append(Integer.toHexString(0xFF & bytes[i]));
        }
        return sha1StrBuff.toString();
    }

    private static byte[] encodeSHA256(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(data);
        // 执行消息摘要
        return md.digest();
    }

    private static String getRandomKey() {
        String result = "";

        for (int i = 0; i < 6; i++) {
            result += (char) (random.nextInt(90) + 33);
        }

        return result;
    }

    /**
     * 获取TEA密钥数据
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] getTeaKeyData(String str) throws Exception {
        return encodeSHA256((str + getRandomKey()).getBytes("UTF-8"));
    }

    /**
     * 获取权限key
     * 
     * @param appId 应用ID
     * @param endId 终端ID
     * @return
     * @throws Exception
     */
    public static String getAuthSessionKey(String appId, String endId) throws Exception {
        return encodeMD5X16(appId + endId, "UTF-8");
    }
}
