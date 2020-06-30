package example.common_base.util;

import android.app.Activity;

import java.io.InputStream;

import me.wcy.lrcview.LrcView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class LrcUtil {
    private static LrcUtil lrcUtil = null;

    private LrcUtil() {

    }

    public static LrcUtil getInstence() {
        if (lrcUtil == null) {
            synchronized (LrcView.class) {
                if (lrcUtil == null) {
                    lrcUtil = new LrcUtil();
                }
            }
        }
        return lrcUtil;
    }

    /**
     * 获取歌词文本
     *
     * @return
     */
    public String getLrcText(String fileName, Activity activity) {
        String lrcText = "";
        try {
            //获取流
            InputStream is = activity.getAssets().open(fileName);
            //创建和流一样大小的字节数组
            byte[] bytes = new byte[is.available()];
            //将流读取到字节数组
            is.read(bytes);
            //将字节数组转为字符串
            lrcText = new String(bytes);
        } catch (Exception e) {
        }
        return lrcText;
    }
}
