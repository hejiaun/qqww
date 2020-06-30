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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.EndPointRouter.RouterMatchResult;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.util.DigestUtils;

/**
 * 终端授权加密工具
 * 
 * @author Konlg
 */
public class VFighterAuthUtil {
    private static final String SSL_ALGORITHM = "sunx509";

    /**
     * 初始化终端
     * 
     * @param sslKeyStorePath SSLkeyStore证书地址
     * @param sslTrustStore SSLTrustStore证书地址
     * @param sslPasswd SSL证书密码
     * @param endpoint 终端类型
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
     * @param endId 终端ID(Android: imei; ios: uuid)
     * @param secret 安全密钥
     * @throws IOException 密钥文件加载失败
     * @throws FileNotFoundException 密钥文件不存在
     * @throws CertificateException 认证失败
     * @throws NoSuchAlgorithmException SSL加密算法不被支持
     * @throws KeyStoreException 密钥信息错误
     * @throws KeyManagementException 密钥管理失败
     * @throws UnrecoverableKeyException 密钥不可重复使用
     * @throws IllegalAccessException 其他问题
     */
    public static void initialize(String sslKeyStorePath, String sslTrustStore, String sslPasswd,
            String endpoint, String endType, String endId, String secret, String apiVersion)
            throws Exception {
        boolean isauth = false;

        RouterMatchResult routeResult = EndPointRouter.get().append(endpoint);

        SSLFactory.Factory.init(sslKeyStorePath, sslPasswd, sslTrustStore, sslPasswd,
                SSL_ALGORITHM);

        isauth = authorize(endpoint, routeResult.namespace, endId, secret);
        if (isauth) {
            System.out.println("握手授权成功");
        }

        AuthClientContext.initialize(routeResult.namespace, endType, endId, isauth, apiVersion);
    }

    /**
     * 初始化终端
     * 
     * @param keystoreStream SSLkeyStore证书地址
     * @param truststoreStream SSLTrustStore证书地址
     * @param sslPasswd SSL证书密码
     * @param endpoint 终端类型
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
     * @param endId 终端ID(Android: imei; ios: uuid)
     * @param secret 安全密钥
     */
    public static void initialize(InputStream keystoreStream, InputStream truststoreStream,
            String sslPasswd, String endpoint, String endType, String endId, String secret,
            String apiVersion) {
        boolean isauth = false;

        RouterMatchResult routeResult = EndPointRouter.get().append(endpoint);

        try {
            SSLFactory.Factory.init(keystoreStream, sslPasswd, truststoreStream, sslPasswd,
                    SSL_ALGORITHM);

            isauth = authorize(endpoint, routeResult.namespace, endId, secret);
        } catch (Exception e) {
            // TODO 临时应对握手不成功，使用非加密通道的情况
            System.out.println(e.getMessage());
        }

        AuthClientContext.initialize(routeResult.namespace, endType, endId, isauth, apiVersion);
    }

    /**
     * 授权请求
     * 
     * @param endpoint 域名
     * @param appdomain 域名
     * @param endId 终端id
     * @param secret 安全密钥
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private static boolean authorize(String endpoint, String appdomain, String endId, String secret)
            throws Exception {
        boolean result = false;
        String sign = DigestUtils.encodeMD5Hex(appdomain + endId + secret, "UTF-8");
        AuthorizeExecutor exec = new AuthorizeExecutor(endpoint, appdomain, endId, sign,
                AuthClientContext.getEndType());
        try {
            ResponseSingle<AuthData> response = exec.execute();

            if (response.hasException()) {
                System.out.println(response.getException().getMessage());
                // 处理或向上抛出异常
                throw new RuntimeException(">>授权失败" + response.getException().getMessage());
            } else {
                AuthData authData = response.getData();
                if (authData != null) {
                    ClientAuthUtil.setTeaKey(authData.getAuthKey());
                    ClientAuthUtil.setTeaTimeout(authData.getTimeout());
                    result = true;
                }
            }
        } catch (ExecuteException e) {
            // TODO 临时应对握手不成功，使用非加密通道的情况
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
