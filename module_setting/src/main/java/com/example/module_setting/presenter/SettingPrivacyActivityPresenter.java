package com.example.module_setting.presenter;

import com.example.module_setting.view_interface.ISettingPrivacyActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description：隐私设置Activity的Presenter
 */
public class SettingPrivacyActivityPresenter extends BasePresenter<ISettingPrivacyActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iSettingPrivacyActivityView View层接口
     */
    public SettingPrivacyActivityPresenter(ISettingPrivacyActivityView iSettingPrivacyActivityView) {
        super(iSettingPrivacyActivityView);
    }
}
