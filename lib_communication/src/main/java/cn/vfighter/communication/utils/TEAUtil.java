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
import java.util.Arrays;

/**
 * TEA 加解密工具
 * 
 * @author Konlg
 */
public class TEAUtil {
    private static final int DELTA = 0x9e3779b9;
    private static final int DE_SUM = 0xC6EF3720;

    /**
     * 转换为无符号整数
     * 
     * @param in 输入的字节
     * @return
     */
    private static int transform(byte in) {
        int tempInt = (int) in;
        if (tempInt < 0) {
            tempInt += 256;
        }
        return tempInt;
    }

    /**
     * 计算XOR密钥
     * 
     * @param teakey
     * @return
     */
    private static byte[] sumxorkey(int[] teakey) {
        int sum = 0;
        for (int key : teakey) {
            sum += key;
        }
        return Integer.toString(sum).getBytes(Charset.forName("UTF-8"));
    }

    /**
     * 字节数组转换为整数数组
     * 
     * @param content 字节数组内容
     * @param offset 起始偏移量
     * @return
     */
    public static int[] bytesToInts(byte[] content, int offset) {
        int[] result = new int[content.length >> 2];
        for (int i = 0, j = offset; j < content.length; i++, j += 4) {
            result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8
                    | transform(content[j + 1]) << 16 | (int) content[j] << 24;
        }
        return result;
    }

    /**
     * 整数数组转换为字节数组
     * 
     * @param content 整数数组内容
     * @param offset 起始偏移量
     * @return
     */
    public static byte[] intsToBytes(int[] content, int offset) {
        byte[] result = new byte[content.length << 2];
        for (int i = 0, j = offset; j < result.length; i++, j += 4) {
            result[j + 3] = (byte) (content[i] & 0xff);
            result[j + 2] = (byte) ((content[i] >> 8) & 0xff);
            result[j + 1] = (byte) ((content[i] >> 16) & 0xff);
            result[j] = (byte) ((content[i] >> 24) & 0xff);
        }
        return result;
    }

    private static byte[] checkData(byte[] data) {
        int len = data.length;
        if (len % 4 > 0) {
            len = (len / 4 + 1) * 4;
            data = Arrays.copyOf(data, len);
        }
        return data;
    }

    /**
     * TEA加密
     * 
     * @param content 待加密的字节码内容
     * @param offset 起始偏移量
     * @param key 加密key
     * @param times 轮数：16，32，64
     * @return 加密后的字节码内容
     */
    public static byte[] encrypt(byte[] content, int offset, int[] key, int times) {
        int len = content.length;
        byte[] keyBytes = sumxorkey(key);

        int[] tempInt = bytesToInts(checkData(XORUtil.codec(content, keyBytes)), offset);
        int y = tempInt[0], z = tempInt[1], sum = 0, i;
        int a = key[0], b = key[1], c = key[2], d = key[3];

        for (i = 0; i < times; i++) {
            sum += DELTA;
            y += ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
            z += ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
        }
        tempInt[0] = y;
        tempInt[1] = z;
        return Arrays.copyOf(intsToBytes(tempInt, 0), len);
    }

    /**
     * TEA解密
     * 
     * @param content 待解密内容
     * @param offset 起始偏移量
     * @param key 解密key
     * @param times 轮数：16，32，64
     * @return 解密后的字节码内容
     */
    public static byte[] decrypt(byte[] content, int offset, int[] key, int times) {
        int len = content.length;
        byte[] keyBytes = sumxorkey(key);

        int[] tempInt = bytesToInts(checkData(content), offset);
        int y = tempInt[0], z = tempInt[1], sum = DE_SUM, i;
        int a = key[0], b = key[1], c = key[2], d = key[3];

        for (i = 0; i < times; i++) {
            z -= ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
            y -= ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
            sum -= DELTA;
        }
        tempInt[0] = y;
        tempInt[1] = z;

        return Arrays.copyOf(XORUtil.codec(intsToBytes(tempInt, 0), keyBytes), len);
    }
}
