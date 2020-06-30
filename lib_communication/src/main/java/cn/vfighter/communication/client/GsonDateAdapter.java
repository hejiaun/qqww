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

package cn.vfighter.communication.client;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 日期 GSON 序列化适配器 Date <==> CST
 * 
 * @author Konlg
 */
public class GsonDateAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

        if (src != null) {
            return new JsonPrimitive(src.getTime());
        }

        return JsonNull.INSTANCE;
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        if (json == null) {
            return null;
        }
        if (StringUtils.isNumeric(json.getAsString())) {
            return new Date(json.getAsLong());
        }
        try {
            long time = json.getAsLong();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            return cal.getTime();
        } catch (Exception e) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse(json.getAsString());

            } catch (ParseException e1) {
                throw new JsonParseException("日期格式不对，既不是时间戳，也不是yyyy-MM-dd HH:mm:ss格式字符", e1);
            }
        }

    }

}
