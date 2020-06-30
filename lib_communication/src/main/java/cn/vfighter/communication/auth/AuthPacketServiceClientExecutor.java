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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHeader;

import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.HeaderConstants;
import cn.vfighter.communication.client.PacketServiceClientExecutor;
import cn.vfighter.communication.packet.JsonFileUploadStream;
import cn.vfighter.communication.packet.PacketReceiver.IStateListener;

/**
 * 授权的分包客户端执行器
 *
 * @param <P>
 * @param <T>
 * @author konlg
 */
public abstract class AuthPacketServiceClientExecutor<P, T> extends PacketServiceClientExecutor {

    private Header appdomain_header;
    private Header endtype_header;
    private Header endid_header;
    private P param;

    public AuthPacketServiceClientExecutor(String endpoint, P param, IStateListener listener,
            String contentType) {
        super(EndPointRouter.get().append(endpoint).endpoint, listener, contentType);
        String domain = AuthClientContext.getAppDomain();
        if (domain == null || domain.length() == 0 || domain.trim().length() == 0) {
            domain = EndPointRouter.get().append(endpoint).namespace;
        }
        appdomain_header = new BasicHeader(HeaderConstants.HEADER_APPDOMAIN, domain);
        endtype_header = new BasicHeader(HeaderConstants.HEADER_ENDTYPE,
                AuthClientContext.getEndType());
        if (AuthClientContext.useEncrypt()) {
            endid_header = new BasicHeader(HeaderConstants.HEADER_ENDID,
                    AuthClientContext.getEndId());
        }
    }

    public Header[] getOtherHeaders() {
        int length = 2;
        if (endid_header != null) {
            length = 3;
        }
        Header[] result = new Header[length];
        result[0] = appdomain_header;
        result[1] = endtype_header;
        if (endid_header != null) {
            result[2] = endid_header;
        }
        // return result;

        return null;
    }

    @Override
    protected HttpEntity createPostEntity() throws UnsupportedEncodingException, IOException {
        /*
         * if (endid_header != null) { byte[] bytes =
         * GsonFactory.SingleTon.create().toJson(param).getBytes("UTF-8");
         * byte[] data = ClientAuthUtil.encrypt(bytes); return new
         * ByteArrayEntity(data); } else { return new InputStreamEntity(new
         * JsonFileUploadStream(param), -1); }
         */
        return new InputStreamEntity(new JsonFileUploadStream(param), -1);
    }

}
