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

package cn.vfighter.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

/**
 * 一个基于无锁队列的线程池实现
 *
 * @author Konlg
 */
public final class DefaultThreadPool implements ThreadPool {

    /**
     * 线程协调信号
     */
    private final Semaphore taskSemaphore = new Semaphore(0);

    /**
     * 待处理任务列表
     */
    private final ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<Runnable>();

    /**
     * 是否关闭。
     */
    private Boolean closing = false;

    /**
     * 线程数量
     */
    private int threadsCount;

    /**
     * 工作线程组
     */
    private Thread threads[] = null;

    /**
     * 创建一个线程池
     */
    public DefaultThreadPool() {
    }

    /**
     * 创建一个线程池
     *
     * @param threadsCount 线程数量。
     */
    public DefaultThreadPool(int threadsCount) {
        this(threadsCount, new SimpleThreadFactory());
    }

    /**
     * 创建线程池。
     *
     * @param threadsCount  线程数量。
     * @param threadFactory 线程工厂。
     * @return 线程池。
     */
    public DefaultThreadPool(int threadsCount, ThreadFactory threadFactory) {
        this.start(threadsCount, threadFactory);
    }

    /**
     * 创建和启动一个并行任务。
     *
     * @param threadsCount  任务的并发数量。
     * @param threadFactory 创建工厂。
     */
    @Override
    final public void start(int threadsCount, ThreadFactory threadFactory) {
        this.threadsCount = threadsCount;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        taskSemaphore.acquire();
                        if (closing) {
                            return;
                        }
                        final Runnable task = tasks.poll();
                        if (task != null) {
                            try {
                                task.run();
                            } catch (final Throwable t) {
                                System.out.println(t.getMessage());
                                // logger
                                // Exception thrown by a task's run method
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                }
            }
        };

        this.threads = new Thread[this.threadsCount];

        // 创建并启动所有线程
        for (int i = 0; i < threadsCount; i++) {
            Thread t = threadFactory.newThread(runnable);
            this.threads[i] = t;
            t.start();
        }
    }

    /**
     * 开始运行任务。
     *
     * @param runnable 运行方法。
     */
    @Override
    final public void execute(Runnable task) {
        this.tasks.add(task);
        this.taskSemaphore.release();
    }

    /**
     * 任务完成时停止线程。
     */
    @Override
    final public void close() {

        closing = true;
        this.taskSemaphore.release(threadsCount);
        Thread ct = Thread.currentThread();

        for (Thread t : this.threads) {
            if (ct != t) {
                t.interrupt();
            }
        }

        for (Thread t : this.threads) {
            if (ct != t) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
            }
        }

        // 释放线程数组
        this.threads = null;
    }
}
