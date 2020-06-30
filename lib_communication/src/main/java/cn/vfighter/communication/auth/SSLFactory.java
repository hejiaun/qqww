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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * SSL工厂，应当在系统启动时初始化 {@link #init(String, String, String, String, String)}
 * 
 * @author Konlg
 */
public enum SSLFactory {

    Factory;

    private static final String KEYSTORE_BKS = "BKS";

    /**
     * SSL 套接字工厂 <b>使用前请确保调用了
     * {@link #init(InputStream, String, InputStream, String, String)}
     * 方法对工厂进行过初始化</b>
     */
    SSLSocketFactory sslFactory;

    /**
     * 获取
     *
     * @return sslFactory xxx
     */
    public SSLSocketFactory getSSLFactory() {
        return sslFactory;
    }

    /**
     * 初始化
     * 
     * @param sslKeyStorePath 密钥
     * @param sslKeyStorePassword 密钥密码
     * @param sslTrustStore 可信密钥
     * @param sslTrustStorePassword 可信密钥密码
     * @param algorithm "sunx509"
     * @throws KeyStoreException 密钥仓库错误
     * @throws NoSuchAlgorithmException 算法错误
     * @throws CertificateException 验证错误
     * @throws FileNotFoundException 无仓库文件错误
     * @throws IOException 读取文件错误
     * @throws UnrecoverableKeyException 解密错误
     * @throws KeyManagementException 密钥管理错误
     */
    public void init(String sslKeyStorePath, String sslKeyStorePassword, String sslTrustStore,
            String sslTrustStorePassword, String algorithm)
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            FileNotFoundException, IOException, UnrecoverableKeyException, KeyManagementException {
        init(new FileInputStream(sslKeyStorePath), sslKeyStorePassword,
                new FileInputStream(sslTrustStore), sslTrustStorePassword, algorithm);
    }

    /**
     * 初始化
     * 
     * @param keystoreStream 密钥
     * @param sslKeyStorePassword 密钥密码
     * @param truststoreStream 可信密钥
     * @param sslTrustStorePassword 可信密钥密码
     * @param algorithm "sunx509"
     * @throws KeyStoreException 密钥仓库错误
     * @throws NoSuchAlgorithmException 算法错误
     * @throws CertificateException 验证错误
     * @throws IOException 读取文件错误
     * @throws UnrecoverableKeyException 解密错误
     * @throws KeyManagementException 密钥管理错误
     */
    @SuppressWarnings("deprecation")
    public void init(InputStream keystoreStream, String sslKeyStorePassword,
            InputStream truststoreStream, String sslTrustStorePassword, String algorithm)
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException,
            UnrecoverableKeyException, KeyManagementException {

        KeyStore kstore = KeyStore.getInstance(KEYSTORE_BKS);
        kstore.load(keystoreStream, sslKeyStorePassword.toCharArray());
        KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(algorithm);
        keyFactory.init(kstore, sslKeyStorePassword.toCharArray());

        KeyStore tstore = KeyStore.getInstance(KEYSTORE_BKS);
        tstore.load(truststoreStream, sslTrustStorePassword.toCharArray());

        sslFactory = new SSLSocketFactory(kstore, sslKeyStorePassword, tstore);
        sslFactory.setHostnameVerifier(new AllowAllHostnameVerifier());
    }
}
