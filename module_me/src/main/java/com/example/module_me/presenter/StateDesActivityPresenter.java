package com.example.module_me.presenter;

import com.example.module_me.view_interface.IStateDesActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class StateDesActivityPresenter extends BasePresenter<IStateDesActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iStateDesActivityView View层接口
     */
    public StateDesActivityPresenter(IStateDesActivityView iStateDesActivityView) {
        super(iStateDesActivityView);
    }
}
