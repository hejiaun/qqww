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

package cn.vfighter.bean;

/**
 * 终端类型
 * 
 * @author konlg
 */
public interface TerminalType {
    /**
     * Html 5
     */
    int HTML5 = 0;

    /**
     * Android
     */
    int ANDROID = 1;

    /**
     * IOS
     */
    int IOS = 2;

    /**
     * PC Web
     */
    int PC_WEB = 3;

    /**
     * PC Client
     */
    int PC_CLIENT = 4;
}
