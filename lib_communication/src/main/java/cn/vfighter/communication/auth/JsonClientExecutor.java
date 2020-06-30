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

import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.client.JsonServiceClientExecutor;
import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpResponseResult;

/**
 * Json客户端执行器
 * 
 * @author Konlg
 */
public abstract class JsonClientExecutor<P, T> extends JsonServiceClientExecutor<P, T> {
    private Header endtype_header;

    /**
     * 创建一个Json客户端执行器
     * 
     * @param endpoint 端点
     * @param param 请求参数
     * @throws IllegalAccessException 参数错误
     */
    public JsonClientExecutor(String endpoint, P param) throws IllegalAccessException {
        this(endpoint, param, AuthClientContext.getAppDomain(), AuthClientContext.getEndType());
    }

    /**
     * 创建一个Json客户端执行器
     * 
     * @param endpoint 端点
     * @param param 请求参数
     * @param appdomain 所属业务域
     * @param endtype 加密类型
     * @throws IllegalAccessException 参数错误
     */
    public JsonClientExecutor(String endpoint, P param, String appdomain, String endtype)
            throws IllegalAccessException {
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
        HttpResponseResult result = createAgent().post(endpoint, jsontext(), namespaceheader,
                endtype_header);
        return result.getEntity();
    }

}
