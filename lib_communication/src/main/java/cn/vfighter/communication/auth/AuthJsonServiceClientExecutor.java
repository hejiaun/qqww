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

import vfighter.android.util.Log;

import com.google.gson.Gson;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.EntityUtilsHC4;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.EndPointRouter.RouterMatchResult;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.communication.client.JsonServiceClientExecutor;
import cn.vfighter.communication.exception.HttpClientException;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.MimeType;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * 公共服务客户端执行器
 *
 * @author Konlg
 */
public abstract class AuthJsonServiceClientExecutor<P, T> extends JsonServiceClientExecutor<P, T> {
    private static final String TAG = "AuthJsonServiceClientEx";
    private Header appdomain_header;
    private Header endtype_header;
    private Header endid_header;
    private Header apiversion_header;

    /**
     * 创建客户端执行器
     *
     * @param endpoint 端点地址
     * @param param    参数
     */
    public AuthJsonServiceClientExecutor(String endpoint, P param) {
        super(EndPointRouter.get().append(endpoint).endpoint, param);
        String domain = AuthClientContext.getAppDomain();
        if (domain == null || domain.length() == 0 || domain.trim().length() == 0) {
            RouterMatchResult router = EndPointRouter.get().append(endpoint);
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
    protected abstract Type getResultType();

    @Override
    public T execute() throws ExecuteException {
        InputStream is = null;
        try {
            HttpEntity response = post();
            T result = consume(response);
            if (result != null) {
                return result;
            }

            byte[] data = EntityUtils.toByteArray(response);
            if (endid_header != null) {
                // 解码
                data = ClientAuthUtil.decrypt(data);
            }
            String resultext = new String(data, "UTF-8");
            // 释放响应结果InputStream，这很重要，如不释放，
            // HttpClient会认为当前Connection一直占用，运行时间久了，连接池就没有可用的连接了。
            if (response.isStreaming()) {
                is = response.getContent();
            }
            EntityUtilsHC4.consume(response);
            Log.w(TAG, "response: " + resultext);
            Log.w("", "------------------------------end--------------------------------");
            return gson().fromJson(resultext, getResultType());
        } catch (RuntimeException e) {
            throw new ExecuteException(e);
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            throw new ExecuteException(e);
        } catch (HttpClientException e) {
            throw new ExecuteException(e);
        } finally {
            if (is != null) {
                try {
                    // 此处关闭是为了保证僵死的Conntion的响应流也被关闭
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取GSON工厂实例
     *
     * @return Gson
     */
    protected Gson gson() {
        return GsonFactory.SingleTon.create();
    }

    @Override
    protected HttpEntity post() throws ClientProtocolException, IOException, HttpResponseException,
            HttpClientException {
        HttpResponseResult result = null;

        if (endid_header != null) {
            String jsonText = jsontext();
            Log.w(TAG, "endpoint: " + endpoint + " request: " + jsonText);
            byte[] bytes = jsonText.getBytes("UTF-8");
            byte[] data = ClientAuthUtil.encrypt(bytes);

            result = createAgent().post(endpoint, new ByteArrayEntity(data),
                    MimeType.APPLICATION_JSON, appdomain_header, endid_header, endtype_header,
                    namespaceheader, typeHeader, apiversion_header);
        } else {
            result = createAgent().post(endpoint, jsontext(), appdomain_header, endid_header,
                    endtype_header, namespaceheader, typeHeader, apiversion_header);
        }

        return result.getEntity();
    }
}
