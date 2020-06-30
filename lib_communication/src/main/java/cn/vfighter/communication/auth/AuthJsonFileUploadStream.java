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

import cn.vfighter.communication.packet.FileTypeBinary;
import cn.vfighter.communication.packet.JsonFileUploadStream;

/**
 * 加密文件上传的报文复合输入流
 * 
 * @author Konlg
 */
public class AuthJsonFileUploadStream extends JsonFileUploadStream {

    /**
     * 构造加密文件上传的报文复合输入流
     * 
     * @param json 信息头
     * @param file 负荷文件
     * @throws IOException 文件读取发生错误
     */
    public AuthJsonFileUploadStream(Object json, FileTypeBinary[] file) throws IOException {
        super(json, file);
    }

    @Override
    protected byte[] convert(byte[] src) throws IOException {
        // 加密
        if (AuthClientContext.useEncrypt()) {
            return ClientAuthUtil.encrypt(src);
        } else {
            return src;
        }
    }
}
