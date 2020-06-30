package example.common_base.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:SharePreference工具类
 */
public class SpUtil {
    private static SpUtil spUtil = null;

    private SpUtil() {
    }

    public static SpUtil getInstence() {
        if (spUtil == null) {
            synchronized (SpUtil.class) {
                if (spUtil == null) {
                    spUtil = new SpUtil();
                }
            }
        }
        return spUtil;
    }


    /**
     * 保存布尔值
     *
     * @param context
     * @param spKey
     * @param valueKey
     * @param value
     */
    public void saveBoolean(Context context, String spKey, String valueKey, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences(spKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(valueKey, value);
        edit.commit();
    }

    public void getString(Context context, String spKey, String valueKey, int value) {
        SharedPreferences sp = context.getSharedPreferences(spKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(valueKey, value);
        edit.commit();
    }

    /**
     * 保存整数值
     *
     * @param context
     * @param spKey
     * @param valueKey
     * @param value
     */
    public void saveInt(Context context, String spKey, String valueKey, int value) {
        SharedPreferences sp = context.getSharedPreferences(spKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(valueKey, value);
        edit.commit();
    }


    /**
     * 获取int值
     *
     * @param context
     * @param spKey
     * @param valueKey
     * @return
     */
    public int getInt(Context context, String spKey, String valueKey) {
        SharedPreferences sp = context.getSharedPreferences(spKey, Context.MODE_PRIVATE);
        return sp.getInt(valueKey, 0);
    }


}
