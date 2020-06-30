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
 * htt client客户端执行器
 * 
 * @author Konlg
 * @param <T> 执行返回的结果类型
 */
public interface IExecutor<T> {

    /**
     * 执行请求，获得结果
     * 
     * @return 结果
     * @throws ExecuteException 执行过程发生异常
     */
    T execute() throws ExecuteException;
}
