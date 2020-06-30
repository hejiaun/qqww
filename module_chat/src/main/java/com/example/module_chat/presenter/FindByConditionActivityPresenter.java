package com.example.module_chat.presenter;


import com.example.module_chat.view_interface.IFindByConditionActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FindByConditionActivityPresenter extends BasePresenter<IFindByConditionActivityView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iFindByConditionActivityView View层接口
     */
    public FindByConditionActivityPresenter(IFindByConditionActivityView iFindByConditionActivityView) {
        super(iFindByConditionActivityView);
    }
}
