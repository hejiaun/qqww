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

import org.apache.http.client.ClientProtocolException;

import cn.vfighter.account.RestInterfaceUrl;
import cn.vfighter.bean.exception.ExceptionInfo;
import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.EndPointRouter.RouterMatchResult;
import cn.vfighter.communication.VFighterResponse;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.communication.client.IExecutor;
import cn.vfighter.communication.exception.HttpResponseException;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.HttpResponseResult;

/**
 * 退出登陆提供者
 * 
 * @author konlg
 */
public class SpringLogoutProvider implements IExecutor<VFighterResponse> {
    private static final String ENDPOINT = RestInterfaceUrl.account_logout;

    private String authenticationUrl;

    /**
     * 退出登陆提供者
     */
    public SpringLogoutProvider() {
        RouterMatchResult endpoint = EndPointRouter.get().append(ENDPOINT);
        this.authenticationUrl = endpoint.endpoint;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VFighterResponse execute() throws ExecuteException {
        VFighterResponse response = new VFighterResponse();

        try {
            HttpResponseResult result = HttpClientFactory.get().create()
                    .post(this.authenticationUrl);
            result.getEntity().consumeContent();
        } catch (ClientProtocolException e) {
            throw new ExecuteException(e);
        } catch (IOException e) {
            throw new ExecuteException(e);
        } catch (HttpResponseException e) {
            switch (e.getErrorcode()) {
            case 401:
                response.setException(new ExceptionInfo((short) 2002, "未知网络异常"));
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
