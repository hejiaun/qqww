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

import java.security.MessageDigest;

/**
 * 数据摘要工具集
 *
 * @author Konlg
 */
public class DigestUtils {
    private static String DEF_CHARSET_NAME = "UTF-8";

    /**
     * 编码为 MD5 数据摘要
     *
     * @param data 输入数据 待做摘要处理的数据
     * @return byte [] 数据摘要
     * @throws Exception
     */
    public static byte[] encodeMD5(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(data);
        return md.digest();
    }

    /**
     * 编码为 MD5 数据摘要
     * 
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static byte[] encodeMD5(String data) throws Exception {
        return encodeMD5(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 MD5 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static byte[] encodeMD5(String data, String charsetName) throws Exception {
        return encodeMD5(data.getBytes(charsetName));
    }

    /**
     * 编码为 MD5 数据摘要
     * 
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static String encodeMD5Hex(String data) throws Exception {
        return encodeMD5Hex(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 MD5 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static String encodeMD5Hex(String data, String charsetName) throws Exception {
        return HexUtils.encodeHexString(encodeMD5(data, charsetName));
    }

    /**
     * 编码为 SHA-1 数据摘要
     *
     * @param data 输入数据 待做摘要处理的数据
     * @return byte [] 数据摘要
     * @throws Exception
     */
    public static byte[] encodeSHA(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.reset();
        md.update(data);
        return md.digest();
    }

    /**
     * 编码为 SHA-1 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA(String data) throws Exception {
        return encodeSHA(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-1 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA(String data, String charsetName) throws Exception {
        return encodeSHA(data.getBytes(charsetName));
    }

    /**
     * 编码为 SHA-1 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static String encodeSHAHex(String data) throws Exception {
        return encodeSHAHex(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-1 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static String encodeSHAHex(String data, String charsetName) throws Exception {
        return HexUtils.encodeHexString(encodeSHA(data, charsetName));
    }

    /**
     * 编码为 SHA-256 数据摘要
     *
     * @param data 输入数据 待做摘要处理的数据
     * @return byte [] 数据摘要
     * @throws Exception
     */
    public static byte[] encodeSHA256(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(data);
        return md.digest();
    }

    /**
     * 编码为 SHA-256 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA256(String data) throws Exception {
        return encodeSHA256(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-256 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA256(String data, String charsetName) throws Exception {
        return encodeSHA256(data.getBytes(charsetName));
    }

    /**
     * 编码为 SHA-256 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static String encodeSHA256Hex(String data) throws Exception {
        return encodeSHA256Hex(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-256 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static String encodeSHA256Hex(String data, String charsetName) throws Exception {
        return HexUtils.encodeHexString(encodeSHA256(data, charsetName));
    }

    /**
     * 编码为 SHA-384 数据摘要
     *
     * @param data 输入数据 待做摘要处理的数据
     * @return byte [] 数据摘要
     * @throws Exception
     */
    public static byte[] encodeSHA384(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        md.reset();
        md.update(data);
        return md.digest();
    }

    /**
     * 编码为 SHA-384 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA384(String data) throws Exception {
        return encodeSHA384(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-384 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA384(String data, String charsetName) throws Exception {
        return encodeSHA384(data.getBytes(charsetName));
    }

    /**
     * 编码为 SHA-384 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static String encodeSHA384Hex(String data) throws Exception {
        return encodeSHA384Hex(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-384 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static String encodeSHA384Hex(String data, String charsetName) throws Exception {
        return HexUtils.encodeHexString(encodeSHA384(data, charsetName));
    }

    /**
     * 编码为 SHA-512 数据摘要
     *
     * @param data 输入数据 待做摘要处理的数据
     * @return byte [] 数据摘要
     * @throws Exception
     */
    public static byte[] encodeSHA512(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        md.update(data);
        return md.digest();
    }

    /**
     * 编码为 SHA-512 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA512(String data) throws Exception {
        return encodeSHA512(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-512 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA512(String data, String charsetName) throws Exception {
        return encodeSHA512(data.getBytes(charsetName));
    }

    /**
     * 编码为 SHA-512 数据摘要
     *
     * @param data 输入数据
     * @return
     * @throws Exception
     */
    public static String encodeSHA512Hex(String data) throws Exception {
        return encodeSHA512Hex(data, DEF_CHARSET_NAME);
    }

    /**
     * 编码为 SHA-512 数据摘要
     *
     * @param data 输入数据
     * @param charsetName 编码名称
     * @return
     * @throws Exception
     */
    public static String encodeSHA512Hex(String data, String charsetName) throws Exception {
        return HexUtils.encodeHexString(encodeSHA512(data, charsetName));
    }
}
