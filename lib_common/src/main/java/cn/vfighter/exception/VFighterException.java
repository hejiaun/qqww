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

import java.util.ArrayList;
import java.util.List;

/**
 * 带有错误编号的异常
 * 
 * @author Konlg
 */
public class VFighterException extends RuntimeException {
    private static final long serialVersionUID = 619049710632160554L;

    ////////////////////////////////////////
    //
    /** 未知异常 */
    public static final short ERROR_UNKNOWN = 101;

    ////////////////////////////////////////
    // 底层异常 操作系统级异常 110 - 199
    /** 操作系统级异常 */
    public static final short ERROR_OS = 110;
    /** 资源异常 */
    public static final short ERROR_OS_RES = 120;
    /** IO异常 */
    public static final short ERROR_OS_IO = 130;
    /** 文件异常 */
    public static final short ERROR_OS_IO_FILE = 131;
    /** 路径异常 */
    public static final short ERROR_OS_IO_PATH = 132;
    /** 流异常 */
    public static final short ERROR_OS_IO_STREAM = 133;
    /***/
    public static final short ERROR_OS_IO_PROPERTY = 134;

    /** 网络异常 */
    public static final short ERROR_OS_NET = 140;
    /** 数据链接异常 */
    public static final short ERROR_OS_DBC = 150;

    ////////////////////////////////////////
    // 底层异常 框架异常 200 - 999
    /** 框架异常 */
    public static final short ERROR_FW = 200;
    /** 未找到异常 */
    public static final short ERROR_UNF = 201;

    /** 编解码异常 */
    public static final short ERROR_FW_CODEC = 210;
    /** 编码异常 */
    public static final short ERROR_FW_ENCODE = 211;
    /** 解码异常 */
    public static final short ERROR_FW_DECODE = 212;

    /** 扩展异常 */
    public static final short ERROR_FW_EXT = 220;

    ////////////////////////////////////////
    // 数据库操作异常 1000 - 1999
    public static final short ERROR_PER = 1000;
    /** 添加记录异常 */
    public static final short ERROR_PER_ADD = 1001;
    /** 删除记录异常 */
    public static final short ERROR_PER_DELETE = 1002;
    /** 查询记录异常 */
    public static final short ERROR_PER_QUERY = 1003;
    /** 更新记录异常 */
    public static final short ERROR_PER_UPDATE = 1004;
    /** 记录已经存在异常 */
    public static final short ERROR_PER_EXISTED = 1005;
    /** 记录不存在异常 */
    public static final short ERROR_PER_NOTEXIST = 1006;
    /** 输入的原数据有误 */
    public static final short ERROR_PER_SOURCE = 1007;

    ////////////////////////////////////////
    // 服务异常 2000 - 2999
    public static final short ERROR_SER = 2000;

    ////////////////////////////////////////
    // 业务异常 3000 - 9999
    public static final short ERROR_BIZ = 3000;

    public static final short ERROR_BIZ_DOMAIN = 3001;

    ////////////////////////////////////////
    // 用户异常 10000 - 32000
    public static final short ERROR_USR = 10000;

    private String category = "";
    private short code = ERROR_UNKNOWN;
    private List<Object> paramsList = new ArrayList<Object>();

    public VFighterException(short code) {
        super();
        this.code = code;
    }

    /**
     * 创建一个 {@link VFighterException}。
     * 
     * @param message 异常消息
     */
    public VFighterException(String message) {
        super(message, null);
    }

    /**
     * 创建一个{@link VFighterException}。
     * 
     * @param code 错误代码
     * @param message 异常消息
     */
    public VFighterException(short code, String message) {
        super(message, null);
        this.code = code;
    }

    /**
     * 创建一个{@link VFighterException}。
     * 
     * @param code 错误代码
     * @param cause 异常的原因
     */
    public VFighterException(short code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    /**
     * 创建一个{@link VFighterException}。
     * 
     * @param code 错误代码
     * @param message 异常消息
     * @param cause 异常的原因
     */
    public VFighterException(short code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 创建一个{@link VFighterException}。
     * 
     * @param code 错误代码
     * @param message 异常消息
     * @param cause 异常的原因
     * @param params 异常的参数
     */
    public VFighterException(short code, String message, Throwable cause, Object... params) {
        super(message, cause);
        this.code = code;
        if (params != null && params.length > 0) {
            for (Object param : params) {
                this.addParam(param);
            }
        } else {
            if (cause != null) {
                this.addParam(cause.getMessage());
            }
        }
    }

    /**
     * 获取 错误代码。
     * 
     * @return
     */
    public short getCode() {
        return this.code;
    }

    /**
     * 添加异常参数
     * 
     * @param value 异常参数值
     */
    public void addParam(Object value) {
        paramsList.add(value);
    }

    /**
     * 获取 异常参数
     * <p>
     * 用于对异常消息的格式化
     *
     * @return paramsMap 异常参数
     */
    public Object[] getParams() {
        return paramsList.toArray();
    }

    /**
     * 获取 错误分类
     *
     * @return category 错误分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置 错误分类
     *
     * @param category 要设置的 错误分类
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 创建一个未知异常
     * 
     * @param cause 异常的原因
     * @param params 异常的参数
     * @return
     */
    public static VFighterException unknown(Throwable cause, Object... params) {
        return new VFighterException(ERROR_UNKNOWN, cause);
    }
}
