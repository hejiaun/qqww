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
 * 包定义的常量
 * 
 * @author konlg
 */
public class PacketConstants {

    /**
     * 报文头起始位
     */
    public final static byte SOH = 1;

    /**
     * 魔法数字
     */
    public final static int MAGICNUMBER = 0x1234;

    /**
     * 主版本号
     */
    public final static byte VER_MAJOR = 1;

    public final static byte VER_MINOR = 0;

    /**
     * 加密类型
     */
    public final static byte ENCODETYPE_NONE = 0;

    /**
     * 包结构起始位
     */
    public static final byte STX = 0x02;

    /**
     * 包结构结束位
     */
    public static final byte ETX = 0x03;

    /**
     * 取消操作标志
     */
    public static final byte[] CTX = new byte[] { 0xF, 0x0, 0xF, 0x00 };

    private PacketConstants() {
    }

}