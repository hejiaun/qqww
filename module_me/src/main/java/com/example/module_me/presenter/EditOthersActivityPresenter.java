package com.example.module_me.presenter;

import com.example.module_me.view_interface.IEditOthersActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人其他信息的Activity的Presenter层
 */
public class EditOthersActivityPresenter extends BasePresenter<IEditOthersActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditOthersActivityView View层接口
     */
    public EditOthersActivityPresenter(IEditOthersActivityView iEditOthersActivityView) {
        super(iEditOthersActivityView);
    }
}
