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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHeader;

import cn.vfighter.ICloseable;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.log.LoggerUtils;

/**
 * 使用 {@link HttpURLConnection}实现包通讯
 * 
 * @author konlg
 */
class PacketHttpClient implements ICloseable {

    private static final int TIMEOUT_CONNECT = 10000;
    private static final int TIMEOUT_READ = 120000;
    private static final int UNIDENTIRIED_URL_ERROR = 403;
    private final URL url;
    private HttpURLConnection connection;
    private InputStreamEntity response;
    private final int timeout;

    /**
     * 申请关闭的链接。 当 <code>true</code>时，应当执行关闭操作
     */
    private boolean closed;

    /**
     * 已经建立链接的标志 当非 <code>null</code>已经和服务器建立了链接
     */
    private InputStream connected;

    /**
     * 确保关闭被正确执行的同步锁
     */
    private final Lock lock;

    /**
     * 构造一个长链接
     * 
     * @param urlstring
     * @throws MalformedURLException
     */
    PacketHttpClient(String urlstring) throws MalformedURLException {
        this(urlstring, TIMEOUT_READ);
    }

    /**
     * 构造一个长链接
     * 
     * @param urlstring
     * @param timeout
     * @throws MalformedURLException
     */
    PacketHttpClient(String urlstring, int timeout) throws MalformedURLException {
        url = new URL(urlstring);
        this.timeout = timeout;
        lock = new ReentrantLock();
    }

    HttpResponseResult post(InputStreamEntity entity, Header[] headers, String contentType)
            throws IOException, HttpResponseException {
        LoggerUtils.get().info("post " + url.toString());
        connection = (HttpURLConnection) url.openConnection();
        LoggerUtils.get().info("opened connection " + url.toString());
        connection.setDoOutput(true);
        Map<String, String> clientHeaderMap = HttpClientFactory.get().agent.clientHeaderMap;
        for (String header : clientHeaderMap.keySet()) {
            connection.addRequestProperty(header, clientHeaderMap.get(header));
        }
        for (Header header : headers) {
            connection.addRequestProperty(header.getName(), header.getValue());
        }
        if (contentType != null) {
            connection.addRequestProperty(HttpServiceAgent.HEADER_CONTENT_TYPE, contentType);
        }
        connection.setConnectTimeout(TIMEOUT_CONNECT);
        connection.setReadTimeout(timeout);
        connection.setAllowUserInteraction(true);
        connection.setInstanceFollowRedirects(true);
        setCookie(connection, HttpClientFactory.get().agent.getCookiesStore());

        if (closed) {
            LoggerUtils.get().info("closed before connecting " + url.toString());
            connection.disconnect();
            throw new IllegalStateException("client is closed");
        }
        LoggerUtils.get().info("connecting " + url.toString());
        connection.connect();
        LoggerUtils.get().info("connected " + url.toString());
        OutputStream outstream = connection.getOutputStream();
        entity.writeTo(outstream);
        LoggerUtils.get().info("request sended " + url.toString());
        int responsecode = 0;
        try {
            responsecode = connection.getResponseCode();

        } catch (IOException e) {
            if (responsecode == HttpStatus.SC_UNAUTHORIZED
                    || responsecode <= UNIDENTIRIED_URL_ERROR)
                throw new HttpResponseException(responsecode, headers, url.toString(),
                        e.getMessage());
            else
                throw new IOException();
        }
        connected = connection.getInputStream();
        if (closed) {
            try {
                closeConnection();
            } catch (InterruptedException e) {
                throw new IllegalStateException("client is closed", e);
            }
            throw new IllegalStateException("client is closed");
        }
        LoggerUtils.get().info("start receive data " + url.toString());
        Map<String, List<String>> headerfileds = connection.getHeaderFields();
        List<Header> hs = new ArrayList<Header>();
        if (headerfileds != null) {
            Iterator<Entry<String, List<String>>> ite = headerfileds.entrySet().iterator();
            while (ite.hasNext()) {
                Entry<String, List<String>> sk = ite.next();
                if (StringUtils.isBlank(sk.getKey()))
                    continue;
                for (String value : sk.getValue()) {
                    hs.add(new BasicHeader(sk.getKey(), value));
                }
            }
        }
        response = new InputStreamEntity(connected, connection.getContentLength());
        HttpResponseResult result = new HttpResponseResult(hs.toArray(new Header[hs.size()]),
                response);
        return result;
    }

    @Override
    public void close() throws IOException {
        lock.lock();
        try {
            if (closed)
                return;
            LoggerUtils.get().info("closing " + url.toString());
            closed = true;
            closeConnection();
            LoggerUtils.get().info("closed " + url.toString());
        } catch (InterruptedException e) {
            throw new IOException("close http connection failure.", e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 如果已经建立链接则关闭链接，否则不做任何事情 某些机器关闭connect 会造成阻塞，使用线程执行，不理会是否正常关闭。
     * 
     * @throws InterruptedException
     */
    private void closeConnection() throws InterruptedException {
        if (connected != null) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        connection.disconnect();
                        connected.close();
                    } catch (IOException e) {
                        LoggerUtils.get()
                                .error("close receive inputstream failure. " + url.toString());
                    }
                }
            });
            thread.start();
            thread.join(2000);
        }
    }

    private void setCookie(HttpURLConnection conn, CookieStore m_cookieStore) {
        if (m_cookieStore != null) {
            List<Cookie> clist = m_cookieStore.getCookies();
            if (clist.size() > 0) {
                StringBuilder cookieStr = new StringBuilder();
                for (int i = 0; i < clist.size(); i++) {
                    Cookie cookie = clist.get(i);
                    cookieStr.append(cookie.getName());
                    cookieStr.append("=");
                    cookieStr.append(cookie.getValue());
                    cookieStr.append("; ");
                }
                conn.setRequestProperty("Cookie", cookieStr.toString());
            }
        }
    }
}
