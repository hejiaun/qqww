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

import java.util.Map;

import cn.vfighter.communication.http.MimeParams;
import cn.vfighter.communication.utils.GsonFactory;

/**
 * 使用 {@link GsonFactory}序列化 POJO 的请求对象
 * 
 * @author Konlg
 */
public class MimeJsonParams extends MimeParams {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -84318195093066677L;

    public MimeJsonParams() {
        super();
    }

    public MimeJsonParams(Map<String, String> source) {
        super(source);
    }

    public MimeJsonParams(String key, String value) {
        super(key, value);
    }

    public MimeJsonParams(Object... keysAndValues) {
        super(keysAndValues);
    }

    @Override
    protected String serialize(Object value) {
        return GsonFactory.SingleTon.create().toJson(value);
    }
}
