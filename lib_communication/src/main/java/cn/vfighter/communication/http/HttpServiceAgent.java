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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

import cn.vfighter.ICloseable;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.log.LoggerUtils;

/**
 * HttpService的客户端代理
 * 
 * @author konlg
 */
@SuppressWarnings("deprecation")
public class HttpServiceAgent {

    /**
     * HTTP端口
     */
    static final int PORT_HTTP = 80;

    /**
     * HTTPS端口
     */
    static final int PORT_HTTPS = 443;

    /**
     * HTTP内容MIME类型
     */
    static final String HEADER_CONTENT_TYPE = "Content-Type";

    /**
     * 相应实体长度
     */
    static final String HEADER_CONTENT_RANGE = "Content-Range";

    /**
     * 内容编码
     */
    static final String HEADER_CONTENT_ENCODING = "Content-Encoding";

    /**
     * MIME功能扩展, 指示代理如何处理附加文件。比如下载文件的文件名
     */
    static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

    /**
     * 客户端代理能支持的编码
     */
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    /**
     * 语言支持
     */
    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    /**
     * 客户端支持GZIP的内容
     */
    static final String ENCODING_GZIP = "gzip";

    /**
     * 默认并发连接数上限
     */
    static final int DEFAULT_MAX_CONNECTIONS = 1000;

    /**
     * 请求超时时限
     */
    private static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;

    /**
     * 请求失败重试次数上限
     */
    private static final int DEFAULT_MAX_RETRIES = 5;

    /**
     * 请求失败重试时间间隔
     */
    private static final int DEFAULT_RETRY_SLEEP_TIME_MILLIS = 500;

    /**
     * 通讯数据缓冲池长度
     */
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;

    /**
     * 请求成功
     */
    private static final int HTTP_OK = 200;

    /**
     * 默认HTTP 用户代理名称
     */
    public static final String DEFAULT_USER_AGENT = "Android Http Client/Xingfu";

    /**
     * 安全套接字HTTP协议
     */
    private static final String SCHEME_HTTPS = "https";

    /**
     * 请求超时时限
     */
    private int timeout = DEFAULT_SOCKET_TIMEOUT;

    /**
     * HttpClient 设置实例
     */
    AbstractHttpClient httpClient;

    /**
     * HttpContext 上下文实例
     */
    HttpContext httpContext;

    /**
     * 发送请求前接受添加报文头的临时保存区
     */
    Map<String, String> clientHeaderMap;

    /**
     * 是否要对请求地址URL进行编码
     */
    private boolean enableUrlEncoding = true;

    /**
     * 本地语种
     */
    private Locale language;

    /**
     * Creates a new HttpServiceAgent with default constructor arguments values
     * 
     * @throws CertificateException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws KeyManagementException
     */
    HttpServiceAgent() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException,
            CertificateException {
    }

    /**
     * 实例一个HttpService客户端
     * 
     * @param httpPort HTTP 端口
     * @throws KeyManagementException 安全键异常
     * @throws UnrecoverableKeyException 安全键异常
     * @throws KeyStoreException 安全键异常
     * @throws CertificateException 认证异常
     */
    HttpServiceAgent(int httpPort) throws KeyManagementException, UnrecoverableKeyException,
            KeyStoreException, CertificateException {
        this(false, httpPort, PORT_HTTPS);
    }

    /**
     * 实例一个HttpService客户端
     * 
     * @param httpPort HTTP 端口
     * @param httpsPort HTTPS端口
     * @throws KeyManagementException 安全键异常
     * @throws UnrecoverableKeyException 安全键异常
     * @throws KeyStoreException 安全键异常
     * @throws CertificateException 认证异常
     */
    HttpServiceAgent(int httpPort, int httpsPort) throws KeyManagementException,
            UnrecoverableKeyException, KeyStoreException, CertificateException {
        this(false, httpPort, httpsPort);
    }

    /**
     * 实例一个HttpService客户端
     * 
     * @param trustAll <code>true</code>信任所有站点
     * @param httpPort HTTP 端口
     * @param httpsPort HTTPS端口
     * @throws KeyManagementException 安全键异常
     * @throws UnrecoverableKeyException 安全键异常
     * @throws KeyStoreException 安全键异常
     * @throws CertificateException 认证异常
     */
    HttpServiceAgent(boolean trustAll, int httpPort, int httpsPort) throws KeyManagementException,
            UnrecoverableKeyException, KeyStoreException, CertificateException {
        init(getDefaultSchemeRegistry(trustAll, httpPort, httpsPort), DEFAULT_MAX_CONNECTIONS);
    }

