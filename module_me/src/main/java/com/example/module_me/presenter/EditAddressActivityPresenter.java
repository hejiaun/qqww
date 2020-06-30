package com.example.module_me.presenter;

import com.example.module_me.view_interface.IEditAddressActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人地址信息的Activity的Presenter层
 */
public class EditAddressActivityPresenter extends BasePresenter<IEditAddressActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditAddressActivityView View层接口
     */
    public EditAddressActivityPresenter(IEditAddressActivityView iEditAddressActivityView) {
        super(iEditAddressActivityView);
    }

    public void finishEdit(){



    }

}
