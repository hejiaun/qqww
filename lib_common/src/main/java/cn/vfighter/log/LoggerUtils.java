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
 * 日志工具
 * 
 * @author konlg
 */
public class LoggerUtils {
    private LogAdapter log;
    private static LoggerUtils instance = null;

    public LoggerUtils() {
        this(new RegooLogAdapter());
    }

    public LoggerUtils(LogAdapter log) {
        this.log = log;
        LoggerUtils.instance = this;
    }

    /** 获取实例 */
    public static LoggerUtils get() {
        if (LoggerUtils.instance == null) {
            new LoggerUtils();
        }
        return LoggerUtils.instance;
    }

    /**
     * 是否启用轨迹日志
     * 
     * @return
     */
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    /**
     * 是否启用调试信息日志
     * 
     * @return
     */
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    /**
     * 是否启用警告日志
     * 
     * @return
     */
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    /**
     * 是否启用错误日志
     * 
     * @return
     */
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    /**
     * 是否启用统计日志
     * 
     * @return
     */
    public boolean isStatsEnabled() {
        return log.isStatsEnabled();
    }

    /**
     * 输出运行轨迹
     * 
     * @param msg
     */
    public void trace(String msg) {
        log.trace(msg);
    }

    /**
     * 输出调试信息
     * 
     * @param msg
     */
    public void debug(String msg) {
        log.debug(msg);
    }

    /**
     * 输出调试信息
     * 
     * @param format
     * @param argArray
     */
    public void debug(String format, Object... argArray) {
        log.debug(format, argArray);
    }

    /**
     * 输出调试信息
     * 
     * @param msg
     * @param t
     */
    public void debug(String msg, Throwable t) {
        log.debug(msg, t);
    }

    /**
     * 输出普通信息
     * 
     * @param msg
     */
    public void info(String msg) {
        log.info(msg);
    }

    /**
     * 输出普通信息
     * 
     * @param format
     * @param argArray
     */
    public void info(String format, Object... argArray) {
        log.info(format, argArray);
    }

    /**
     * 输出普通信息
     * 
     * @param msg
     * @param t
     */
    public void info(String msg, Throwable t) {
        log.info(msg, t);
    }

    /**
     * 输出警告信息
     * 
     * @param msg
     */
    public void warn(String msg) {
        log.warn(msg);
    }

    /**
     * 输出警告信息
     * 
     * @param format
     * @param argArray
     */
    public void warn(String format, Object... argArray) {
        log.warn(format, argArray);
    }

    /**
     * 输出警告信息
     * 
     * @param msg
     * @param t
     */
    public void warn(String msg, Throwable t) {
        log.warn(msg, t);
    }

    /**
     * 输出错误信息
     * 
     * @param msg
     */
    public void error(String msg) {
        log.error(msg);
    }

    /**
     * 输出错误信息
     * 
     * @param format
     * @param argArray
     */
    public void error(String format, Object... argArray) {
        log.error(format, argArray);
    }

    /**
     * 输出错误信息
     * 
     * @param msg
     * @param t
     */
    public void error(String msg, Throwable t) {
        log.error(msg, t);
    }

    /**
     * 输出操作日志
     * 
     * @param msg
     */
    public void accessLog(String msg) {
        log.accessLog(msg);
    }

    /**
     * 输出统计日志
     * 
     * @param msg
     */
    public void accessStatsLog(String msg) {
        log.accessStatsLog(msg);
    }

    /**
     * 输出统计日志
     * 
     * @param format
     * @param argArray
     */
    public void accessStatsLog(String format, Object... argArray) {
        log.accessStatsLog(format, argArray);
    }

    /**
     * 输出配置日志
     * 
     * @param format
     * @param argArray
     */
    public void accessProfileLog(String format, Object... argArray) {
        log.accessProfileLog(format, argArray);
    }
}
