package com.example.module_me.presenter;

import com.example.module_me.view_interface.ICollectionActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class CollectionActivityPresenter extends BasePresenter<ICollectionActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iCollectionActivityView View层接口
     */
    public CollectionActivityPresenter(ICollectionActivityView iCollectionActivityView) {
        super(iCollectionActivityView);
    }
}
