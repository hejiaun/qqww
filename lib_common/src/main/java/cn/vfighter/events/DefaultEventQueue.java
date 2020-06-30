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

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

import cn.vfighter.concurrent.ThreadPool;

/**
 * 事件队列
 * <p>
 * 接收消息存入队列并且在另一个线程进行处理
 *
 * @author Konlg
 */
public final class DefaultEventQueue<E> implements EventQueue<E> {
    /**
     * 是否允许 acquireControl 操作
     */
    private boolean autonomous;

    /**
     * 队列非空时为true
     */
    private volatile boolean notEmpty;

    /**
     * 线程池, 提供派发处理事件的线程
     */
    private ThreadPool threadPool;

    /**
     * 事件处理器, 处理派发的事件
     */
    private EventProcessor<E> eventProcessor;

    /**
     * 事件队列, 存储待处理事件
     */
    private ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<E>();

    /**
     * 事件队列的原子控制器
     */
    private AtomicReference<EventQueue<E>> atomicControl = new AtomicReference<EventQueue<E>>();

    /**
     * 事件处理任务
     */
    private Runnable processTask = new Runnable() {
        @Override
        public void run() {
            if (commandeer(DefaultEventQueue.this)) {
                while (true) {
                    E event = queue.peek();
                    if (event == null) {
                        atomicControl.set(null);
                        if (queue.peek() == null || !commandeer(DefaultEventQueue.this))
                            return;
                        if (queue.peek() == null) {
                            atomicControl.set(null);
                            return;
                        }
                    }
                    notEmpty = false;
                    eventProcessor.haveEvents();
                }
            }
        }
    };

    /**
     * 创建一个事件队列
     * 
     * @param threadPool 线程管理器
     * @param autonomous 是否允许 acquireControl 方法
     */
    public DefaultEventQueue(ThreadPool threadPool, boolean autonomous) {
        this.threadPool = threadPool;
        this.autonomous = autonomous;
    }

    /**
     * 控制队列
     *
     * @param eventQueue 要控制的队列
     * @return 控制成功返回true
     */
    private boolean commandeer(EventQueue<E> eventQueue) {
        boolean result = false;
        if (atomicControl.get() == null) {
            result = atomicControl.compareAndSet(null, eventQueue);
        }
        return result;
    }

    @Override
    public boolean acquireControl(EventQueue<E> eventQueue) {
        boolean result = false;
        if (!autonomous) {
            if (commandeer(eventQueue.getController())) {
                notEmpty = false;
                result = true;
            }
        }
        return result;
    }

    @Override
    public void relinquishControl() {
        EventQueue<E> c = atomicControl.get();
        if (c == this) {
            return;
        }
        atomicControl.set(null);
        if (notEmpty) {
            threadPool.execute(processTask);
        }
    }

    @Override
    public EventQueue<E> getController() {
        EventQueue<E> result = atomicControl.get();
        if (result == null) {
            result = this;
        }
        return result;
    }

    @Override
    public void setEventProcessor(EventProcessor<E> eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public boolean isEmpty() {
        return queue.peek() == null;
    }

    @Override
    public void putEvent(E event) {
        queue.offer(event);
        notEmpty = true;
        if (atomicControl.get() == null) {
            threadPool.execute(processTask);
        }
    }

    @Override
    public boolean dispatchEvents() {
        boolean result = false;

        E event = queue.poll();
        if (event != null) {
            result = true;
        }
        while (event != null) {
            eventProcessor.processEvent(event);
            event = queue.poll();
        }

        return result;
    }
}
