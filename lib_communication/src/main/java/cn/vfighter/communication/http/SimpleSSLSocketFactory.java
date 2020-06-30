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

package cn.vfighter.communication.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * 安全套接字简易工厂 仅仅用于调试目的，不适合用于生产环境
 * 
 * @author konlg
 */
@SuppressWarnings({ "deprecation" })
public class SimpleSSLSocketFactory extends SSLSocketFactory {

    /**
     * HTTP 协议名称
     */
    private static final String SCHEME_HTTP = "http";

    /**
     * HTTPS 协议名称
     */
    private static final String SCHEME_HTTPS = "https";

    /**
     * 认证加密方式 x.509
     */
    private static final String CERTIFICATE_TYPE_X509 = "X.509";

    private static final String CERTIFICATE_ALIAS = "ca";

    /**
     * 加密通讯上下文
     */
    SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS);

    /**
     * 使用给定的密钥仓库，创建新的安全套接字工厂
     * 
     * @param truststore 密钥仓库
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws KeyManagementException KeyManagementException
     * @throws KeyStoreException KeyStoreException
     * @throws UnrecoverableKeyException UnrecoverableKeyException
     */
    public SimpleSSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException,
            KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(truststore);

        X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sslContext.init(null, new TrustManager[] { tm }, null);
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
            throws IOException {
        return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    @Override
    public Socket createSocket() throws IOException {
        return sslContext.getSocketFactory().createSocket();
    }

    /**
     * 使用给定的密钥仓库，配置 {@link HttpsURLConnection}
     */
    public void fixHttpsURLConnection() {
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }

    /**
     * 为指定的证书建立密钥工厂
     * 
     * @param cert 证书
     * @return KeyStore 密钥仓库
     * @throws CertificateException 验证失败
     * @throws KeyStoreException 仓库势必啊
     */
    public static KeyStore getKeystoreOfCA(InputStream cert)
            throws CertificateException, KeyStoreException {
        InputStream caInput = null;
        Certificate ca = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance(CERTIFICATE_TYPE_X509);
            caInput = new BufferedInputStream(cert);
            ca = cf.generateCertificate(caInput);
        } finally {
            try {
                if (caInput != null) {
                    caInput.close();
                }
            } catch (IOException e) {
                System.err.println("close certificate buffer failed.\n" + e.getMessage());
            }
        }

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = null;
        keyStore = KeyStore.getInstance(keyStoreType);
        try {
            keyStore.load(null, null);
            keyStore.setCertificateEntry(CERTIFICATE_ALIAS, ca);
        } catch (IOException e) {
            throw new KeyStoreException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new KeyStoreException(e);
        }
        return keyStore;
    }

    /**
     * 获取默认的密钥仓库
     * 
     * @return 仓库
     * @throws KeyStoreException 仓库失败
     * @throws CertificateException 验证失败
     */
    public static KeyStore getKeystore() throws KeyStoreException, CertificateException {
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
        } catch (IOException e) {
            throw new KeyStoreException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new KeyStoreException(e);
        }
        return trustStore;
    }

    /**
     * 信任所有证书的安全套接字工厂
     * 
     * @return 安全套接字工厂
     * @throws KeyStoreException 仓库失败
     * @throws KeyManagementException 密钥管理错误
     * @throws UnrecoverableKeyException 解密失败
     * @throws CertificateException 验证失败
     */
    public static SSLSocketFactory getFixedSocketFactory() throws KeyStoreException,
            KeyManagementException, UnrecoverableKeyException, CertificateException {
        SSLSocketFactory socketFactory;
        try {
            socketFactory = new SimpleSSLSocketFactory(getKeystore());
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (NoSuchAlgorithmException e) {
            throw new KeyStoreException(e);
        }
        return socketFactory;
    }

    /**
     * 信任指定证书的 {@link DefaultHttpClient}
     * 
     * @param keyStore 证书密钥仓库
     * @return
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    public static DefaultHttpClient getNewHttpClient(KeyStore keyStore)
            throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException {
        SSLSocketFactory factory = new SimpleSSLSocketFactory(keyStore);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme(SCHEME_HTTP, PlainSocketFactory.getSocketFactory(),
                HttpServiceAgent.PORT_HTTP));
        registry.register(new Scheme(SCHEME_HTTPS, factory, HttpServiceAgent.PORT_HTTPS));

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

        return new DefaultHttpClient(ccm, params);
    }

}
