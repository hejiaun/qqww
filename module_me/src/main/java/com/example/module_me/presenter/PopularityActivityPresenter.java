package com.example.module_me.presenter;

import com.example.module_me.view_interface.IPopularityActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PopularityActivityPresenter extends BasePresenter<IPopularityActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iPopularityActivityView View层接口
     */
    public PopularityActivityPresenter(IPopularityActivityView iPopularityActivityView) {
        super(iPopularityActivityView);
    }
}
