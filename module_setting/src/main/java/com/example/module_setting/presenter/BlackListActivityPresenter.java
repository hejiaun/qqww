package com.example.module_setting.presenter;

import com.example.module_setting.view_interface.IBlackListActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:黑名单列表Activity的Presenter
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
