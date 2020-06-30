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

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 * 人类友好的鉴权处理拦截器
 * 
 * @author konlg
 */
public class PreemptiveAuthorizationHttpRequestInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(final HttpRequest request, final HttpContext context)
            throws HttpException, IOException {
        AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
        if (authState == null)
            return;
        if (authState.getAuthScheme() == null) {
            CredentialsProvider credsProvider = (CredentialsProvider) context
                    .getAttribute(ClientContext.CREDS_PROVIDER);
            HttpHost targetHost = (HttpHost) context
                    .getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
            Credentials creds = credsProvider.getCredentials(authScope);
            if (creds != null) {
                authState.setAuthScheme(new BasicScheme());
                authState.setCredentials(creds);
            }
        }
    }

}
