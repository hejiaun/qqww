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
import java.lang.reflect.Type;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;

import vfighter.android.util.Log;
import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.client.JsonServiceClientExecutor;
import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;

/**
 * 使用SSL的安全客户端执行器
 * 
 * @author Konlg
 */
public abstract class SSLClientExecutor<P, T> extends JsonServiceClientExecutor<P, T> {

    private Header namespaceheader;
    private Header endtype_header;

    /**
     * 使用SSL的安全客户端执行器
     * 
     * @param endpoint 端点
     * @param param 参数
     * @param appdomain 域名
     * @param endtype 终端类型
     */
    public SSLClientExecutor(String endpoint, P param, String appdomain, String endtype) {
        super(EndPointRouter.get().append(endpoint).endpoint, param);
        String domain = AuthClientContext.getAppDomain();
        if (domain == null || domain.length() == 0 || domain.trim().length() == 0) {
            domain = EndPointRouter.get().append(endpoint).namespace;
        }
        namespaceheader = new BasicHeader(HeaderConstants.HEADER_APPDOMAIN, appdomain);
        endtype_header = new BasicHeader(HeaderConstants.HEADER_ENDTYPE, endtype);
    }

    @Override
    protected abstract Type getResultType();

    @Override
    protected HttpEntity post() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException {
        Log.w("", "endpoint:" + endpoint + " jsonText:" + jsontext());
        HttpResponseResult result = createAgent().post(endpoint, jsontext(), namespaceheader,
                endtype_header);
        return result.getEntity();
    }

    @Override
    protected HttpServiceAgent createAgent() throws HttpClientException {
        HttpServiceAgent agent = HttpClientFactory.get().create();
        agent.setSSLSocketFactory(SSLFactory.Factory.sslFactory);
        return agent;
    }
}
