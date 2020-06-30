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
 * 事件接收器
 * <p>
 * 接收从不同线程中传来的事件
 *
 * @param <E> 事件类型
 * @author Konlg
 */
public interface EventReceiver<E> {
    /**
     * 将事件添加到处理队列
     * 
     * @param event 要处理的事件
     */
    void putEvent(E event);
}
