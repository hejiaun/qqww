package com.example.module_me.presenter;


import com.example.module_me.view_interface.INewListActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class NewListActivityPresenter extends BasePresenter<INewListActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iNewListActivityView View层接口
     */
    public NewListActivityPresenter(INewListActivityView iNewListActivityView) {
        super(iNewListActivityView);
    }
}
