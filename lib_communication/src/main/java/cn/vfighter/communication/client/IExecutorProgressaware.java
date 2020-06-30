/* Copyright (c) vfighter.cn, All Rights Reserved
 *             ____     __    __
 *   _  ______/ __/____/ / __/ /_________
 *  | |/ /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | / / / / / /__  / // // /_/ ___/ /
 *  |__/_/ /_/  __/ /_//_/ \__/\___/_/
 *             \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 * @date    2018年10月26日
 */

package cn.vfighter.communication.client;

/**
 * 可发布进度的执行器
 * 
 * @author Konlg
 * @param <T> 结果类型
 * @param <P> 进度消息类型
 */
public interface IExecutorProgressaware<T, P> extends IExecutor<T> {

    /**
     * 发布进度
     * 
     * @param progress
     * @return
     * @throws ExecuteException
     */
    T execute(IPublishProgress<P> progress) throws ExecuteException;
}
