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

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;

/**
 * 应用安全通讯授权
 * 
 * @author Konlg
 */
public class AuthorizeExecutor
        extends SSLClientExecutor<VFighterRequest<AuthorizeParam>, ResponseSingle<AuthData>> {
    private static final TypeToken<ResponseSingle<AuthData>> token = new TypeToken<ResponseSingle<AuthData>>() {
    };

    /**
     * 应用安全通讯授权
     * 
     * @param endpoint 授权
     * @param appdomain 应用域名
     * @param endId 终端ID
     * @param sign 安全签名
     * @throws IllegalAccessException
     */
    public AuthorizeExecutor(String endpoint, String appdomain, String endId, String sign,
            String endtype) {
        super(endpoint,
                new VFighterRequest<AuthorizeParam>(new AuthorizeParam(appdomain, endId, sign)),
                appdomain, endtype);
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }

}
