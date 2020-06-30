package com.example.module_me.presenter;

import com.example.module_me.view_interface.IFollowListActivityView;
import com.example.module_me.view_interface.IPopularityActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class FollowActivityPresenter extends BasePresenter<IFollowListActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iPopularityActivityView View层接口
     */
    public FollowActivityPresenter(IFollowListActivityView view) {
        super(view);
    }
}
