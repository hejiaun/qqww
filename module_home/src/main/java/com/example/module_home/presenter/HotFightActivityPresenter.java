package com.example.module_home.presenter;

import com.example.module_home.view_interface.IHotFightActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 热门对战的Activity的presenter
 */
public class HotFightActivityPresenter extends BasePresenter<IHotFightActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iHotFightActivityView View层接口
     */
    public HotFightActivityPresenter(IHotFightActivityView iHotFightActivityView) {
        super(iHotFightActivityView);
    }
}
