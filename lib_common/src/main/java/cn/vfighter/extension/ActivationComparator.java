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

package cn.vfighter.extension;

import java.util.Comparator;

import cn.vfighter.extension.annotations.Activation;

/**
 * Activation比较
 * 
 * @author konlg
 */
public class ActivationComparator<T> implements Comparator<T> {

    /**
     * sequence 大的排在后面,如果没有设置sequence的排到最前面
     */
    @Override
    public int compare(T o1, T o2) {
        Activation p1 = o1.getClass().getAnnotation(Activation.class);
        Activation p2 = o2.getClass().getAnnotation(Activation.class);
        if (p1 == null) {
            return 1;
        } else if (p2 == null) {
            return -1;
        } else {
            return p1.sequence() - p2.sequence();
        }
    }
}
