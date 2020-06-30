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
 * @date    2018年10月26日
 */

package cn.vfighter.communication.utils;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author konlg
 */
public enum GsonFactory {

    SingleTon;

    private GsonBuilder builder;

    private GsonFactory() {
        builder = new GsonBuilder();
        // 使用自定义解析
        builder.registerTypeAdapter(Date.class, new GsonDateAdapter());
        builder.disableHtmlEscaping();
        builder.serializeSpecialFloatingPointValues();
    }

    public Gson create() {
        return builder.create();
    }

    public void registerTypeAdapter(Type type, Object typeAdapter) {
        builder.registerTypeAdapter(type, typeAdapter);
    }

    public void setExclusionStrategies(ExclusionStrategy... strategies) {
        builder.setExclusionStrategies(strategies);
    }

    public static Gson get() {
        return GsonFactory.SingleTon.create();
    }
}
