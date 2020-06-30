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
import java.io.UnsupportedEncodingException;

import org.apache.http.protocol.HTTP;

import cn.vfighter.communication.utils.GsonFactory;

/**
 * 构造一个简单报文复合输入流
 * 
 * @author konlg
 */
public class JsonUploadStream extends CombinatedInputStream {

    public JsonUploadStream(Object json) throws UnsupportedEncodingException, IOException {
        super();
        append(new PacketInputStreamOfPacketHeader());
        append(new PacketInputStreamOfJson(
                GsonFactory.SingleTon.create().toJson(json).getBytes(HTTP.UTF_8)));
    }

    public void cancel() throws IOException {
        append(new PacketInputStreamOfCancel());
    }
}
