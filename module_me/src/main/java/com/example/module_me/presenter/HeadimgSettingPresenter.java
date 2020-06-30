package com.example.module_me.presenter;

import com.example.module_me.view_interface.IHeadimgSettingView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class HeadimgSettingPresenter extends BasePresenter<IHeadimgSettingView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iHeadimgSettingView View层接口
     */
    public HeadimgSettingPresenter(IHeadimgSettingView iHeadimgSettingView) {
        super(iHeadimgSettingView);
    }
}
