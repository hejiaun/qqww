package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IUpdateGroupNameActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class UpdateGroupNameActivityPresenter extends BasePresenter<IUpdateGroupNameActivityView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iUpdateGroupNameActivityView View层接口
     */
    public UpdateGroupNameActivityPresenter(IUpdateGroupNameActivityView iUpdateGroupNameActivityView) {
        super(iUpdateGroupNameActivityView);
    }
}
