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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import cn.vfighter.concurrent.ThreadPool;
import cn.vfighter.events.DefaultEventQueue;
import cn.vfighter.events.EventProcessor;
import cn.vfighter.events.EventQueue;

/**
 * 缓冲事件队列
 *
 * @param <E> 事件类型
 * @author Konlg
 */
public final class DefaultBufferedEventsQueue<E> implements BufferedEventsQueue<E> {
    /**
     * 实际调度处理的事件队列
     */
    private EventQueue<Collection<E>> eventQueue;

    /**
     * 事件处理器
     */
    EventProcessor<E> eventProcessor;

    /**
     * 初始缓冲容量
     */
    private int initialBufferCapacity = 10;

    /**
     * 挂起的事件缓存
     */
    private HashMap<BufferedEventsReceiver<E>, Collection<E>> pending = new HashMap<BufferedEventsReceiver<E>, Collection<E>>();

    /**
     * 创建一个缓冲事件队列
     * 
     * @param eventQueue 实际调度处理的事件队列
     */
    public DefaultBufferedEventsQueue(EventQueue<Collection<E>> eventQueue) {
        this.eventQueue = eventQueue;
        eventQueue.setEventProcessor(new EventProcessor<Collection<E>>() {
            @Override
            public void processEvent(Collection<E> bufferedEvents) {
                if (bufferedEvents.size() > 0) {
                    for (E event : bufferedEvents) {
                        eventProcessor.processEvent(event);
                    }
                }
            }

            @Override
            public void haveEvents() {
                eventProcessor.haveEvents();
            }
        });
    }

    /**
     * 创建一个缓冲事件队列
     * 
     * @param threadPool 线程管理器
     * @param autonomous 是否允许 setControl 方法
     */
    public DefaultBufferedEventsQueue(ThreadPool threadPool, boolean autonomous) {
        this(new DefaultEventQueue<Collection<E>>(threadPool, autonomous));
    }

    @Override
    public void setBufferCapacity(int bufferCapacity) {
        this.initialBufferCapacity = bufferCapacity;
    }

    @Override
    public void send(BufferedEventsReceiver<E> destination, E event) {
        Collection<E> bufferedEvents = pending.get(destination);
        if (bufferedEvents == null) {
            bufferedEvents = new ArrayList<E>(initialBufferCapacity);
            pending.put(destination, bufferedEvents);
        }
        bufferedEvents.add(event);
    }

    @Override
    public void sendPendingEvents() {
        if (isEmpty() && !pending.isEmpty()) {
            Iterator<BufferedEventsReceiver<E>> it = pending.keySet().iterator();
            while (it.hasNext()) {
                BufferedEventsReceiver<E> destination = it.next();
                Collection<E> bufferedEvents = pending.get(destination);
                destination.putBufferedEvents(bufferedEvents);
            }
            pending.clear();
        }
    }

    @Override
    public void putBufferedEvents(Collection<E> bufferedEvents) {
        eventQueue.putEvent(bufferedEvents);
    }

    @Override
    public void putEvent(E event) {
        ArrayList<E> bufferedEvents = new ArrayList<E>(1);
        bufferedEvents.add(event);
        eventQueue.putEvent(bufferedEvents);
    }

    @Override
    public void setEventProcessor(EventProcessor<E> eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    @Override
    public boolean dispatchEvents() {
        if (eventQueue.dispatchEvents()) {
            sendPendingEvents();
            return true;
        }
        return false;
    }

    @Override
    public EventQueue<Collection<E>> getEventQueue() {
        return eventQueue;
    }
}
