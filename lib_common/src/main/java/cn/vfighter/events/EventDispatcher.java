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

package cn.vfighter.events;

/**
 * 事件发送器
 *
 * @param <E> 事件类型
 * @author Konlg
 */
public interface EventDispatcher<E> {
    /**
     * 设置活动的事件处理器
     * 
     * @param eventProcessor 事件处理器
     */
    void setEventProcessor(EventProcessor<E> activeEventProcessor);

    /**
     * 判断待处理队列是否为空
     * 
     * @return boolean 没有任何待处理事件时, 返回true
     */
    boolean isEmpty();

    /**
     * 发送并处理事件
     * 
     * @return boolean 有事件被发送成功时返回true
     */
    boolean dispatchEvents();
}
