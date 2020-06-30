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
 */

package cn.vfighter.communication.packet;

/**
 * 包的类型
 * 
 * @author Konlg
 */
public enum PacketType {

    /**
     * 普通文本
     */
    STRING,

    /**
     * JSON 文本
     */
    JSON,

    /**
     * 二进制数据流
     */
    BINARY;
}