package com.example.module_me.presenter;

import com.example.module_me.view_interface.IVipChargeFragmentView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class VipChargeFragmentPresenter extends BasePresenter<IVipChargeFragmentView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iVipChargeFragmentView View层接口
     */
    public VipChargeFragmentPresenter(IVipChargeFragmentView iVipChargeFragmentView) {
        super(iVipChargeFragmentView);
    }
}