    /**
     * 构建实例 设置连接超时为 {@value #DEFAULT_SOCKET_TIMEOUT} 设置连接池最大并发数为
     * {@value #DEFAULT_MAX_CONNECTIONS} 使用 {@link HttpVersion#HTTP_1_1} 支持
     * {@link #ENCODING_GZIP}
     * 
     * @param schemeRegistry Scheme 参数
     * @param maxConnections 连接池并发数
     * @return
     */
    void init(SchemeRegistry schemeRegistry, int maxConnections) {
        BasicHttpParams httpParams = new BasicHttpParams();
        // 字符集
        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
        ConnManagerParams.setTimeout(httpParams, timeout);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,
                new ConnPerRouteBean(maxConnections));
        ConnManagerParams.setMaxTotalConnections(httpParams, maxConnections + 1);

        HttpConnectionParams.setSoTimeout(httpParams, timeout * 5);
        HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams, DEFAULT_SOCKET_BUFFER_SIZE);

        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(httpParams, DEFAULT_USER_AGENT);

        ThreadSafeClientConnManager connManager = new ThreadSafeClientConnManager(httpParams,
                schemeRegistry);

        clientHeaderMap = new HashMap<String, String>();

        httpContext = new SyncBasicHttpContext(new BasicHttpContext());

        httpClient = new DefaultHttpClient(connManager, httpParams);
        httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) {
                if (!request.containsHeader(HEADER_ACCEPT_ENCODING)) {
                    request.addHeader(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
                }
                for (String header : clientHeaderMap.keySet()) {
                    request.addHeader(header, clientHeaderMap.get(header));
                }
            }
        });

        httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
            @Override
            public void process(HttpResponse response, HttpContext context) {
                final HttpEntity entity = response.getEntity();
                if (entity == null) {
                    return;
                }
                final Header encoding = entity.getContentEncoding();
                if (encoding != null) {
                    for (HeaderElement element : encoding.getElements()) {
                        if (element.getName().equalsIgnoreCase(ENCODING_GZIP)) {
                            response.setEntity(new InflatingEntity(entity));
                            break;
                        }
                    }
                }
            }
        });

        httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(final HttpRequest request, final HttpContext context)
                    throws HttpException, IOException {
                AuthState authState = (AuthState) context
                        .getAttribute(ClientContext.TARGET_AUTH_STATE);
                if (authState == null)
                    return;
                if (authState.getAuthScheme() == null) {
                    CredentialsProvider credsProvider = (CredentialsProvider) context
                            .getAttribute(ClientContext.CREDS_PROVIDER);
                    HttpHost targetHost = (HttpHost) context
                            .getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                    AuthScope authScope = new AuthScope(targetHost.getHostName(),
                            targetHost.getPort());
                    Credentials creds = credsProvider.getCredentials(authScope);
                    if (creds != null) {
                        authState.setAuthScheme(new BasicScheme());
                        authState.setCredentials(creds);
                    }
                }
            }
        }, 0);

        httpClient.setHttpRequestRetryHandler(
                new RetryHandler(DEFAULT_MAX_RETRIES, DEFAULT_RETRY_SLEEP_TIME_MILLIS));
    }

    /**
     * 构建实例 设置连接超时为 {@value #DEFAULT_SOCKET_TIMEOUT} 设置连接池最大并发数为
     * {@value #DEFAULT_MAX_CONNECTIONS} 使用 {@link HttpVersion#HTTP_1_1} 支持
     * {@link #ENCODING_GZIP}
     * 
     * @param schemeRegistry Scheme 参数
     * @param maxConnections 连接池并发数
     * @return
     */
    void init(SchemeRegistry schemeRegistry, int maxConnections, int timeoutParam) {
        BasicHttpParams httpParams = new BasicHttpParams();
        // 字符集
        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
        ConnManagerParams.setTimeout(httpParams, timeoutParam);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,
                new ConnPerRouteBean(maxConnections));
        ConnManagerParams.setMaxTotalConnections(httpParams, maxConnections + 1);

        HttpConnectionParams.setSoTimeout(httpParams, timeoutParam);
        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutParam);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams, DEFAULT_SOCKET_BUFFER_SIZE);

        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(httpParams, DEFAULT_USER_AGENT);

        ThreadSafeClientConnManager connManager = new ThreadSafeClientConnManager(httpParams,
                schemeRegistry);

        clientHeaderMap = new HashMap<String, String>();

        httpContext = new SyncBasicHttpContext(new BasicHttpContext());

        httpClient = new DefaultHttpClient(connManager, httpParams);
        httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) {
                if (!request.containsHeader(HEADER_ACCEPT_ENCODING)) {
                    request.addHeader(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
                }
                for (String header : clientHeaderMap.keySet()) {
                    request.addHeader(header, clientHeaderMap.get(header));
                }
            }
        });

        httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
            @Override
            public void process(HttpResponse response, HttpContext context) {
                final HttpEntity entity = response.getEntity();
                if (entity == null) {
                    return;
                }
                final Header encoding = entity.getContentEncoding();
                if (encoding != null) {
                    for (HeaderElement element : encoding.getElements()) {
                        if (element.getName().equalsIgnoreCase(ENCODING_GZIP)) {
                            response.setEntity(new InflatingEntity(entity));
                            break;
                        }
                    }
                }
            }
        });

        httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(final HttpRequest request, final HttpContext context)
                    throws HttpException, IOException {
                AuthState authState = (AuthState) context
                        .getAttribute(ClientContext.TARGET_AUTH_STATE);
                if (authState == null)
                    return;
                if (authState.getAuthScheme() == null) {
                    CredentialsProvider credsProvider = (CredentialsProvider) context
                            .getAttribute(ClientContext.CREDS_PROVIDER);
                    HttpHost targetHost = (HttpHost) context
                            .getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                    AuthScope authScope = new AuthScope(targetHost.getHostName(),
                            targetHost.getPort());
                    Credentials creds = credsProvider.getCredentials(authScope);
                    if (creds != null) {
                        authState.setAuthScheme(new BasicScheme());
                        authState.setCredentials(creds);
                    }
                }
            }
        }, 0);

        httpClient.setHttpRequestRetryHandler(
                new RetryHandler(DEFAULT_MAX_RETRIES, DEFAULT_RETRY_SLEEP_TIME_MILLIS));
    }

    /**
     * Cookie存储仓库
     * 
     * @param cookieStore Cookie存储仓库
     */
    public void setCookieStore(CookieStore cookieStore) {
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }

    public CookieStore getCookiesStore() {
        return (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
    }

    /**
     * 启用或禁止重定向的简单设置。如果在底层的 {@link HttpClient}中直接设置了 {@link RedirectHandler}
     * ，那么本方法的设置内容将不起作用。
     * <p>
     * &nbsp;
     * </p>
     * 默认设置是禁用重定向
     * 
     * @param enableRedirects boolean 是否启用重定向
     * @param enableRelativeRedirects boolean 是否启用相对地址重定向
     * @param enableCircularRedirects boolean 是否允许循环重定向（指回地址本身）
     */
    public void setEnableRedirects(final boolean enableRedirects,
            final boolean enableRelativeRedirects, final boolean enableCircularRedirects) {
        httpClient.getParams().setBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT,
                !enableRelativeRedirects);
        httpClient.getParams().setBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
                enableCircularRedirects);
        httpClient.setRedirectHandler(new SimpleRedirectHandler(enableRedirects));
    }

    /**
     * 同 {@link #setEnableRedirects(boolean, boolean, boolean)} 默认启用循环重定向
     * 
     * @param enableRedirects boolean 是否启用重定向
     * @param enableRelativeRedirects boolean 是否启用相对地址重定向
     * @see #setEnableRedirects(boolean, boolean, boolean)
     */
    public void setEnableRedirects(final boolean enableRedirects,
            final boolean enableRelativeRedirects) {
        setEnableRedirects(enableRedirects, enableRelativeRedirects, true);
    }

    /**
     * 是否启用重定向
     * 
     * @param enableRedirects boolean 是否允许所有重定向类型
     * @see #setEnableRedirects(boolean, boolean, boolean)
     */
    public void setEnableRedirects(final boolean enableRedirects) {
        setEnableRedirects(enableRedirects, enableRedirects, enableRedirects);
    }

    /**
     * 设置重定向处理句柄的具体实现实例
     * 
     * @param customRedirectHandler 重定向处理句柄
     */
    public void setRedirectHandler(final RedirectHandler customRedirectHandler) {
        httpClient.setRedirectHandler(customRedirectHandler);
    }

    /**
     * 用户代理名称，默认使用 {@value #DEFAULT_USER_AGENT}
     * 
     * @param userAgent 用于设置 User-Agent header 的名称
     */
    public void setUserAgent(String userAgent) {
        HttpProtocolParams.setUserAgent(this.httpClient.getParams(), userAgent);
    }

    /**
     * 最大并发连接数
     * 
     * @return 最大并发连接数
     */
    public int getMaxConnections() {
        return ConnManagerParams.getMaxTotalConnections(httpClient.getParams());
    }

    /**
     * SOCKET连接超时时限，默认是 10000 (10sec)
     * 
     * @return SOCKET连接超时时限，单位毫秒
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * SOCKET连接超时时限
     * 
     * @param timeout SOCKET连接超时时限，最小为1秒
     * @return 连接超时时限
     */
    public HttpServiceAgent setTimeout(int timeout) {
        if (timeout < 1000)
            timeout = DEFAULT_SOCKET_TIMEOUT;
        this.timeout = timeout;
        final HttpParams httpParams = this.httpClient.getParams();
        ConnManagerParams.setTimeout(httpParams, this.timeout);
        HttpConnectionParams.setSoTimeout(httpParams, this.timeout);
        HttpConnectionParams.setConnectionTimeout(httpParams, this.timeout);
        return this;
    }

    /**
     * 代理服务器和端口
     * 
     * @param hostname 代理服务器 (IP or DNS name)
     * @param port 端口. -1表示使用SCHEME的默认端口.
     */
    public void setProxy(String hostname, int port) {
        final HttpHost proxy = new HttpHost(hostname, port);
        final HttpParams httpParams = this.httpClient.getParams();
        httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    }

    /**
     * 设置代理服务器和端口，以及用户名和密码
     * 
     * @param hostname 代理服务器 (IP or DNS name)
     * @param port 端口. -1表示使用SCHEME的默认端口.
     * @param username 用户名
     * @param password 密码
     */
    public void setProxy(String hostname, int port, String username, String password) {
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, port),
                new UsernamePasswordCredentials(username, password));
        final HttpHost proxy = new HttpHost(hostname, port);
        final HttpParams httpParams = this.httpClient.getParams();
        httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    }

    /**
     * 安全套接字工厂，在发起请求时用于生产安全套接字。
     * 
     * @param sslSocketFactory 安全套接字工厂，用于生产HTTPS请求
     */
    public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.httpClient.getConnectionManager().getSchemeRegistry()
                .register(new Scheme(SCHEME_HTTPS, sslSocketFactory, PORT_HTTPS));
    }

    /**
     * 设置重试的次数限制，以及重试的间隔时间
     * 
     * @param retries 每个请求允许重试的次数限制
     * @param timeout 每次重试的间隔时间，单位毫秒
     */
    public void setMaxRetriesAndTimeout(int retries, int timeout) {
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(retries, timeout));
    }

    /**
     * 是否使用URL编码
     * 
     * @param enableUrlEncoding <code>true</code>使用
     */
    public void setEnableUrlEncoding(boolean enableUrlEncoding) {
        this.enableUrlEncoding = enableUrlEncoding;
    }

    /**
     * 是否使用URL编码
     * 
     * @return <code>true</code>使用
     */
    public boolean isEnableUrlEncoding() {
        return enableUrlEncoding;
    }

    /**
     * 清空HTTP HEADER设置表。
     */
    public void removeAllHeaders() {
        clientHeaderMap.clear();
    }

    /**
     * 添加一个HTTP HEADER设置，这些设置将在下一次发起请求时起效
     * 
     * @param header 报文头名称
     * @param value 报文头内容
     */
    public void addHeader(String header, String value) {
        clientHeaderMap.put(header, value);
    }

    /**
     * 删除一个报文头设置项，将在下一次发起请求时起效
     * 
     * @param header 报文头名称
     */
    public void removeHeader(String header) {
        clientHeaderMap.remove(header);
    }

    /**
     * 设置基础鉴权的用户名和密码。 Basic Authentication。 等效于
     * {@link #setBasicAuth(String, String, AuthScope)} 其中 {@link AuthScope#ANY}
     * 
     * @param username 基础鉴权用户名
     * @param password 基础鉴权密码
     */
    public void setBasicAuth(String username, String password) {
        setBasicAuth(username, password, false);
    }

    /**
     * 设置基础鉴权的用户名和密码。 Basic Authentication。 等效于
     * {@link #setBasicAuth(String, String, AuthScope)} 其中 {@link AuthScope#ANY}
     * 
     * @param username 基础鉴权用户名
     * @param password 基础鉴权密码
     * @param preemtive 是否使用对人类友好的方式， <code>true</code> 是
     */
    public void setBasicAuth(String username, String password, boolean preemtive) {
        setBasicAuth(username, password, null, preemtive);
    }

    /**
     * 设置基础鉴权的用户名和密码。 可以使用指定的安全鉴权域。例如 setBasicAuth("username","password", new
     * AuthScope("host",port,AuthScope.ANY_REALM))
     * 
     * @param username 基础鉴权用户名
     * @param password 基础鉴权密码
     * @param scope - an AuthScope object
     */
    public void setBasicAuth(String username, String password, AuthScope scope) {
        setBasicAuth(username, password, scope, false);
    }

    /**
     * 设置基础鉴权的用户名和密码。 可以使用指定的安全鉴权域。例如 setBasicAuth("username","password", new
     * AuthScope("host",port,AuthScope.ANY_REALM))
     * 
     * @param username 基础鉴权用户名
     * @param password 基础鉴权密码
     * @param scope - an AuthScope object
     * @param preemtive 是否使用对人类友好的方式， <code>true</code> 是
     */
    public void setBasicAuth(String username, String password, AuthScope scope, boolean preemtive) {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username,
                password);
        this.httpClient.getCredentialsProvider()
                .setCredentials(scope == null ? AuthScope.ANY : scope, credentials);
        setAuthenticationPreemptive(preemtive);
    }

    /**
     * 设置处理鉴权的 {@link HttpRequestInterceptor}的优先方式。 也可以调用
     * `AsyncHttpClient.addHeader("Authorization", "Basic
     * base64OfUsernameAndPassword==")`来实现
     * 
     * @param isPreemtive whether the authorization is processed in preemtive
     *            way
     */
    public void setAuthenticationPreemptive(boolean isPreemtive) {
        if (isPreemtive) {
            httpClient.addRequestInterceptor(new PreemptiveAuthorizationHttpRequestInterceptor(),
                    0);
        } else {
            httpClient.removeRequestInterceptorByClass(
                    PreemptiveAuthorizationHttpRequestInterceptor.class);
        }
    }

    /**
     * 删除之前设置的基础鉴权凭证
     */
    public void clearBasicAuth() {
        this.httpClient.getCredentialsProvider().clear();
    }

    /**
     * HTTP HEAD 只请求报文头内容 执行一次HEAD请求。 无任何参数
     * 
     * @param url 被请求的地址
     * @return 结果
     * @throws IOException 读取数据错误
     * @throws ClientProtocolException 协议错误
     * @throws HttpResponseException 服务器响应错误
     */
    public HttpResponseResult head(String url)
            throws ClientProtocolException, IOException, HttpResponseException {
        return head(url, null);
    }

    /**
     * HTTP HEAD 只请求报文头内容
     * 
     * @param url 被请求的地址
     * @param params 请求参数
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult head(String url, RequestParams params)
            throws ClientProtocolException, IOException, HttpResponseException {
        return sendRequest(httpClient, httpContext,
                new HttpHead(getUrlWithQueryString(enableUrlEncoding, url, params)), null);
    }

    /**
     * HTTP HEAD 只请求报文头内容
     * 
     * @param url 被请求的地址
     * @param headers 请求的报文头内容
     * @param params 请求参数
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult head(String url, Header[] headers, RequestParams params)
            throws ClientProtocolException, IOException, HttpResponseException {
        HttpUriRequest request = new HttpHead(
                getUrlWithQueryString(enableUrlEncoding, url, params));
        if (headers != null)
            request.setHeaders(headers);
        return sendRequest(httpClient, httpContext, request, null);
    }

    /**
     * 执行HTTP GET 请求，无任何参数
     * 
     * @param url 被请求的地址
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult get(String url)
            throws ClientProtocolException, IOException, HttpResponseException {
        return get(url, null);
    }

    /**
     * 执行HTTP GET 请求
     * 
     * @param url 被请求的地址
     * @param params 请求参数
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult get(String url, RequestParams params)
            throws ClientProtocolException, IOException, HttpResponseException {
        return sendRequest(httpClient, httpContext,
                new HttpGet(getUrlWithQueryString(enableUrlEncoding, url, params)), null);
    }

    /**
     * 执行HTTP GET 请求
     * 
     * @param url 被请求的地址
     * @param headers 请求的报文头内容
     * @param params 请求参数
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult get(String url, Header[] headers, RequestParams params)
            throws ClientProtocolException, IOException, HttpResponseException {
        HttpUriRequest request = new HttpGet(getUrlWithQueryString(enableUrlEncoding, url, params));
        if (headers != null)
            request.setHeaders(headers);
        return sendRequest(httpClient, httpContext, request, null);
    }

    /**
     * 执行HTTP POST请求
     * 
     * @param url 被请求的地址
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(String url)
            throws ClientProtocolException, IOException, HttpResponseException {
        return sendRequest(httpClient, httpContext, new HttpPost(url), null);
    }

    /**
     * 发起一个 JSON 实体请求
     * 
     * @param url 目标地址
     * @param json JSON实体序列化字符串
     * @param headers 附加的报文头
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(String url, String json, Header... headers)
            throws ClientProtocolException, IOException, HttpResponseException {
        return post(url, new StringEntity(json, HTTP.UTF_8), MimeType.APPLICATION_JSON, headers);
    }

    /**
     * upload mutil file to server
     * 
     * @param endpoint request url of server
     * @param entity post entity with mutil fields
     * @param headers 附加的报文头
     * @return if success ,return a HttResponseResult
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult multiPostUpload(String endpoint, HttpEntity entity, Header... headers)
            throws ClientProtocolException, IOException, HttpResponseException {

        HttpPost post = new HttpPost(endpoint);
        post.setEntity(entity);
        if (headers != null) {
            for (Header header : headers) {
                post.addHeader(header);
            }
        }
        HttpResponse response = httpClient.execute(post, httpContext);
        StatusLine status = response.getStatusLine();
        if (HTTP_OK != status.getStatusCode()) {
            String msg = status.getReasonPhrase();
            throw new HttpResponseException(status.getStatusCode(), response.getAllHeaders(),
                    endpoint, msg);
        }
        return new HttpResponseResult(response);
    }

    /**
     * POST 请求
     * 
     * @param url 目标地址
     * @param entity 实体
     * @param contentType 内容MIME类型
     * @return 响应对象
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(String url, HttpEntity entity, String contentType)
            throws ClientProtocolException, IOException, HttpResponseException {
        return sendRequest(httpClient, httpContext,
                addEntityToRequestBase(new HttpPost(URI.create(url).normalize()), entity),
                contentType);
    }

    /**
     * 执行HTTP POST请求
     * 
     * @param url 被请求的地址
     * @param params 请求参数
     * @param progressHandler 进度处理器，可为 <code>null</code>
     * @param contentType 内容MIME类型
     * @return 结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(String url, RequestParams params,
            ProgressHandlerInterface progressHandler, String contentType)
            throws ClientProtocolException, IOException, HttpResponseException {
        return post(url, paramsToEntity(params, progressHandler), contentType);
    }

    public HttpResponseResult post(String url, Header[] headers, MimeParams params,
            String contentType, ProgressHandlerInterface progressHandler)
            throws ClientProtocolException, IOException, HttpResponseException {
        HttpEntityEnclosingRequestBase request = new HttpPost(URI.create(url).normalize());
        if (params != null) {
            HttpEntity entity = params.createEntity(progressHandler);
            request.setEntity(entity);
        }
        if (headers != null)
            request.setHeaders(headers);
        return sendRequest(httpClient, httpContext, request, contentType);
    }

    /**
     * 发起一个POST 请求
     * 
     * @param url 目标地址
     * @param headers 报文头内容
     * @param params 参数对象
     * @param contentType MIME类型 如 {@link RequestParams#APPLICATION_JSON}
     * @param progressHandler 进度句柄
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(String url, Header[] headers, RequestParams params,
            String contentType, ProgressHandlerInterface progressHandler)
            throws ClientProtocolException, IOException, HttpResponseException {
        HttpEntityEnclosingRequestBase request = new HttpPost(URI.create(url).normalize());
        if (params != null) {
            request.setEntity(paramsToEntity(params, progressHandler));
        }
        if (headers != null)
            request.setHeaders(headers);
        return sendRequest(httpClient, httpContext, request, contentType);
    }

    public HttpResponseResult post(String url, HttpEntity entity, String contentType,
            Header... headers) throws ClientProtocolException, IOException, HttpResponseException {
        HttpUriRequest request = addEntityToRequestBase(new HttpPost(URI.create(url).normalize()),
                entity);
        if (headers != null) {
            for (Header header : headers)
                request.addHeader(header);
        }
        return sendRequest(httpClient, httpContext, request, contentType);
    }

    /**
     * 执行一个POST请求
     * 
     * @param url 目标地址
     * @param headers 报文头内容
     * @param entity 请求实体
     * @param contentType MIME类型
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(String url, Header[] headers, HttpEntity entity,
            String contentType) throws ClientProtocolException, IOException, HttpResponseException {
        HttpEntityEnclosingRequestBase request = addEntityToRequestBase(
                new HttpPost(URI.create(url).normalize()), entity);
        if (headers != null)
            request.setHeaders(headers);
        return sendRequest(httpClient, httpContext, request, contentType);
    }

    /**
     * 构建一个可以关闭的长请求 获得的结果，可以用于调用
     * {@link #post(ICloseable, Header[], HttpEntity, String)}，执行请求
     * 
     * @param url 请求地址
     * @param timeout 超时时间
     * @return 可以关闭的长请求
     * @throws MalformedURLException url规范错误
     */
    public ICloseable createCloseableRequest(String url, int timeout) throws MalformedURLException {
        return new PacketHttpClient(url, timeout);
    }

    /**
     * 执行可关闭的Post请求
     * 
     * @param closeable {@link #createCloseableRequest(String, int)}的实例
     * @param headers 请求头
     * @param entity 请求内容
     * @param contentType 内容类型
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult post(ICloseable closeable, Header[] headers, HttpEntity entity,
            String contentType) throws ClientProtocolException, IOException, HttpResponseException {
        PacketHttpClient pc = PacketHttpClient.class.cast(closeable);
        return pc.post((InputStreamEntity) entity, headers, contentType);
    }

    /**
     * 发起一个HTTP PUT 请求
     * 
     * @param url 目标地址
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult put(String url)
            throws ClientProtocolException, IOException, HttpResponseException {
        return put(url, null, null);
    }

    /**
     * 发起一个HTTP PUT 请求
     * 
     * @param url 目标地址
     * @param entity 请求实体
     * @param contentType MIME类型
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult put(String url, HttpEntity entity, String contentType)
            throws ClientProtocolException, IOException, HttpResponseException {
        return sendRequest(httpClient, httpContext,
                addEntityToRequestBase(new HttpPut(URI.create(url).normalize()), entity),
                contentType);
    }

    /**
     * 执行HTTP PUT 请求
     * 
     * @param url 目标地址
     * @param headers 报文头内容
     * @param entity 请求实体
     * @param contentType MIME类型 如 {@link RequestParams#APPLICATION_JSON}
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult put(String url, Header[] headers, HttpEntity entity,
            String contentType) throws ClientProtocolException, IOException, HttpResponseException {
        HttpEntityEnclosingRequestBase request = addEntityToRequestBase(
                new HttpPut(URI.create(url).normalize()), entity);
        if (headers != null)
            request.setHeaders(headers);
        return sendRequest(httpClient, httpContext, request, contentType);
    }

    /**
     * 执行一次 HTTP DELETE 请求
     * 
     * @param url 目标地址
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult delete(String url)
            throws ClientProtocolException, IOException, HttpResponseException {
        final HttpDelete delete = new HttpDelete(URI.create(url).normalize());
        return sendRequest(httpClient, httpContext, delete, null);
    }

    /**
     * 执行一次 HTTP DELETE 请求
     * 
     * @param url 目标地址
     * @param headers 报文头内容
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult delete(String url, Header[] headers)
            throws ClientProtocolException, IOException, HttpResponseException {
        final HttpDelete delete = new HttpDelete(URI.create(url).normalize());
        if (headers != null)
            delete.setHeaders(headers);
        return sendRequest(httpClient, httpContext, delete, null);
    }

    /**
     * 执行一次 HTTP DELETE 请求
     * 
     * @param url 目标地址
     * @param headers 报文头内容
     * @param params 请求参数对象
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    public HttpResponseResult delete(String url, Header[] headers, RequestParams params)
            throws ClientProtocolException, IOException, HttpResponseException {
        HttpDelete httpDelete = new HttpDelete(
                getUrlWithQueryString(enableUrlEncoding, url, params));
        if (headers != null)
            httpDelete.setHeaders(headers);
        return sendRequest(httpClient, httpContext, httpDelete, null);
    }

    /**
     * 发送请求
     * 
     * @param client 执行请求的 HttpClient
     * @param httpContext http上下文
     * @param uriRequest 请求实体
     * @param contentType 内容类型,可以为 <code>null</code>
     * @return 响应结果
     * @throws ClientProtocolException 协议错误
     * @throws IOException 读取数据错误
     * @throws HttpResponseException 服务端响应错误
     */
    protected HttpResponseResult sendRequest(AbstractHttpClient client, HttpContext httpContext,
            HttpUriRequest uriRequest, String contentType)
            throws ClientProtocolException, IOException, HttpResponseException {
        if (contentType != null) {
            uriRequest.addHeader(HEADER_CONTENT_TYPE, contentType);
        }

        // String localeStr = null;
        Locale tmpLocale;
        if (language == null) {
            tmpLocale = Locale.getDefault();
        } else {
            tmpLocale = language;
        }

        // StringBuffer buffer = new StringBuffer();
        // buffer.append(tmpLocale.getLanguage().toLowerCase());
        // String country = tmpLocale.getCountry();
        // if(country !=null && !StringUtils.isEmpty(country)){
        // buffer.append("-").append(country.toLowerCase());
        // }
        //
        // localeStr = buffer.toString();
        // Log.w(TAG, "request language: " +
        // localeStr+"local.tostring():"+tmpLocale.toString());
        uriRequest.addHeader(HEADER_ACCEPT_LANGUAGE, tmpLocale.toString());

        // Fixes #115
        if (uriRequest.getURI().getScheme() == null) {
            // subclass of IOException so processed in the caller
            throw new MalformedURLException("No valid URI scheme was provided");
        }

        HttpResponse response = client.execute(uriRequest, httpContext);
        StatusLine status = response.getStatusLine();
        LoggerUtils.get()
                .info("request URL: " + uriRequest.getURI() + " status:" + status.getStatusCode());
        if (HTTP_OK != status.getStatusCode()) {
            String msg = status.getReasonPhrase();
            throw new HttpResponseException(status.getStatusCode(), response.getAllHeaders(),
                    uriRequest.getURI().toString(), msg);
        }
        return new HttpResponseResult(response);
    }

    /**
     * 仅仅适用于扩展自 {@link HttpEntityEnclosingRequestBase}的方法，例如，不适用于DELELTE
     * 
     * @param requestBase 请求方法的实例，不能为 <code>null</code>
     * @param entity 发起请求的请求实体
     * @return 请求结果
     */
    private HttpEntityEnclosingRequestBase addEntityToRequestBase(
            HttpEntityEnclosingRequestBase requestBase, HttpEntity entity) {
        if (entity != null) {
            requestBase.setEntity(entity);
        }
        return requestBase;
    }

    /**
     * 将请求参数对象进行URL编码（如果启用），添加到QueryString上
     * 
     * @param shouldEncodeUrl 是否要URL编码
     * @param url 目标地址
     * @param params 请求参数
     * @return 添加了请求参数后的URL
     */
    private static String getUrlWithQueryString(boolean shouldEncodeUrl, String url,
            RequestParams params) {
        if (url == null)
            return null;

        if (shouldEncodeUrl)
            url = url.replace(" ", "%20");

        if (params != null) {
            // Construct the query string and trim it, in case it
            // includes any excessive white spaces.
            String paramString = params.getParamString().trim();

            // Only add the query string if it isn't empty and it
            // isn't equal to '?'.
            if (!paramString.equals("") && !paramString.equals("?")) {
                url += url.contains("?") ? "&" : "?";
                url += paramString;
            }
        }

        return url;
    }

    /**
     * 默认的HTTP协议控制实例
     * 
     * @param trustAll <code>true</code>信任所有站点。Fix to SSL flaw in API < ICS
     * @param httpPort HTTP端口
     * @param httpsPort HTTPS端口
     * @return HTTP协议控制实例
     * @throws CertificateException 鉴权错误
     * @throws KeyStoreException 密钥仓库错误
     * @throws UnrecoverableKeyException 解密错误
     * @throws KeyManagementException 密钥管理错误
     * @See https://code.google.com/p/android/issues/detail?id=13117
     */
    static SchemeRegistry getDefaultSchemeRegistry(boolean trustAll, int httpPort, int httpsPort)
            throws KeyManagementException, UnrecoverableKeyException, KeyStoreException,
            CertificateException {
        if (trustAll) {
            System.out.println(
                    "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }

        if (httpPort < 1) {
            httpPort = 80;
        }

        if (httpsPort < 1) {
            httpsPort = 443;
        }

        SSLSocketFactory sslSocketFactory;
        if (trustAll)
            sslSocketFactory = SimpleSSLSocketFactory.getFixedSocketFactory();
        else
            sslSocketFactory = SSLSocketFactory.getSocketFactory();

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry
                .register(new Scheme("http", PlainSocketFactory.getSocketFactory(), httpPort));
        schemeRegistry.register(new Scheme("https", sslSocketFactory, httpsPort));

        return schemeRegistry;
    }

    /**
     * 封装一个GZIP的流，解码得到数据，用于访问实体内容
     * 
     * @author sam.pan
     */
    static class InflatingEntity extends HttpEntityWrapper {

        public InflatingEntity(HttpEntity wrapped) {
            super(wrapped);
        }

        InputStream wrappedStream;
        PushbackInputStream pushbackStream;
        GZIPInputStream gzippedStream;

        @Override
        public InputStream getContent() throws IOException {
            wrappedStream = wrappedEntity.getContent();
            pushbackStream = new PushbackInputStream(wrappedStream, 2);
            if (isInputStreamGZIPCompressed(pushbackStream)) {
                gzippedStream = new GZIPInputStream(pushbackStream);
                return gzippedStream;
            } else {
                return pushbackStream;
            }
        }

        @Override
        public long getContentLength() {
            return -1;
        }

        @Override
        public void consumeContent() throws IOException {
            silentCloseInputStream(wrappedStream);
            silentCloseInputStream(pushbackStream);
            silentCloseInputStream(gzippedStream);
            super.consumeContent();
        }

        private void silentCloseInputStream(InputStream stream) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }

        private boolean isInputStreamGZIPCompressed(final PushbackInputStream inputStream)
                throws IOException {
            if (inputStream == null)
                return false;

            byte[] signature = new byte[2];
            int readStatus = inputStream.read(signature);
            inputStream.unread(signature);
            int streamHeader = ((int) signature[0] & 0xff) | ((signature[1] << 8) & 0xff00);
            return readStatus == 2 && GZIPInputStream.GZIP_MAGIC == streamHeader;
        }
    }

    /**
     * 将参数对象转换为请求实体的方法，支持进度处理句柄
     * 
     * @param params 待转换的参数对象
     * @param progressHandler 进度处理句柄
     * @return 请求实体
     */
    private HttpEntity paramsToEntity(RequestParams params,
            ProgressHandlerInterface progressHandler) {
        HttpEntity entity = null;
        try {
            if (params != null) {
                entity = params.getEntity(progressHandler);
            }
        } catch (IOException e) {
            if (progressHandler != null) {
                progressHandler.sendFailureMessage(0, null, null, e);
            } else {
                e.printStackTrace();
            }
        }
        return entity;
    }

    /**
     * 强制规定语种，默认情况下使用客户端默认的语种（对于手机，可以通过手机的setting来设置）
     * 
     * @param language 2015年2月15日-上午11:14:14
     */
    void setLanguage(Locale language) {
        this.language = language;
    }

    /**
     * 便利方法，不抛出异常，关闭一个 {@link InputStream}
     * 
     * @param is 待关闭的流
     */
    public static void silentCloseInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            System.out.println("silentCloseInputStream Cannot close input stream");
        }
    }

    /**
     * 便利方法，不抛出异常，关闭一个 {@link OutputStream}
     * 
     * @param os 待关闭的流
     */
    public static void silentCloseOutputStream(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            System.out.println("silentCloseOutputStream Cannot close output stream");
        }
    }
}
