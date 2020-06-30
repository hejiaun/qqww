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
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.SSLException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 * 重试处理句柄
 * 
 * @author konlg
 */
class RetryHandler implements HttpRequestRetryHandler {

    /**
     * 黑白名单列表单例黑
     * 
     * @author sam.pan contact: sam301@163.com
     */
    static enum SingleTonList {

        SINGLETON;

        private final Set<Class<?>> exceptionWhitelist;

        private final Set<Class<?>> exceptionBlacklist;

        private SingleTonList() {
            exceptionWhitelist = new HashSet<Class<?>>();
            exceptionBlacklist = new HashSet<Class<?>>();
            // Retry if the server dropped connection on us
            exceptionWhitelist.add(NoHttpResponseException.class);
            // retry-this, since it may happens as part of a WI-FI to 3G failure
            // over
            exceptionWhitelist.add(UnknownHostException.class);
            // retry-this, since it may happens as part of a WI-FI to 3G failure
            // over
            exceptionWhitelist.add(SocketException.class);

            // never retry timeouts
            exceptionBlacklist.add(InterruptedIOException.class);
            // never retry SSL handshake failures
            exceptionBlacklist.add(SSLException.class);
        }

        /**
         * 添加一個允许重试的白名单
         * 
         * @param cls
         */
        void addClassToWhitelist(Class<?> cls) {
            exceptionWhitelist.add(cls);
        }

        /**
         * 添加一个禁止重试的黑名单
         * 
         * @param cls
         */
        void addClassToBlacklist(Class<?> cls) {
            exceptionBlacklist.add(cls);
        }
    }

    /**
     * 重试次数上限
     */
    private final int maxRetries;

    /**
     * 重试时间间隔
     */
    private final int retrySleepTimeMS;

    private Lock lock;

    public RetryHandler(int maxRetries, int retrySleepTimeMS) {
        this.maxRetries = maxRetries;
        this.retrySleepTimeMS = retrySleepTimeMS;
        lock = new ReentrantLock();
    }

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        try {
            lock.lock();
            boolean retry = true;

            Boolean b = (Boolean) context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
            boolean sent = (b != null && b);

            if (executionCount > maxRetries) {
                // Do not retry if over max retry count
                retry = false;
            } else if (isInList(SingleTonList.SINGLETON.exceptionWhitelist, exception)) {
                // immediately retry if error is whitelisted
                retry = true;
            } else if (isInList(SingleTonList.SINGLETON.exceptionBlacklist, exception)) {
                // immediately cancel retry if the error is blacklisted
                retry = false;
            } else if (!sent) {
                // for most other errors, retry only if request hasn't been
                // fully
                // sent yet
                retry = true;
            }

            if (retry) {
                // resend all idempotent requests
                HttpUriRequest currentReq = (HttpUriRequest) context
                        .getAttribute(ExecutionContext.HTTP_REQUEST);
                if (currentReq == null) {
                    return false;
                }
            }

            if (retry) {
                try {
                    Thread.sleep(retrySleepTimeMS);
                } catch (InterruptedException e) {
                    return false;
                }
            } else {
                exception.printStackTrace();
            }

            return retry;
        } finally {
            lock.unlock();
        }
    }

    private boolean isInList(Set<Class<?>> list, Throwable error) {
        for (Class<?> aList : list) {
            if (aList.isInstance(error)) {
                return true;
            }
        }
        return false;
    }
}
