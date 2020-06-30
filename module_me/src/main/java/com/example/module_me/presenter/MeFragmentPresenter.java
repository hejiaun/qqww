package com.example.module_me.presenter;


import com.example.module_me.view_interface.IMeFragmentView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MeFragmentPresenter extends BasePresenter<IMeFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iMeFragmentView View层接口
     */
    public MeFragmentPresenter(IMeFragmentView iMeFragmentView) {
        super(iMeFragmentView);
    }
}
