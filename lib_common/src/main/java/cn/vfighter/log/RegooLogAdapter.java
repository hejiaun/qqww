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

package cn.vfighter.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.vfighter.util.StringTools;

/**
 * 默认的日志适配器(commons.logging)
 *
 * @author konlg
 */
public class RegooLogAdapter implements LogAdapter {
    private static Log trace = LogFactory.getLog("xingfu.trace");
    private static Log debug = LogFactory.getLog("xingfu.debug");
    private static Log info = LogFactory.getLog("xingfu.info");
    private static Log warn = LogFactory.getLog("xingfu.warn");
    private static Log error = LogFactory.getLog("xingfu.error");
    private static Log access = LogFactory.getLog("xingfu.accessLog");
    private static Log serviceStats = LogFactory.getLog("xingfu.serviceStatsLog");
    private static Log profileLogger = LogFactory.getLog("xingfu.profile");

    @Override
    public void trace(String msg) {
        trace.trace(msg);
    }

    @Override
    public void trace(String format, Object... argArray) {
        trace.trace(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public void debug(String msg) {
        debug.debug(msg);
    }

    @Override
    public void debug(String format, Object... argArray) {
        debug.debug(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public void debug(String msg, Throwable t) {
        debug.debug(msg, t);
    }

    @Override
    public void info(String msg) {
        info.info(msg);
    }

    @Override
    public void info(String format, Object... argArray) {
        // "{}"
        info.info(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public void info(String msg, Throwable t) {
        info.info(msg, t);
    }

    @Override
    public void warn(String msg) {
        warn.warn(msg);
    }

    @Override
    public void warn(String format, Object... argArray) {
        warn.warn(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public void warn(String msg, Throwable t) {
        warn.warn(msg, t);
    }

    @Override
    public void error(String msg) {
        error.error(msg);
    }

    @Override
    public void error(String format, Object... argArray) {
        error.error(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public void error(String msg, Throwable t) {
        error.error(msg, t);
    }

    @Override
    public void accessLog(String msg) {
        access.info(msg);
    }

    @Override
    public void accessStatsLog(String msg) {
        serviceStats.info(msg);
    }

    @Override
    public void accessStatsLog(String format, Object... argArray) {
        serviceStats.info(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public void accessProfileLog(String format, Object... argArray) {
        profileLogger.info(StringTools.arrayFormat(format, argArray));
    }

    @Override
    public boolean isTraceEnabled() {
        return trace.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return debug.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return info.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return warn.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return error.isErrorEnabled();
    }

    @Override
    public boolean isStatsEnabled() {
        return serviceStats.isInfoEnabled();
    }
}
