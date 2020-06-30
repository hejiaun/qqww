package com.example.module_setting.presenter;

import com.example.module_setting.view_interface.ISettingMessageActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:消息设置Activity的Presenter
 */
public class SettingMessageActivityPresenter extends BasePresenter<ISettingMessageActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iSettingMessageActivityView View层接口
     */
    public SettingMessageActivityPresenter(ISettingMessageActivityView iSettingMessageActivityView) {
        super(iSettingMessageActivityView);
    }
}
