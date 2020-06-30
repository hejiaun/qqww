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
 * 结果处理回调
 * 
 * @author Konlg
 * @param <R> 结果实体类型
 */
public interface IResultHandler<R> {

    /**
     * 处理结果
     * 
     * @param result
     */
    void onResult(IExecutor<R> executor, R result);

    /**
     * 发生错误
     * 
     * @param e
     */
    void onError(Exception e);
}
