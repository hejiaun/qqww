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

import org.apache.http.client.CookieStore;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Locale;

/**
 * HTTP 客户端工厂 生产 HttpServiceAgent实例 初始化时可使用
 * {@link #setCookieStore(CookieStore)}设置具体的Cookie仓库实例。
 *
 * @author konlg
 */
public class HttpClientFactory {

    /**
     * 单例实现
     *
     * @author konlg
     */
    static enum Singleton {

        INSTANC;

        private HttpClientFactory instance;

        private Singleton() {
            instance = new HttpClientFactory();
            try {
                instance.agent = new HttpServiceAgent();
            } catch (Exception e) {
                throw new RuntimeException("single ton instance HttpClientFactory failure.", e);
            }
        }
    }

    /**
     * 工厂单例实例
     *
     * @return 工厂单例实例
     */
    public static HttpClientFactory get() {
        return Singleton.INSTANC.instance;
    }

    private boolean inited;

    public HttpClientFactory onCreate(int maxConnection) throws KeyManagementException,
            UnrecoverableKeyException, KeyStoreException, CertificateException {
        synchronized (agent) {
            agent.init(HttpServiceAgent.getDefaultSchemeRegistry(true, HttpServiceAgent.PORT_HTTP,
                    HttpServiceAgent.PORT_HTTPS), maxConnection);
            inited = true;
        }
        return this;
    }

    HttpServiceAgent agent;

    private HttpClientFactory() {
    }

    /**
     * 生产一个 HttpServiceAgent 如果设置了Cookie仓库，则将该仓库实例设置给生产的HttpServiceAgent实例
     *
     * @return HttpServiceAgent实例
     */
    public HttpServiceAgent create() {
        if (!inited) {
            synchronized (agent) {
                if (!inited) {
                    try {
                        agent.init(
                                HttpServiceAgent.getDefaultSchemeRegistry(true,
                                        HttpServiceAgent.PORT_HTTP, HttpServiceAgent.PORT_HTTPS),
                                HttpServiceAgent.DEFAULT_MAX_CONNECTIONS);
                        inited = true;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return agent;
    }

    /**
     * 注册,生产一个 {@link PersistentCookieStore}的cookie实例
     *
     * @param cookiestore cookie实例
     */
    public final void setCookieStore(CookieStore cookiestore) {
        create().setCookieStore(cookiestore);
    }

    /**
     * 强制规定语种，默认情况下使用客户端默认的语种（对于手机，可以通过手机的setting来设置）
     *
     * @param locale 2015年2月15日-上午11:16:00
     */
    public final void setLanguage(Locale locale) {
        create().setLanguage(locale);
    }
}
