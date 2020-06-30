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

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;
import cn.vfighter.communication.http.MimeType;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * JSON service client executor 以表单POST的方式请求服务,请求实体为{@link StringEntity} 本类默认采用
 * {@link GsonFactory} 对返回的请求内容进行JSON反序列化。 具体实现类可以通过重载
 * {@link #consume(HttpEntity)}来提供自己的反序列化方式。
 * 
 * @author Konlg
 * @param <P> 参数-发送给后台服务接口的请求参数
 * @param <T> 结果-后台服务接口返回的结果类型
 */
public abstract class FormStringServiceClientExecutor<P, T> implements IExecutor<T> {

    private String endpoint;
    /**
     * 请求参数
     */
    protected final P param;

    public FormStringServiceClientExecutor(String endpoint, P param) {
        this.endpoint = endpoint;
        this.param = param;
    }

    @Override
    public T execute() throws ExecuteException {
        try {
            HttpEntity response = post();
            T result = consume(response);
            if (result != null) {
                return result;
            }
            String entity = EntityUtils.toString(response);
            return gson().fromJson(entity, getResultType());
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (ParseException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            throw new ExecuteException(e);
        } catch (HttpClientException e) {
            throw new ExecuteException(e);
        }
    }

    /**
     * 获取GSON工厂实例
     * 
     * @return
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
     * @throws ExecuteException
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

    private HttpEntity post() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException {
        Header[] headers = headers();

        StringEntity rp = new StringEntity(param.toString());

        HttpResponseResult result = createAgent().post(endpoint, headers, rp,
                MimeType.APPLICATION_FORM);

        header(result.getHeader());

        return result.getEntity();
    }

    /**
     * 实现类提供额外的Header
     * 
     * @return
     */
    protected abstract Header[] headers();

    /**
     * 实现类可以对返回结果的Header进行处理
     * 
     * @param headers
     */
    protected abstract void header(Header[] headers);

    /**
     * 实现类提供HTTP客户端代理
     * 
     * @return 执行HTTP请求的客户端实现
     * @throws HttpClientException
     */
    protected HttpServiceAgent createAgent() throws HttpClientException {
        return HttpClientFactory.get().create();
    }
}
