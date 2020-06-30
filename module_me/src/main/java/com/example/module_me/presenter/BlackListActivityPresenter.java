package com.example.module_me.presenter;

import com.example.module_me.view_interface.IBlackListActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class BlackListActivityPresenter extends BasePresenter<IBlackListActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iBlackListActivityView View层接口
     */
    public BlackListActivityPresenter(IBlackListActivityView iBlackListActivityView) {
        super(iBlackListActivityView);
    }
}
