package com.example.module_setting.presenter;


import com.example.module_setting.view_interface.ISettingActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:设置Activity的Presenter
 */
public class SettingActivityPresenter extends BasePresenter<ISettingActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iSettingActivityView View层接口
     */
    public SettingActivityPresenter(ISettingActivityView iSettingActivityView) {
        super(iSettingActivityView);
    }
}
