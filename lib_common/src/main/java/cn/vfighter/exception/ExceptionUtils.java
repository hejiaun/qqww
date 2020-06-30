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

package cn.vfighter.exception;

/**
 * 异常工具集
 * 
 * @author konlg
 */
public class ExceptionUtils {
    /**
     * 判定是否是业务方的逻辑抛出的异常
     * 
     * <pre>
     *      true: 来自业务方的异常
     *      false: 来自框架本身的异常
     * </pre>
     * 
     * @param e
     * @return
     */
    public static boolean isBizException(Exception e) {
        return e instanceof BizException;
    }

    /**
     * 是否是框架包装过的异常
     * 
     * @param e
     * @return
     */
    public static boolean isXingfuException(Exception e) {
        return e instanceof VFighterException;
    }

    /** 判断是否是框架的核心异常 */
    public static boolean isFrameworkException(Exception e) {
        return e instanceof FrameworkException;
    }
}
