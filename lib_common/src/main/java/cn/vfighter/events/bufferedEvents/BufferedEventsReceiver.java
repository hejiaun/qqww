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

import cn.vfighter.events.EventReceiver;

/**
 * 缓冲事件接收器
 * <P>
 * 从不同的线程中接收事件列表
 *
 * @param <E> 事件类型
 * @author Konlg
 */
public interface BufferedEventsReceiver<E> extends EventReceiver<E> {
    /**
     * 添加缓冲事件列表
     * 
     * @param bufferedEvents 要处理的缓冲事件列表
     */
    void putBufferedEvents(Collection<E> bufferedEvents);
}
