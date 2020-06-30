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

package cn.vfighter.communication.client;

import java.io.IOException;

/**
 * 可空消费或直接关闭的对象
 * 
 * @author Konlg
 */
public interface IConsumeCloseable {

    /**
     * 关闭
     * 
     * @throws IOException
     */
    void close() throws IOException;

    /**
     * 消费剩余的内容，不会导致对象被关闭。
     * 
     * @throws IOException
     */
    void consume() throws IOException;

}
