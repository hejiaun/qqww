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

package cn.vfighter.communication;

import vfighter.android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import cn.vfighter.communication.client.IExecutor;
import cn.vfighter.communication.httpcontainer.AntPathMatcher;
import cn.vfighter.communication.httpcontainer.PathMatcher;
import cn.vfighter.communication.httpcontainer.StringUtils;

/**
 * 端点路由管理 管理 HTTP 服务器和 ENDPOINT 的对应关系
 *
 * @author konlg
 */
public class EndPointRouter {

    private static final String TAG = "EndPointRouter";
    /**
     * Any number of these characters are considered delimiters between multiple
     * resource paths in a single String value.
     */
    public static final String RESOURCE_URL_DELIMITERS = ",; \t\n";

    /**
     * 去掉根路径符号
     */
    private static final String PATTERN_ROOT = "/*";

    /**
     * 直接端点 key path , value server
     */
    private Map<String, String> endpointsStraight;

    /**
     * 正则端点 key path , value server
     */
    private Map<String, String> endpointPattern;

    /**
     * 路径匹配
     */
    private PathMatcher pathMatcher;

    /**
     * 默认主机,当没有匹配的路由时,使用该主机作为接入服务器
     */
    private String defaultServer;

    /**
     * 默认命名空间
     */
    private String defaultNamespace;

    /**
     * 服务端点追加器 key server , value appender
     */
    private Map<String, SoftReference<ThreadLocalEndPointAppender>> endpointAppenderes;

    /**
     * 命名空间 key path , value namespace
     */
    private Map<String, String> namespaces;

    private EndPointRouter() {
        endpointsStraight = new HashMap<String, String>(5);
        pathMatcher = new AntPathMatcher();
        endpointPattern = new HashMap<String, String>(5);
        endpointAppenderes = new HashMap<String, SoftReference<ThreadLocalEndPointAppender>>(5);
        namespaces = new HashMap<String, String>(5);
    }

    static enum SingleTon {
        SINGLETON;
        EndPointRouter router;

        private SingleTon() {
            router = new EndPointRouter();
        }
    }

    /**
     * 单例
     *
     * @return 单例
     */
    public final static EndPointRouter get() {
        return SingleTon.SINGLETON.router;
    }

    /**
     * 加载ENDPOINT路由表
     *
     * @param propertiesInputStream 配置文件输入流
     * @throws IOException 读取文件错误
     */
    public void load(InputStream propertiesInputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(propertiesInputStream);
        endpointsStraight.clear();
        Enumeration<Object> enumer = properties.keys();
        while (enumer.hasMoreElements()) {
            String key = enumer.nextElement().toString();
            String value = properties.getProperty(key);
            String[] resourceUrls = StringUtils.tokenizeToStringArray(value,
                    RESOURCE_URL_DELIMITERS);
            if (resourceUrls.length < 2)
                continue;
            else if (resourceUrls.length == 2) {
                defaultServer = resourceUrls[0];
                defaultNamespace = resourceUrls[1];
            } else {
                String server = resourceUrls[0];
                String namespace = resourceUrls[1];
                for (int index = 2; index < resourceUrls.length; index++) {
                    String urlPattern = resourceUrls[index];
                    if (endpointPattern.containsKey(urlPattern)
                            || endpointsStraight.containsKey(urlPattern))
                        Log.w(TAG, String.format("Duplicate endpoint , ignore target : %s, %s",
                                server, urlPattern));
                    if (pathMatcher.isPattern(urlPattern))
                        endpointPattern.put(urlPattern, server);
                    else
                        endpointsStraight.put(urlPattern, server);
                    namespaces.put(urlPattern, namespace);
                }
            }
        }
        Log.w(TAG,
                String.format(
                        "loaded < %d > straight endpoints, < %d > pattern endpoints, default router %s",
                        endpointsStraight.size(), endpointPattern.size(), defaultServer));
    }

    /**
     * 合并 使用 XFEndpoint 注解的执行器服务端点
     *
     * @param exec 执行器
     * @return 如果有 XFEndpoint 注解,返回它的路由匹配结果, 否则返回 <code>null</code>
     */
    public RouterMatchResult append(IExecutor<?> exec) {
        VFEndpoint annotation = exec.getClass().getAnnotation(VFEndpoint.class);
        if (annotation != null) {
            return append(annotation.value());
        }
        return null;
    }

    /**
     * 合并端点
     *
     * @param part 目标端点的后半部分
     * @return 路由结果
     */
    public RouterMatchResult append(String part) {
        String server = defaultServer;
        String namespace = defaultNamespace;
        part = pathMatcher.extractPathWithinPattern(PATTERN_ROOT, part);
        if (endpointsStraight.containsKey(part)) {
            server = endpointsStraight.get(part);
            namespace = namespaces.get(part);
        } else {
            Iterator<Entry<String, String>> ite = endpointPattern.entrySet().iterator();
            while (ite.hasNext()) {
                Entry<String, String> entry = ite.next();
                if (pathMatcher.match(entry.getKey(), part)) {
                    server = entry.getValue();
                    namespace = namespaces.get(entry.getKey());
                    break;
                }
            }
        }

        ThreadLocalEndPointAppender appender = null;
        if (!endpointAppenderes.containsKey(server)
                || (appender = endpointAppenderes.get(server).get()) == null) {
            appender = new ThreadLocalEndPointAppender(server);
            endpointAppenderes.put(server,
                    new SoftReference<EndPointRouter.ThreadLocalEndPointAppender>(appender));
        }
        RouterMatchResult result = new RouterMatchResult(appender.append(part), namespace);
        // Log.e(TAG, String.format("%s , %s ", result.endpoint,
        // result.namespace));
        return result;
    }

    /**
     * 线程安全的端点追加器
     *
     * @author sam.pan
     */
    static class ThreadLocalEndPointAppender {

        /**
         * 线程变量StringBuffer,用于追加端点
         */
        private ThreadLocal<StringBuffer> tlsb;

        private String server;

        /**
         * tlsb的基准位置
         */
        private int pos;

        /**
         * 构造一个线程安全的端点追加器
         *
         * @param server
         */
        ThreadLocalEndPointAppender(String server) {
            super();
            pos = server.length();
            this.server = server;
            tlsb = new ThreadLocal<StringBuffer>();
        }

        /**
         * 追加一个端点
         *
         * @param endpoint
         * @return
         */
        String append(String endpoint) {
            StringBuffer sb = tlsb.get();
            if (sb == null) {
                sb = new StringBuffer(server);
                tlsb.set(sb);
            }
            try {
                String result = sb.append(endpoint).toString();
                return result;
            } finally {
                sb.delete(pos, tlsb.get().length());
            }
        }
    }

    /**
     * 路由结果
     *
     * @author sam.pan
     */
    public static class RouterMatchResult {

        /**
         * 完整端点
         */
        public final String endpoint;

        /**
         * 对应的命名空间
         */
        public final String namespace;

        RouterMatchResult(String endpoint, String namespace) {
            super();
            this.endpoint = endpoint;
            this.namespace = namespace;
        }
    }
}
