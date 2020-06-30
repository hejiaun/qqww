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

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * 拥有固定数量的简易线程池 用足够简易的API完成线程池的功能 用于处理可运行的任务集合。
 * 
 * @author Konlg
 */
public interface ThreadPool extends Executor {
    /**
     * 创建和启动一个并行任务。
     *
     * @param threadCount 任务的并发数量。
     * @param threadFactory 创建工厂。
     */
    void start(int threadCount, ThreadFactory threadFactory);

    /**
     * 任务完成时停止线程。
     */
    void close();
}
