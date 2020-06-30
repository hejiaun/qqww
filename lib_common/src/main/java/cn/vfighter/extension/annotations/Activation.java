/* Copyright (c) vfighter.cn, All Rights Reserved
 *              ____     __    __
 *   _   ______/ __/____/ / __/ /_________
 *  | | / /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | |/ / / / / /__  / // // /_/ ___/ /
 *  |___/_/ /_/  __/ /_//_/ \__/\___/_/
 *              \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.extension.annotations;

/**
 * 标识活动的扩展<br/>
 * Spi有多个实现时，可以根据条件进行过滤、排序后再返回。
 * 
 * @author konlg
 */
public @interface Activation {
    /**
     * seq号越小，在返回的list<Instance>中的位置越靠前，尽量使用 0-100以内的数字
     * 
     * @return
     */
    int sequence() default 20;

    /**
     * spi 的key，获取spi列表时，根据key进行匹配，当key中存在待过滤的search-key时，匹配成功
     * 
     * @return
     */
    String[] key() default "";

    /**
     * 是否支持重试的时候也调用
     * 
     * @return
     */
    boolean retry() default true;
}
