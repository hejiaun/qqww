package com.example.module_me.presenter;

import com.example.module_me.view_interface.IClearCacheActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class ClearCacheActivityPresenter extends BasePresenter<IClearCacheActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iClearCacheActivityView View层接口
     */
    public ClearCacheActivityPresenter(IClearCacheActivityView iClearCacheActivityView) {
        super(iClearCacheActivityView);
    }
}
