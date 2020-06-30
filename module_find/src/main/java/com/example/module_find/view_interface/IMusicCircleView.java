package com.example.module_find.view_interface;

import android.app.Activity;

import com.example.module_find.adapter.MusicCircleAdapter;

import example.common_base.base.IBaseView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  音乐圈Activity的View层接口
 */
public interface IMusicCircleView extends IBaseView {
    /**
     * 获取上下文
     *
     * @return
     */
    Activity getActivity();

    /**
     * View层向Presenter层提供 MusicCircleAdapter
     *
     * @return MusicCircleAdapter
     */
    MusicCircleAdapter getAdapter();
}
