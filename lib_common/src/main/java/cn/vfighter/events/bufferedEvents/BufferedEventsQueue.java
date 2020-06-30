/* Copyright (c) vfighter.cn, All Rights Reserved
 *              ____     __    __
 *   _   ______/ __/____/ / __/ /_________
 *  | | / /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | |/ / / / / /__  / // // /_/ ___/ /
 *  |___/_/ /_/  __/ /_//_/ \__/\___/_/
 *              \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.events.bufferedEvents;

import java.util.Collection;

import cn.vfighter.events.EventDispatcher;
import cn.vfighter.events.EventQueue;

/**
 * 缓冲事件队列
 *
 * @param <E> 事件类型
 * @author Konlg
 */
public interface BufferedEventsQueue<E> extends BufferedEventsReceiver<E>, EventDispatcher<E> {
    /**
     * 发送挂起的事件
     */
    void sendPendingEvents();

    /**
     * 设置缓冲事件的初始容量
     *
     * @param bufferCapacity 缓冲事件的初始容量
     */
    void setBufferCapacity(int bufferCapacity);

    /**
     * 缓冲事件并随后发出
     *
     * @param destination 事件发送目标
     * @param event 要发送的事件
     */
    void send(BufferedEventsReceiver<E> destination, E event);

    /**
     * 获取事件队列
     *
     * @return 事件队列
     */
    EventQueue<Collection<E>> getEventQueue();
}
