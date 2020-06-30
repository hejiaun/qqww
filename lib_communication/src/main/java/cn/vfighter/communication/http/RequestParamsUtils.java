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

package cn.vfighter.communication.http;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;

/**
 * 表单参数工具
 * 
 * @author konlg
 */
public abstract class RequestParamsUtils {

    private static final Map<Class<?>, Map<Field, Method>> memberOfType = new HashMap<Class<?>, Map<Field, Method>>();

    public RequestParamsUtils() {
    }

    /**
     * 将一个POJO转换为FORM编码的实体
     * 
     * @param param POJO
     * @return HTTP实体
     */
    public HttpEntity convert(Object param) {
        try {
            return param(param).getEntity(null);
        } catch (IOException e) {
            throw new IllegalStateException("convert param to RequestParams failure.", e);
        }
    }

    /**
     * 将一个POJO转换为参数实体
     * 
     * @param param POJO
     * @return 参数实体
     */
    public RequestParams param(Object param) {
        try {
            Map<Field, Method> members = retrieveMember(param.getClass());
            RequestParams result = new RequestParams();
            result.setUseJsonStreamer(false);
            for (Entry<Field, Method> member : members.entrySet()) {
                if (member.getKey().getName().equals("serialVersionUID"))
                    continue;
                result.put(member.getKey().getName(),
                        convertString(member.getValue().invoke(param)));
            }
            return result;
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalStateException("convert param to RequestParams failure.", e);
        }
    }

    private String convertString(Object invoke) {
        if (invoke == null) {
            return null;
        }
        if (invoke instanceof String)
            return (String) invoke;
        if (invoke instanceof Date) {
            return convertDateToString((Date) invoke);
        }
        return invoke + "";
    }

    /**
     * 将日期参数转化为字符串
     * 
     * @param invoke 待转换的时间
     * @return 日期表达式
     */
    protected abstract String convertDateToString(Date invoke);

    public Map<Field, Method> retrieveMember(Class<? extends Object> class1)
            throws NoSuchMethodException {
        if (memberOfType.containsKey(class1))
            return memberOfType.get(class1);

        Map<Field, Method> mapper = new HashMap<Field, Method>();
        while (class1 != null && isAcceptable(class1)) {
            Field[] fields = class1.getDeclaredFields();
            for (Field field : fields) {
                mapper.put(field, class1.getMethod(getGetter(field)));
            }
            class1 = class1.getSuperclass();
        }

        memberOfType.put(class1, mapper);
        return mapper;
    }

    /**
     * 过滤非POJO的类
     * 
     * @param class1
     * @return <code>true</code>是可接受的类
     */
    private boolean isAcceptable(Class<? extends Object> class1) {
        if (Object.class == class1 || List.class.isAssignableFrom(class1)
                || Map.class.isAssignableFrom(class1) || Set.class.isAssignableFrom(class1)
                || Collection.class.isAssignableFrom(class1))
            return false;
        return true;
    }

    private String getGetter(Field field) {
        String name = field.getName();
        return new StringBuffer(
                TypeUtils.isAssignable(field.getType(), Boolean.TYPE) ? "is" : "get")
                        .append(name.substring(0, 1).toUpperCase()).append(name.substring(1))
                        .toString();
    }

    /**
     * 从一个请求参数中读取某个键值
     * 
     * @param param 请求参数
     * @param key 参数键
     * @return 值
     */
    public String readValue(RequestParams param, String key) {
        List<BasicNameValuePair> params = param.paramList();
        for (BasicNameValuePair p : params) {
            if (p.getName().equals(key)) {
                return p.getValue();
            }
        }
        throw new IllegalArgumentException("no such key : " + key);
    }
}
