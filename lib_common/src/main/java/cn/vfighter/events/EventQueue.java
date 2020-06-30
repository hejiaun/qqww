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
 * 事件队列
 * <p>
 * 接收事件, 使用另一个线程处理事件
 * 
 * @param <E> 事件类型
 * @author Konlg
 */
public interface EventQueue<E> extends EventDispatcher<E>, EventReceiver<E> {
    /**
     * 请求控制队列
     *
     * @param eventQueue 事件队列
     * @return 成功返回 True
     */
    boolean acquireControl(EventQueue<E> eventQueue);

    /**
     * 放弃控制队列
     */
    void relinquishControl();

    /**
     * 获取控制队列
     *
     * @return 事件队列
     */
    EventQueue<E> getController();
}
