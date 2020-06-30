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

import com.google.gson.Gson;

import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.client.JsonServiceClientExecutor;
import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * 安全授权请求执行器
 * 
 * @author Konlg
 */
public abstract class AuthRequestExecutor<P, T> extends JsonServiceClientExecutor<P, T> {
    private Header appdomain_header;
    private Header endtype_header;
    private Header endid_header;
    private Header authreq_header;

    public AuthRequestExecutor(String endpoint, P param) throws IllegalAccessException {
        super(endpoint, param);
        String domain = AuthClientContext.getAppDomain();
        if (domain == null || domain.length() == 0 || domain.trim().length() == 0) {
            domain = EndPointRouter.get().append(endpoint).namespace;
        }
        appdomain_header = new BasicHeader(HeaderConstants.HEADER_APPDOMAIN, domain);
        endtype_header = new BasicHeader(HeaderConstants.HEADER_ENDTYPE,
                AuthClientContext.getEndType());
        endid_header = new BasicHeader(HeaderConstants.HEADER_ENDID, AuthClientContext.getEndId());
        authreq_header = new BasicHeader(HeaderConstants.HEADER_AUTHREQ, "1");
    }

    @Override
    protected abstract Type getResultType();

    /**
     * 获取GSON工厂实例
     * 
     * @return Gson
     */
    protected Gson gson() {
        return GsonFactory.SingleTon.create();
    }

    @Override
    protected HttpEntity post() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException {
        HttpResponseResult result = createAgent().post(endpoint, jsontext(), appdomain_header,
                endid_header, endtype_header, authreq_header);
        return result.getEntity();
    }

    @Override
    protected HttpServiceAgent createAgent() throws HttpClientException {
        HttpServiceAgent agent = HttpClientFactory.get().create();
        agent.setSSLSocketFactory(SSLFactory.Factory.sslFactory);
        return agent;
    }
}
