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

package cn.vfighter.account.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.account.RestInterfaceUrl;
import cn.vfighter.account.bean.UserAccount;
import cn.vfighter.bean.exception.ExceptionInfo;
import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.EndPointRouter.RouterMatchResult;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.auth.AuthClientContext;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.communication.client.IExecutor;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;
import cn.vfighter.communication.http.RequestParams;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * 用用户account 和 accountType 进行和 spring-security 配合的鉴权提供者。</br>
 * 仅供四川出入境系统使用。
 * 
 * @author chaoxin
 */
public class SimpleLoginSpringAuthenticationProvider
        implements IExecutor<ResponseSingle<UserAccount>> {
    private static final String ENDPOINT = RestInterfaceUrl.account_login;

    private String account;
    private String password;

    private String authenticationUrl;
    private String appdomain;

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";

    private String key_account = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String key_password = "password";

    private static TypeToken<ResponseSingle<String>> errToken = new TypeToken<ResponseSingle<String>>() {
    };
    private static final TypeToken<ResponseSingle<UserAccount>> userToken = new TypeToken<ResponseSingle<UserAccount>>() {
    };

    private static final String HEADER_ERR = "JSON_ERR";

    /**
     * 构造一个Spring security 鉴权提供者
     * 
     * @param account 账号
     * @param accountType 账号类型
     * @param protectCode 保护码
     */
    public SimpleLoginSpringAuthenticationProvider(String account, String password) {
        RouterMatchResult endpoint = EndPointRouter.get().append(ENDPOINT);
        this.authenticationUrl = endpoint.endpoint;
        this.appdomain = endpoint.namespace;
        this.account = account;
        this.password = password;
    }

    @Override
    public ResponseSingle<UserAccount> execute() throws ExecuteException {
        ResponseSingle<UserAccount> response = new ResponseSingle<UserAccount>();

        try {
            Header domainHeader = new BasicHeader(HeaderConstants.HEADER_APPDOMAIN, this.appdomain);
            Header endidHeader = new BasicHeader(HeaderConstants.HEADER_ENDID,
                    AuthClientContext.getEndId());
            Header endTypeHeader = new BasicHeader(HeaderConstants.HEADER_ENDTYPE,
                    AuthClientContext.getEndType());
            RequestParams params = new RequestParams(key_account, this.account, key_password,
                    this.password);
            if (!StringUtils.isNotEmpty(this.password)) {
                params.put(key_password, this.password);
            }
            HttpResponseResult result = HttpClientFactory.get().create().post(
                    this.authenticationUrl,
                    new Header[] { domainHeader, endidHeader, endTypeHeader }, params,
                    ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), null);
            String entity = EntityUtils.toString(result.getEntity());
            response = GsonFactory.SingleTon.create().fromJson(entity, userToken.getType());

            if (!response.hasException()) {
                UserAccount ui = response.getData();
                if (ui != null) {
                    AuthClientContext.setSessionId(ui.getId() + "");
                }
            }
        } catch (ClientProtocolException e) {
            throw new ExecuteException(e);
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            switch (e.getErrorcode()) {
            case 401:
                boolean errNoFound = true;
                Header[] headers = e.getHeaders();
                for (Header h : headers) {
                    if (HEADER_ERR.equals(h.getName())) {
                        String content = h.getValue();
                        if (content != null) {
                            try {
                                content = URLDecoder.decode(content, "UTF-8");
                                response = GsonFactory.SingleTon.create().fromJson(content,
                                        errToken.getType());
                                errNoFound = false;
                            } catch (UnsupportedEncodingException e1) {
                            }
                        }
                        break;
                    }
                }
                if (errNoFound) {
                    response.setException(new ExceptionInfo((short) 2002, "未知网络异常"));
                }
                break;
            case 500:
                response.setException(new ExceptionInfo((short) 2003, "未知服务器异常"));
            case 404:
            default:
                response.setException(new ExceptionInfo((short) 2004, "未知网络异常"));
            }
        }

        return response;
    }
}
