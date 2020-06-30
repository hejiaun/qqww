package com.example.module_me.presenter;

import com.example.module_me.view_interface.ISelectCourseFragmentView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SelectCourseFragmentPresenter extends BasePresenter<ISelectCourseFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iSelectCourseFragmentView View层接口
     */
    public SelectCourseFragmentPresenter(ISelectCourseFragmentView iSelectCourseFragmentView) {
        super(iSelectCourseFragmentView);
    }
}
