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

package cn.vfighter.communication.client;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.net.ssl.SSLException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.EntityUtilsHC4;

import com.google.gson.Gson;

import vfighter.android.util.Log;
import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.EndPointRouter.RouterMatchResult;
import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * JSON service client executor 本类默认采用 {@link GsonFactory}对返回的请求内容进行JSON反序列化。
 * 具体实现类可以通过重载 {@link #consume(HttpEntity)}来提供自己的反序列化方式。
 * 
 * @author Konlg
 * @param <P> 参数-发送给后台服务接口的请求参数
 * @param <T> 结果-后台服务接口返回的结果类型
 */
public abstract class JsonServiceClientExecutor<P, T> implements IExecutor<T> {

    private static final String TAG = "JsonServiceClientExecutor";

    /**
     * 接入域名 Header 字段名
     */
    public static final String HEADER_NS = "AS_NS";
    /**
     * 标识安卓机型 字段名
     */
    public static final String HEADER_TYPE = "ClientType";

    /**
     * 标识安卓机型报文头值
     */
    protected static final String type_value = "0";
    protected final String endpoint;
    protected Header namespaceheader;
    protected Header typeHeader;

    protected P param;

    private String jsontext;

    /**
     * 构造一个JSON over HTTP 的执行器 使用 XFEndpoint 提供 ENDPOINT 地址
     * 
     * @param param
     */
    public JsonServiceClientExecutor(P param) {
        RouterMatchResult router = EndPointRouter.get().append(this);
        this.endpoint = router.endpoint;
        namespaceheader = new BasicHeader(HEADER_NS, router.namespace);
        typeHeader = new BasicHeader(HEADER_TYPE, type_value);
        this.param = param;
    }

    /**
     * 构造一个JSON over HTTP 的执行器 继承类拼凑完整的 ENDPOINT
     * 
     * @param endpoint
     * @param param
     */
    public JsonServiceClientExecutor(String endpoint, P param) {
        this.endpoint = endpoint;
        namespaceheader = new BasicHeader(HEADER_NS, NameSpaceConfig.instance.getNamespace());
        typeHeader = new BasicHeader(HEADER_TYPE, type_value);
        this.param = param;
    }

    @Override
    public T execute() throws ExecuteException {
        HttpEntity response = null;
        try {
            response = post();
            T result = consume(response);
            if (result != null) {
                return result;
            }
            String entity = EntityUtils.toString(response);
            Log.w(TAG, "response: " + entity);
            Log.w("", "------------------------------end--------------------------------");
            return gson().fromJson(entity, getResultType());
        } catch (RuntimeException e) {
            throw new ExecuteException(e);
        } catch (SSLException e) {
            throw new ExecuteException(e);
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            throw new ExecuteException(e);
        } catch (HttpClientException e) {
            throw new ExecuteException(e);
        } finally {
            if (response != null) {
                // 释放响应结果InputStream，这很重要，如不释放，
                // HttpClient会认为当前Connection一直占用，运行时间久了，连接池就没有可用的连接了。
                try {
                    EntityUtilsHC4.consume(response);
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取GSON工厂实例
     * 
     * @return 实例
     */
    protected Gson gson() {
        return GsonFactory.SingleTon.create();
    }

    /**
     * 处理服务端响应结果
     * 
     * @param response 服务端响应对象
     * @return <code>null</code>使用json解析结果, 非 <code>null</code>
     *         则消费了response,实现代码必须关闭response对象
     * @throws ExecuteException 执行期错误
     */
    protected T consume(HttpEntity response) throws ExecuteException {
        return null;
    }

    /**
     * 返回的结果的类型，用于GSON，反序列化
     * 
     * @return 结果类型定义
     */
    protected abstract Type getResultType();

    protected HttpEntity post() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException {
        Log.w(TAG, "endpoint: " + endpoint + " request: " + jsontext());
        HttpResponseResult result = createAgent().post(endpoint, jsontext(), getHeaders());
        return result.getEntity();
    }

    /**
     * 获取报文头
     * 
     * @return
     */
    private Header[] getHeaders() {
        Header[] headers = new Header[2];
        headers[0] = namespaceheader;
        headers[1] = typeHeader;
        return headers;
    }

    /**
     * 获取参数的序列化字符串
     * 
     * @return 序列化字符串
     */
    protected final String jsontext() {
        if (jsontext == null)
            jsontext = gson().toJson(param);
        // Log.w(TAG, "endpoint: " + endpoint + " request: " + jsontext);

        return jsontext;
    }

    /**
     * 实现类提供HTTP客户端代理
     * 
     * @return 执行HTTP请求的客户端实现
     * @throws HttpClientException 发生请求错误
     */
    protected HttpServiceAgent createAgent() throws HttpClientException {
        return HttpClientFactory.get().create();
    }
}
