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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.entity.mime.MIME;

import com.google.gson.Gson;

import cn.vfighter.communication.packet.PacketReader;

/**
 * 一个加密兼容模式的JSON包读取器
 * 
 * @author Konlg
 */
public class AuthPacketReader extends PacketReader {
    private String appDomain;
    private String endId;
    private IDecrypt decrypt;
    private boolean teaEncrypt;

    /**
     * 构造一个加密兼容模式的JSON包读取器
     * 
     * @param appdomain 应用域
     * @param endId 加密客户端ID
     * @param input 输入流
     * @param gson gson解析器
     * @param decrypt 加密组件
     */
    public AuthPacketReader(String appdomain, String endId, InputStream input, Gson gson,
            IDecrypt decrypt) {
        super(input, gson);
        this.decrypt = decrypt;
        this.appDomain = appdomain;
        this.endId = endId;
    }

    @Override
    public String readAsJson() throws IOException, IllegalArgumentException {
        if (endId == null)
            return super.readAsJson();
        byte[] buf = readData();
        try {
            // 解密
            buf = decrypt.decrypt(appDomain, endId, new ByteArrayInputStream(buf));
        } catch (Exception ex) {
            throw new IOException(ex);
        }
        String str = new String(buf, MIME.UTF8_CHARSET);
        teaEncrypt = true;
        return str;
    }

    /**
     * 是否使用了加密
     * 
     * @return <code>true</code>采用加密技术
     */
    public boolean isTeaEncrypt() {
        return teaEncrypt;
    }
}
