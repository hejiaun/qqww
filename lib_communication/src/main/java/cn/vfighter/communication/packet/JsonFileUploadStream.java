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

package cn.vfighter.communication.packet;

import java.io.IOException;

import org.apache.http.protocol.HTTP;

import cn.vfighter.communication.utils.GsonFactory;

/**
 * 构造一个简单的文件上传的报文复合输入流
 * 
 * @author konlg
 */
public class JsonFileUploadStream extends CombinatedInputStream {
    /**
     * 构造一个简单的文件上传的报文复合输入流
     * 
     * @param json JSON参数
     * @param file 文件输入流
     * @throws IOException 输入流发生异常
     */
    public JsonFileUploadStream(Object json, FileTypeBinary... file) throws IOException {
        super();
        append(new PacketInputStreamOfPacketHeader());
        append(new PacketInputStreamOfJson(
                convert(GsonFactory.SingleTon.create().toJson(json).getBytes(HTTP.UTF_8))));
        if (file != null) {
            for (FileTypeBinary f : file) {
                append(new PacketInputStreamOfBinary(f));
            }
        }
    }

    /**
     * 数据转换
     * 
     * @param src 待转换数据
     * @return 转换后数据
     * @throws IOException 发生错误
     */
    protected byte[] convert(byte[] src) throws IOException {
        return src;
    }
}
