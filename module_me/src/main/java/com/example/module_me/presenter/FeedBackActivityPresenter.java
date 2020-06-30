package com.example.module_me.presenter;

import com.example.module_me.view_interface.IFeedBackActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class FeedBackActivityPresenter extends BasePresenter<IFeedBackActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iFeedBackActivityView View层接口
     */
    public FeedBackActivityPresenter(IFeedBackActivityView iFeedBackActivityView) {
        super(iFeedBackActivityView);
    }
}
