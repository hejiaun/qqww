package com.example.module_practice.presenter;


import com.example.module_practice.view_interface.IUploadAccompanimentView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class UploadAccompanimentPresenter extends BasePresenter<IUploadAccompanimentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iUploadAccompanimentView View层接口
     */
    public UploadAccompanimentPresenter(IUploadAccompanimentView iUploadAccompanimentView) {
        super(iUploadAccompanimentView);
    }
}
