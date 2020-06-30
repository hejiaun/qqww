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

package cn.vfighter.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列号工厂
 * 
 * @author konlg
 */
public enum SerialNumberFactory {
    SingleTon;

    private static final AtomicInteger bidCounter = new AtomicInteger(0);
    private static final AtomicInteger sidCounter = new AtomicInteger(0);

    private SerialNumberFactory() {
        //
    }

    public static SerialNumberFactory get() {
        return SerialNumberFactory.SingleTon;
    }

    /**
     * 创建业务ID
     * 
     * @param appid 应用编号
     * @param endtype 终端类型
     * @return 业务编号
     */
    public String createBID(String appid, String endtype) {
        StringBuilder result = new StringBuilder(appid);
        result.append(endtype);

        Date date = new Date();

        result.append(new SimpleDateFormat("MMdd").format(date));
        result.append(new SimpleDateFormat("HHmmssSSSS").format(date));

        int value = 0;
        if ((value = bidCounter.incrementAndGet()) > 9998) {
            bidCounter.set(0);
        }

        result.append(String.format("%04d", value));

        return result.toString();
    }

    /**
     * 创建ID
     * 
     * @param p
     * @return
     */
    public long createSID(/* byte p */) {
        byte p = 22;    // 默认前置
        long nextSid = 0; // 生成结果
        nextSid = (System.currentTimeMillis() << 22) >>> 8; // 先操作时间
        nextSid = nextSid | ((long) (p) << 55); // 再操作前置

        int value = 0;
        if ((value = sidCounter.incrementAndGet()) > Short.MAX_VALUE) {
            sidCounter.set(0);
        }

        nextSid = nextSid | value;  // 最后把序号加入进来
        return nextSid;
    }
}
