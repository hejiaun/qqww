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

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * 抽象执行器
 *
 * @param <T>
 * @author konlg
 */
public abstract class AbstractExecutor<T> implements IExecutor<T> {

    /**
     * 配置指向的接入域名命名 报文头
     * 
     * @return
     */
    protected final Header namespaceHeader() {
        return new BasicHeader(JsonServiceClientExecutor.HEADER_NS,
                NameSpaceConfig.instance.getNamespace());
    }
}
