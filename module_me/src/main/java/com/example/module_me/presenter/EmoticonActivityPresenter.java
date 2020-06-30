package com.example.module_me.presenter;


import com.example.module_me.view_interface.IEmoticonActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class EmoticonActivityPresenter extends BasePresenter<IEmoticonActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iEmoticonActivityView View层接口
     */
    public EmoticonActivityPresenter(IEmoticonActivityView iEmoticonActivityView) {
        super(iEmoticonActivityView);
    }
}
