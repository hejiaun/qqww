package example.common_base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:时间工具类
 */
public class TimeUtil {
    private static TimeUtil timeUtil = null;

    private TimeUtil() {

    }

    public static TimeUtil getInstence() {
        if (timeUtil == null) {
            synchronized (TimeUtil.class) {
                if (timeUtil == null) {
                    timeUtil = new TimeUtil();
                }
            }
        }
        return timeUtil;
    }

    /**
     * 获取当前时间秒数
     *
     * @return
     */
    public long getSecondTime() {
        return System.currentTimeMillis() / 1000;
    }

    public String secondTime2String(long secondTime) {
        String result = null;
        Date date = new Date();
        date.setTime(secondTime * 1000);
//        long compareSecond = new Date().getTime() / 1000 - secondTime;
//        if (compareSecond >= 365 * 24 * 60) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        result = dateFormat.format(date);
//        } else if (compareSecond) {
//
//        }
        return result;
    }

}
