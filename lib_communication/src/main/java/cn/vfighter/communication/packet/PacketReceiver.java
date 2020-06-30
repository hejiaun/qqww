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

package cn.vfighter.communication.packet;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import cn.vfighter.ICloseable;

/**
 * 接收输入流，并拆解重组报文
 * 
 * @author Konlg
 */
public class PacketReceiver {

    private final InputStream input;
    private final IStateListener listener;

    /**
     * 构造一个收包器
     * 
     * @param in 输入流
     * @param listener 收齐一个包后的侦听器
     * @throws IOException
     */
    public PacketReceiver(HttpEntity entity, IStateListener listener) throws IOException {
        this.input = entity.getContent();
        this.listener = listener;
    }

    /**
     * 开始收包
     * 
     * @throws IOException
     */
    public void start() throws IOException {
        int len = -1;
        while ((len = readPacketLength()) > 0) {
            listener.onSegmentInputstream(new BackToBackInputStream(input, len), readPacketType());
        }
        listener.onEnd();
        input.close();
    }

    /**
     * 读出一个整数
     * 
     * @return -1 包结束 ; 大于 -1 ，组包的数据长度。
     * @throws IOException
     */
    private int readPacketLength() throws IOException {
        byte[] data = new byte[4];
        byte[] buf = new byte[4];
        int len = -1;
        int pos = 0;
        while (pos < 4 && (len = input.read(buf)) != -1) {
            int index = 0;
            while (index < len) {
                data[pos] = buf[index];
                index++;
                pos++;
            }
        }
        if (len == -1)
            return len;
        return data[3] & 0xff | (data[2] & 0xff) << 8 | (data[1] & 0xff) << 16
                | (data[0] & 0xff) << 24;
    }

    private PacketType readPacketType() throws IOException {
        byte[] abyte = new byte[1];
        int readlen = -1;
        while ((readlen = input.read(abyte)) != -1) {
            if (readlen > 0)
                break;
        }
        if (readlen == -1)
            throw new IOException("close by peer?");
        int type = abyte[0];
        if (type >= PacketType.values().length) {
            throw new IOException("illegal packet type, type value is " + type);
        }
        return PacketType.values()[type];
    }

    /**
     * 状态机状态变迁侦听器
     * 
     * @author sam.pan
     */
    public static interface IStateListener extends ICloseable {

        /**
         * 开始
         * 
         * @param closeable 可关闭的链接。实现类可在任何时候关闭该链接
         */
        void onStart(ICloseable closeable);

        /**
         * 结束
         */
        void onEnd();

        /**
         * 数据流
         * 
         * @param in 流, 可直接关闭而放弃后续的状态变迁 ，该流是 {@link BackToBackInputStream}
         * @param type 流的内容类型
         */
        void onSegmentInputstream(InputStream in, PacketType type);
    }
}
