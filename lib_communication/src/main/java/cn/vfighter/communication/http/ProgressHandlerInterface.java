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

package cn.vfighter.communication.http;

import org.apache.http.Header;

/**
 * 进度处理句柄规范
 * 
 * @author konlg
 */
public interface ProgressHandlerInterface {

    /**
     * 进度消息通知 具体实现可以用于更新进度条
     * 
     * @param bytesWritten 已经写入的字节数
     * @param bytesTotal 总字节数
     */
    void sendProgressMessage(int bytesWritten, int bytesTotal);

    /**
     * 如果请求执行完毕，但是发生了错误时，通知具体实现进行失败处理
     * 
     * @param statusCode 错误代码 HTTP State code
     * @param headers 请求的报文头
     * @param responseBody 响应的Body内容
     * @param error 导致错误的异常实例
     */
    void sendFailureMessage(int statusCode, Header[] headers, byte[] responseBody, Throwable error);

}
