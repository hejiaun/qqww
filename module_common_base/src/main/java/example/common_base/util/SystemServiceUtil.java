package example.common_base.util;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  手机设备服务工具类
 */
public class
SystemServiceUtil {
    private static SystemServiceUtil deviceUtil = null;

    public static SystemServiceUtil getInstence() {
        if (deviceUtil == null) {
            synchronized (SystemServiceUtil.class) {
                if (deviceUtil == null) {
                    deviceUtil = new SystemServiceUtil();
                }
            }
        }
        return deviceUtil;
    }

    /**
     * 调用手机震动
     *
     * @param time 时长/毫秒
     */
    public void vibrateForTime(Context context, int time) {
        //调用手机震动器短震
        Vibrator vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }


}
