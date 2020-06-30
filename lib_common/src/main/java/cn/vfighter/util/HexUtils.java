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

/**
 * HEX字符串编码工具类
 * 
 * @author konlg
 */
public class HexUtils {
    /** 小写可用字符 */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    /** 大写可用字符 */
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 将二进制数据转换为HEX字符串
     * 
     * @param data
     * @param toDigits
     * @return
     */
    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    /**
     * 将二进制数据转换为HEX字符串
     * 
     * @param data 要转换的数据
     * @return
     */
    public static String encodeHexString(final byte[] data) {
        return new String(encodeHex(data, true));
    }

    /**
     * 将二进制数据转换为HEX字符串
     * 
     * @param data 要转换的数据
     * @param toLowerCase 使用小写
     * @return
     */
    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }
}
