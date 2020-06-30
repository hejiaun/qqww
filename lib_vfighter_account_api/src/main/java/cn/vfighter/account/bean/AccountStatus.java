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

package cn.vfighter.account.bean;

/**
 * 帐号状态
 * 
 * @author konlg
 */
public enum AccountStatus {
    /** 未知状态 */
    UNKNOWN(-1),

    /** 初始化完成（注册完成） */
    INITIALZED(0),

    /** 验证完成 */
    VERIFIED(1),

    /** 审核不通过 */
    UNQUALIFIED(2),

    /** 审核通过 */
    QUALIFIED(3),

    /** 锁定状态 */
    LOGIN_LOCKED(4),

    /** 禁用状态 */
    FORBIDDEN(5);

    private short value;

    private AccountStatus(int v) {
        this.value = (short) v;
    }

    /**
     * 获取枚举值
     * 
     * @return 代表审核状态的枚举值
     */
    public short getValue() {
        return this.value;
    }

    /**
     * 将数值转换为枚举
     * 
     * @param v
     * @return
     */
    public static AccountStatus valueOf(int v) {
        AccountStatus result = AccountStatus.UNKNOWN;
        switch (v) {
        case -1: {
            result = UNKNOWN;
            break;
        }
        case 0: {
            result = INITIALZED;
            break;
        }
        case 1: {
            result = VERIFIED;
            break;
        }
        case 2: {
            result = UNQUALIFIED;
            break;
        }
        case 3: {
            result = QUALIFIED;
            break;
        }
        case 4: {
            result = LOGIN_LOCKED;
            break;
        }
        case 5: {
            result = FORBIDDEN;
            break;
        }
        default: {
            throw new IllegalArgumentException("args is: " + v);
        }
        }
        return result;
    }
}
