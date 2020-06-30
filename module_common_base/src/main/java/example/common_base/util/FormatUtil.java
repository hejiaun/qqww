package example.common_base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  格式工具类
 */
public class FormatUtil {
    private static FormatUtil formatUtil = null;

    public static FormatUtil getInstence() {
        if (formatUtil == null) {
            synchronized (FormatUtil.class) {
                if (formatUtil == null) {
                    formatUtil = new FormatUtil();
                }
            }
        }
        return formatUtil;
    }

    /**
     * 转日期(Date)对象为 年月日格式(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public String date2YMD(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 秒(long)转为   分:秒 格式(String)
     *
     * @param second 秒
     */
    public String second2MS(long second) {
        int resultSecond = (int) (second % 60);
        int resultMinute = (int) second / 60;
        String result;
        result = resultMinute < 10 ? "0" + resultMinute : resultMinute + "";
        result += ":";
        result += resultSecond < 10 ? "0" + resultSecond : resultSecond + "";
        return result;
    }

}
