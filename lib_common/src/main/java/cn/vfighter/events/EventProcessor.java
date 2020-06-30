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
 * 事件处理器
 * <p>
 * 接收并处理事件
 *
 * @param <E> 事件类型
 * @author Konlg
 */
public interface EventProcessor<E> {
    /**
     * 通知有待处理的事件
     */
    void haveEvents();

    /**
     * 处理事件
     *
     * @param event 要处理的事件
     */
    void processEvent(E event);
}
