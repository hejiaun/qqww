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
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.MimeParams;
import cn.vfighter.communication.http.ProgressHandlerInterface;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * JSON service client executor
 * 
 * @author Konlg
 * @param <P> 参数
 * @param <T> 结果
 */
public abstract class MimePostClientExecutor<T> implements IExecutor<T> {

    private String endpoint;
    private MimeParams params;

    private ProgressHandlerInterface progressHandler;

    private String contentType;

    public MimePostClientExecutor(String endpoint, MimeParams param,
            ProgressHandlerInterface progressHandler) {
        this(endpoint, param, progressHandler, null);
    }

    public MimePostClientExecutor(String endpoint, MimeParams param,
            ProgressHandlerInterface progressHandler, String contentType) {
        this.endpoint = endpoint;
        params = param;
        this.progressHandler = progressHandler;
        this.contentType = contentType;
    }

    @Override
    public T execute() throws ExecuteException {
        try {
            String entity = EntityUtils.toString(post());
            if (entity == null || entity.trim().length() < 1) {
                return null;
            }
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

    protected abstract Type getResultType();

    private HttpEntity post() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException {
        HttpResponseResult result = HttpClientFactory.get().create().post(endpoint, null, params,
                contentType, progressHandler);
        return result.getEntity();
    }
}
