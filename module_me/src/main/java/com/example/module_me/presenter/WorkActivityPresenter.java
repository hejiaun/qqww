package com.example.module_me.presenter;

import com.example.module_me.view_interface.IWorkActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class WorkActivityPresenter extends BasePresenter<IWorkActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iBaseView View层接口
     */
    public WorkActivityPresenter(IWorkActivityView iBaseView) {
        super(iBaseView);
    }
}
