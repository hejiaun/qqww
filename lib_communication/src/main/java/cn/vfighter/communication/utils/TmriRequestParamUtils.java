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

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.vfighter.communication.http.RequestParamsUtils;

/**
 * 表单参数工具
 * 
 * @author konlg
 */
public class TmriRequestParamUtils extends RequestParamsUtils {
    @Override
    protected String convertDateToString(Date invoke) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(invoke);
    }

}
