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

/**
 * 封装JSON值为二进制流的实体规范。
 * 
 * @author konlg
 */
public interface JsonValueInterface {

    /**
     * 返回封装了JSON对象后，可以用于实体的二进制数组
     * 
     * @return JSON对象序列化为二进制数组
     */
    byte[] getEscapedJsonValue();
}
