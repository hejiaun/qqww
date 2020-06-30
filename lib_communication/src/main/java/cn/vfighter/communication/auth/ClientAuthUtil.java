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

import java.io.IOException;

import vfighter.android.util.Log;
import cn.vfighter.communication.utils.TEAUtil;

/**
 * 客户端授权加密工具
 * 
 * @author Konlg
 */
public class ClientAuthUtil {

    private static final String TAG = "ClientAuthUtil";
    private static final int TEA_TIME = 32;

    private static volatile int[] teaKey;
    private static volatile long teaTimeout;

    /**
     * 获取TEA Key
     * 
     * @return Key
     */
    public static int[] getTeaKey() {
        return teaKey;
    }

    /**
     * 设置TEA Key
     * 
     * @param teaKey Key
     */
    public static void setTeaKey(int[] teaKey) {
        ClientAuthUtil.teaKey = teaKey;
    }

    /**
     * TEA KEY有效时间
     * 
     * @param timeout 有效时间
     */
    public static void setTeaTimeout(int timeout) {
        teaTimeout = System.currentTimeMillis() + timeout;
        Log.w(TAG, "setTealTimeOut:" + timeout + " toal:" + teaTimeout);
    }

    /**
     * TEA KEY是否超时
     * 
     * @return <code>true</code>超时
     */
    public static boolean isTeaTimeout() {
        return System.currentTimeMillis() > (teaTimeout - 10 * 60 * 1000);
    }

    /**
     * 解密数据
     * 
     * @param data 待解密的数据
     * @return 解密结果
     * @throws IOException 读取数据异常
     */
    public static byte[] decrypt(byte[] data) throws IOException {
        if (teaKey == null) {
            throw new IOException("teaKey");
        }

        return TEAUtil.decrypt(data, 0, teaKey, TEA_TIME);
    }

    /**
     * 加密数据
     * 
     * @param data 代加密数据
     * @return 加密后的数据
     * @throws IOException 读取数据异常
     */
    public static byte[] encrypt(byte[] data) throws IOException {
        if (teaKey == null) {
            throw new IOException("teaKey is null");
        }

        return TEAUtil.encrypt(data, 0, teaKey, TEA_TIME);
    }

}
