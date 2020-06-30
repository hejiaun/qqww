package com.example.module_study.view_interface;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.module_study.adapter.StudyCourseListAdapter;

import example.common_base.base.IBaseView;

/**
 * PorjectName:shengdoushi
 * Author: HeJiaJun
 * Description:课程列表Fragment的View层接口
 */
public interface IStudyCourseListFragmentView extends IBaseView {
    StudyCourseListAdapter getAdapter();

    SwipeRefreshLayout getSwipeRefreshLayout();

}
