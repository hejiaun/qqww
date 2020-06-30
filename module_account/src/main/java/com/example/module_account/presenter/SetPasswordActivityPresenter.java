package com.example.module_account.presenter;

import com.example.module_account.view_interface.ISetPasswordActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SetPasswordActivityPresenter extends BasePresenter<ISetPasswordActivityView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iSetPasswordActivityView View层接口
     */
    public SetPasswordActivityPresenter(ISetPasswordActivityView iSetPasswordActivityView) {
        super(iSetPasswordActivityView);
    }
}
