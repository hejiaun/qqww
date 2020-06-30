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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import cn.vfighter.ICloseable;
import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;

/**
 * 可关闭HTTP链接的JSON Over HTTP Service
 * <p>
 * 一个支持关闭底层链接的json 服务。 该服务使用的http 不在
 * httpclient的线程池内，不占用该资源。应当谨慎使用，适合需要长时间等待的服务端口
 * </p>
 * 
 * @author Konlg
 * @param <P> 参数
 * @param <T> 值
 */
public abstract class JsonServiceCloseableExecutor<P, T> extends JsonServiceClientExecutor<P, T>
        implements ICloseable {

    private int timeout;
    private ICloseable closeable;
    private Lock lock;
    private boolean closed;

    /**
     * 构建一个可关闭底层链接的服务执行器
     * 
     * @param endpoint 业务端点
     * @param timeout 超时
     * @param param 请求参数
     */
    public JsonServiceCloseableExecutor(int timeout, P param) {
        super(param);
        this.timeout = timeout;
        lock = new ReentrantLock();
    }

    /**
     * 构建一个可关闭底层链接的服务执行器
     * 
     * @param endpoint 业务端点
     * @param timeout 超时
     * @param param 请求参数
     */
    public JsonServiceCloseableExecutor(String endpoint, int timeout, P param) {
        super(EndPointRouter.get().append(endpoint).endpoint, param);
        this.timeout = timeout;
        lock = new ReentrantLock();
    }

    @Override
    public T execute() throws ExecuteException {
        HttpResponseResult result = null;
        try {
            HttpServiceAgent agent = HttpClientFactory.get().create();
            lock.lock();
            try {
                closeable = agent.createCloseableRequest(endpoint, timeout);
                if (closed) {
                    closeable.close();
                    throw new ExecuteException("task abort");
                }
            } finally {
                lock.unlock();
            }
            result = agent.post(closeable, new Header[0], createPostEntity(),
                    ContentType.APPLICATION_OCTET_STREAM.getMimeType());
            return gson().fromJson(EntityUtils.toString(result.getEntity()), getResultType());
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (ParseException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            throw new ExecuteException(e);
        } finally {
            if (result != null) {
                try {
                    result.getEntity().consumeContent();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private HttpEntity createPostEntity() throws UnsupportedEncodingException {
        byte[] data = jsontext().getBytes(HTTP.UTF_8);
        return new InputStreamEntity(new ByteArrayInputStream(data), data.length);
    }

    @Override
    public void close() throws IOException {
        lock.lock();
        try {
            closed = true;
            if (closeable != null) {
                closeable.close();
            }
        } finally {
            lock.unlock();
        }
    }

}
