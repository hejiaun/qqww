package com.example.module_me.presenter;

import com.example.module_me.view_interface.ICourseScheduleFragmentView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class
CourseScheduleFragmentPresenter extends BasePresenter<ICourseScheduleFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iCourseScheduleFragmentView View层接口
     */
    public CourseScheduleFragmentPresenter(ICourseScheduleFragmentView iCourseScheduleFragmentView) {
        super(iCourseScheduleFragmentView);
    }
}
