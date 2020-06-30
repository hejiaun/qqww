package com.example.module_practice.view_interface;
import example.common_base.base.IBaseView;

import com.example.module_practice.activity.SingleSingActivity;

import example.common_base.widget.SongProgressView;
import me.wcy.lrcview.LrcView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  独唱Activity回调
 */
public interface ISingleSingView extends IBaseView {
    /**
     * 获取上下文Activity
     *
     * @return
     */
    SingleSingActivity getActivity();

    /**
     * 获取歌词
     * . @return
     */
    LrcView getLrcView();

    /**
     * 获取自定义进度条
     *
     * @return
     */
    SongProgressView getSongProgressView();


}
