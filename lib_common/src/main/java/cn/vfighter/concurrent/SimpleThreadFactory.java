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

import java.util.concurrent.ThreadFactory;

/**
 * 简单的线程工厂实现
 * 
 * @author Konlg
 */
public final class SimpleThreadFactory implements ThreadFactory {
    /**
     * 构造一个新 Thread。
     * 
     * @param run 由新线程实例所执行的可运行线程
     * @return 构造的线程，如果请求创建线程被拒绝，则返回 null
     */
    @Override
    public Thread newThread(Runnable run) {
        return new Thread(run);
    }
}
