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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 客户端上下文对象接口
 * 
 * @author Konlg
 */
public class AuthClientContext {
    /**
     * 客户端ID计数器
     */
    private static final AtomicInteger cidCounter = new AtomicInteger(0);
    private static final byte cflag = 122;

    /**
     * 单例实现
     *
     * @author Konlg
     */
    private enum Singleton {
        instance;
        AuthClientContext context;

        private Singleton() {
            context = new AuthClientContext();
        }
    }

    private AuthClientContext() {
    }

    /**
     * 获取会话上下文实例
     * 
     * @return
     */
    private static final AuthClientContext getInstance() {
        return Singleton.instance.context;
    }

    private String appDomain;
    private String endType;
    private String endId;
    private String sessionId;
    private boolean useEncrypt;
    private String userId;
    private String apiVersion;

    /**
     * 初始化
     * 
     * @param appdomain 应用域
     * @param endType 终端类型
     *            <ul>
     *            <li>internal：0</li>
     *            <li>HTML5：H</li>
     *            <li>Adnroid：A</li>
     *            <li>IOS：I</li>
     *            <li>PC Web：W</li>
     *            <li>PC Client：C</li>
     *            <li>Third party platform: T
     *            <ul>
     *            <li>Weixin: TW</li>
     *            <li>Alipay: TA</li>
     *            </ul>
     *            </li>
     *            </ul>
     * @param endId 终端ID
     * @param useEncrypt <code>true</code>加密
     */
    public static void initialize(String appdomain, String endType, String endId,
            boolean useEncrypt, String apiVersion) {
        getInstance().appDomain = appdomain;
        getInstance().endType = endType;
        getInstance().endId = endId;
        getInstance().useEncrypt = useEncrypt;
        getInstance().apiVersion = apiVersion;
    }

    /**
     * 初始化
     * 
     * @param endType 终端类型
     *            <ul>
     *            <li>internal：0</li>
     *            <li>HTML5：H</li>
     *            <li>Adnroid：A</li>
     *            <li>IOS：I</li>
     *            <li>PC Web：W</li>
     *            <li>PC Client：C</li>
     *            <li>Third party platform: T
     *            <ul>
     *            <li>Weixin: TW</li>
     *            <li>Alipay: TA</li>
     *            </ul>
     *            </li>
     *            </ul>
     * @param endId 终端ID
     */
    public static void initialize(String endType, String endId) {
        getInstance().endType = endType;
        getInstance().endId = endId;
    }

    /**
     * 初始化
     * 
     * @param endType 终端类型
     *            <ul>
     *            <li>internal：0</li>
     *            <li>HTML5：H</li>
     *            <li>Adnroid：A</li>
     *            <li>IOS：I</li>
     *            <li>PC Web：W</li>
     *            <li>PC Client：C</li>
     *            <li>Third party platform: T
     *            <ul>
     *            <li>Weixin: TW</li>
     *            <li>Alipay: TA</li>
     *            </ul>
     *            </li>
     *            </ul>
     */
    public static void initialize(String endType) {
        getInstance().endType = endType;
        getInstance().endId = createCID(cflag);
    }

    /**
     * 创建客户端编号
     * 
     * @param flag 标识
     * @return 客户端ID
     */
    public static String createCID(byte flag) {
        long nextSid = 0;
        nextSid = (System.currentTimeMillis() << 24) >>> 8;
        nextSid = nextSid | ((long) (flag) << 55);

        int value = 0;
        if ((value = cidCounter.incrementAndGet()) > 32767) {
            cidCounter.set(0);
        }

        nextSid = nextSid | value;
        return nextSid + "";
    }

    // location 资源定位

    /**
     * 获取 命名空间
     *
     * @return appdomain 应用域
     */
    public static String getAppDomain() {
        return getInstance().appDomain;
    }

    /**
     * 设置 命名空间
     *
     * @param appdomain 要设置的 应用域
     */
    public static void setAppDomain(String appdomain) {
        getInstance().appDomain = appdomain;
    }

    /**
     * 获取 终端类型
     *
     * @return endType 终端类型
     *         <ul>
     *         <li>internal：0</li>
     *         <li>HTML5：H</li>
     *         <li>Adnroid：A</li>
     *         <li>IOS：I</li>
     *         <li>PC Web：W</li>
     *         <li>PC Client：C</li>
     *         <li>Third party platform: T
     *         <ul>
     *         <li>Weixin: TW</li>
     *         <li>Alipay: TA</li>
     *         </ul>
     *         </li>
     *         </ul>
     */
    public static String getEndType() {
        return getInstance().endType;
    }

    /**
     * 设置 终端类型
     *
     * @param endType 要设置的 终端类型
     *            <ul>
     *            <li>internal：0</li>
     *            <li>HTML5：H</li>
     *            <li>Adnroid：A</li>
     *            <li>IOS：I</li>
     *            <li>PC Web：W</li>
     *            <li>PC Client：C</li>
     *            <li>Third party platform: T
     *            <ul>
     *            <li>Weixin: TW</li>
     *            <li>Alipay: TA</li>
     *            </ul>
     *            </li>
     *            </ul>
     */
    public static void setEndType(String endType) {
        getInstance().endType = endType;
    }

    /**
     * 获取 终端ID
     *
     * @return endId 终端ID 请保证该ID单设备唯一
     */
    public static String getEndId() {
        return getInstance().endId;
    }

    /**
     * 设置 终端ID
     *
     * @param endId 要设置的 终端ID 请保证该ID单设备唯一
     */
    public static void setEndId(String endId) {
        getInstance().endId = endId;
    }

    /**
     * 获取 是否使用加密
     * 
     * @return useEncrypt 否使用加密
     */
    public static boolean useEncrypt() {
        return getInstance().useEncrypt;
    }

    /**
     * 设置 否使用加密
     * 
     * @param useEncrypt 要设置的 否使用加密
     */
    public static void setEncrypt(boolean useEncrypt) {
        getInstance().useEncrypt = useEncrypt;
    }

    /**
     * 获取 会话ID
     * 
     * @return SessionId 会话ID
     */
    public static String getSessionId() {
        return getInstance().sessionId;
    }

    /**
     * 设置 会话ID
     * 
     * @param sessionId 要设置的 会话ID
     */
    public static void setSessionId(String sessionId) {
        getInstance().sessionId = sessionId;
    }

    /**
     * 获取 用户ID
     * 
     * @return userId 用户ID
     */
    public static String getUserId() {
        return getInstance().userId;
    }

    /**
     * 设置 用户ID
     * 
     * @param userId 要设置的 用户ID
     */
    public static void setUserId(String userId) {
        getInstance().userId = userId;
    }

    public static String getApiVersion() {
        return getInstance().apiVersion;
    }

}
