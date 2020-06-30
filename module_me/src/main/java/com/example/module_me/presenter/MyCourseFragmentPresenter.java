package com.example.module_me.presenter;

import com.example.module_me.view_interface.IMyCourseFragmentView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyCourseFragmentPresenter extends BasePresenter<IMyCourseFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iMyCourseFragmentView View层接口
     */
    public MyCourseFragmentPresenter(IMyCourseFragmentView iMyCourseFragmentView) {
        super(iMyCourseFragmentView);
    }
}
