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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeader;

import cn.vfighter.ICloseable;
import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.EndPointRouter.RouterMatchResult;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.auth.AuthClientContext;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.HttpServiceAgent;
import cn.vfighter.communication.packet.PacketReceiver;
import cn.vfighter.communication.packet.PacketReceiver.IStateListener;

/**
 * 返回结果为包结构的服务请求 能够持续保持在线，接受服务器不断发送而来的数据包，调用者通过传入
 * {@link IStateListener}接收包到达的事件，并消费该包的数据内容。 等待10分钟
 * 
 * @author Konlg
 */
public abstract class PacketServiceClientExecutor implements IExecutor<Void> {

    private static final int LONG_TIMEOUT = 10 * 60 * 1000;
    private String endpoint;
    private IStateListener packetListener;
    private String contentType;
    private BasicHeader namespaceheader;
    protected Header typeHeader;

    private Header appdomain_header;
    private Header endtype_header;
    private Header endid_header;
    private Header apiversion_header;

    /**
     * 构造一个解析包结构的服务请求 使用 XFEndpoint 提供 ENDPOINT 地址
     * 
     * @param endpoint 服务端点
     * @param listener 包结果侦听器
     * @param contentType
     */
    public PacketServiceClientExecutor(IStateListener listener, String contentType) {
        RouterMatchResult router = EndPointRouter.get().append(this);
        this.endpoint = router.endpoint;
        namespaceheader = new BasicHeader(JsonServiceClientExecutor.HEADER_NS, router.namespace);
        typeHeader = new BasicHeader(JsonServiceClientExecutor.HEADER_TYPE,
                JsonServiceClientExecutor.type_value);

        String domain = AuthClientContext.getAppDomain();
        if (domain == null || domain.length() == 0 || domain.trim().length() == 0) {
            if (router != null) {
                domain = router.namespace;
            }
        }
        appdomain_header = new BasicHeader(HeaderConstants.HEADER_APPDOMAIN, domain);
        endtype_header = new BasicHeader(HeaderConstants.HEADER_ENDTYPE,
                AuthClientContext.getEndType());
        if (AuthClientContext.useEncrypt()) {
            endid_header = new BasicHeader(HeaderConstants.HEADER_ENDID,
                    AuthClientContext.getEndId());
        }
        String apiVersion = AuthClientContext.getApiVersion();
        if (apiVersion != null && !"".equals(apiVersion.trim())) {
            apiversion_header = new BasicHeader(HeaderConstants.HEADER_APIVERSION, apiVersion);
        }

        this.packetListener = listener;
        this.contentType = contentType;
    }

    /**
     * 构造一个解析包结构的服务请求
     * 
     * @param endpoint 服务端点
     * @param listener 包结果侦听器
     * @param contentType
     */
    public PacketServiceClientExecutor(String endpoint, IStateListener listener,
            String contentType) {
        RouterMatchResult router = EndPointRouter.get().append(endpoint);
        this.endpoint = router.endpoint;
        this.packetListener = listener;
        this.contentType = contentType;
        namespaceheader = new BasicHeader(JsonServiceClientExecutor.HEADER_NS,
                NameSpaceConfig.instance.getNamespace());
        typeHeader = new BasicHeader(JsonServiceClientExecutor.HEADER_TYPE,
                JsonServiceClientExecutor.type_value);

        String domain = AuthClientContext.getAppDomain();
        if (domain == null || domain.length() == 0 || domain.trim().length() == 0) {
            if (router != null) {
                domain = router.namespace;
            }
        }
        appdomain_header = new BasicHeader(HeaderConstants.HEADER_APPDOMAIN, domain);
        endtype_header = new BasicHeader(HeaderConstants.HEADER_ENDTYPE,
                AuthClientContext.getEndType());
        if (AuthClientContext.useEncrypt()) {
            endid_header = new BasicHeader(HeaderConstants.HEADER_ENDID,
                    AuthClientContext.getEndId());
        }
        String apiVersion = AuthClientContext.getApiVersion();
        if (apiVersion != null && !"".equals(apiVersion.trim())) {
            apiversion_header = new BasicHeader(HeaderConstants.HEADER_APIVERSION, apiVersion);
        }
    }

    @Override
    public Void execute() throws ExecuteException {
        HttpResponseResult result;
        try {
            HttpServiceAgent agent = HttpClientFactory.get().create();
            ICloseable closeable = agent.createCloseableRequest(endpoint,
                    Long.valueOf(LONG_TIMEOUT).intValue());
            packetListener.onStart(closeable);
            result = agent.post(closeable, getHeaders(), createPostEntity(), contentType);
            new PacketReceiver(result.getEntity(), packetListener).start();
            return null;
        } catch (ParseException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            throw new ExecuteException(e);
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (IllegalStateException e) {
            throw new ExecuteException(e);
        } finally {
            packetListener.onEnd();
        }
    }

    /**
     * 获取报文头
     * 
     * @return
     */
    private Header[] getHeaders() {
        List<Header> list = new ArrayList<Header>();
        if (namespaceheader != null) {
            list.add(namespaceheader);
        }
        if (typeHeader != null) {
            list.add(typeHeader);
        }
        if (appdomain_header != null) {
            list.add(appdomain_header);
        }
        if (endtype_header != null) {
            list.add(endtype_header);
        }
        if (endid_header != null) {
            list.add(endid_header);
        }
        if (apiversion_header != null) {
            list.add(apiversion_header);
        }

        Header[] headers = new Header[list.size()];
        return list.toArray(headers);
    }

    protected abstract HttpEntity createPostEntity()
            throws UnsupportedEncodingException, IOException;

}
