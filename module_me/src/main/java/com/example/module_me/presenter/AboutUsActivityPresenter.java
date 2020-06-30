package com.example.module_me.presenter;

import com.example.module_me.view_interface.IAboutUsActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AboutUsActivityPresenter extends BasePresenter<IAboutUsActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iAboutUsActivityView View层接口
     */
    public AboutUsActivityPresenter(IAboutUsActivityView iAboutUsActivityView) {
        super(iAboutUsActivityView);
    }
}
