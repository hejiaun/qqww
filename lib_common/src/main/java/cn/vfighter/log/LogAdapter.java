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

package cn.vfighter.log;

/**
 * log适配器。方便适配不同的log方式和配置。
 * 
 * @author konlg
 */
public interface LogAdapter {
    /**
     * 输出运行轨迹
     * 
     * @param msg
     */
    void trace(String msg);

    /**
     * 输出运行轨迹
     * 
     * @param format
     * @param argArray
     */
    void trace(String format, Object... argArray);

    /**
     * 输出调试信息
     * 
     * @param msg
     */
    void debug(String msg);

    /**
     * 输出调试信息
     * 
     * @param format
     * @param argArray
     */
    void debug(String format, Object... argArray);

    /**
     * 输出调试信息
     * 
     * @param msg
     * @param t
     */
    void debug(String msg, Throwable t);

    /**
     * 输出普通信息
     * 
     * @param msg
     */
    void info(String msg);

    /**
     * 输出普通信息
     * 
     * @param format
     * @param argArray
     */
    void info(String format, Object... argArray);

    /**
     * 输出普通信息
     * 
     * @param msg
     * @param t
     */
    void info(String msg, Throwable t);

    /**
     * 输出警告信息
     * 
     * @param msg
     */
    void warn(String msg);

    /**
     * 输出警告信息
     * 
     * @param format
     * @param argArray
     */
    void warn(String format, Object... argArray);

    /**
     * 输出警告信息
     * 
     * @param msg
     * @param t
     */
    void warn(String msg, Throwable t);

    /**
     * 输出错误信息
     * 
     * @param msg
     */
    void error(String msg);

    /**
     * 输出错误信息
     * 
     * @param format
     * @param argArray
     */
    void error(String format, Object... argArray);

    /**
     * 输出错误信息
     * 
     * @param msg
     * @param t
     */
    void error(String msg, Throwable t);

    /**
     * 输出操作日志
     * 
     * @param msg
     */
    void accessLog(String msg);

    /**
     * 输出配置日志
     * 
     * @param format
     * @param argArray
     */
    void accessProfileLog(String format, Object... argArray);

    /**
     * 输出统计日志
     * 
     * @param msg
     */
    void accessStatsLog(String msg);

    /**
     * 输出统计日志
     * 
     * @param format
     * @param argArray
     */
    void accessStatsLog(String format, Object... argArray);

    /**
     * 是否启用轨迹日志
     * 
     * @return
     */
    boolean isTraceEnabled();

    /**
     * 是否启用调试信息日志
     * 
     * @return
     */
    boolean isDebugEnabled();

    /**
     * 是否启用普通信息日志
     * 
     * @return
     */
    boolean isInfoEnabled();

    /**
     * 是否启用警告日志
     * 
     * @return
     */
    boolean isWarnEnabled();

    /**
     * 是否启用错误日志
     * 
     * @return
     */
    boolean isErrorEnabled();

    /**
     * 是否启用统计日志
     * 
     * @return
     */
    boolean isStatsEnabled();
}
