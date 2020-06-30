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

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * GET client executor
 * 
 * @author Konlg
 * @param <P> 参数
 * @param <T> 结果
 */
public abstract class GetMethodClientExecutor<P, T> implements IExecutor<T> {

    private String endpoint;
    private String jsonTxt;

    public GetMethodClientExecutor(String endpoint, P param) {
        this.endpoint = endpoint;
        this.jsonTxt = gson().toJson(param);
    }

    /**
     * 提供GSON工厂对象
     * 
     * @return
     */
    protected Gson gson() {
        return GsonFactory.SingleTon.create();
    }

    @Override
    public T execute() throws ExecuteException {
        try {
            HttpEntity response = get();
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

    protected abstract Type getResultType();

    private HttpEntity get() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException, HttpHostConnectException {
        HttpResponseResult result;
        if (jsonTxt != null && !jsonTxt.equals("") && !jsonTxt.equals("null"))
            result = createAgent().get(endpoint + "?data=" + jsonTxt);
        else
            result = createAgent().get(endpoint);
        return result.getEntity();
    }

    /**
     * 实现类提供HTTP客户端代理
     * 
     * @return
     * @throws HttpClientException
     */
    protected HttpServiceAgent createAgent() throws HttpClientException {
        return HttpClientFactory.get().create();
    }
}
