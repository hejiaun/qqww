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
 */

package cn.vfighter.bean.param;

import java.util.Map;

/**
 * 排序参数接口
 * 
 * @author Konlg
 */
public interface ISortParam {
    /**
     * 获取排序字典
     *
     * @return sorts 排序字典
     */
    Map<String, String> getSorts();

    /**
     * 添加正序排序
     * 
     * @param column
     */
    void addAsc(String column);

    /**
     * 添加倒叙排序
     * 
     * @param column
     */
    void addDesc(String column);

}
