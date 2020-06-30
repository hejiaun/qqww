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
 * 发布进度内容
 * 
 * @author Konlg
 * @param <PROGRESS>
 */
public interface IPublishProgress<PROGRESS> {

    /**
     * 发布进度消息
     * 
     * @param progress
     */
    void progress(PROGRESS... progress);

}
