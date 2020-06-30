package com.example.module_public_busniess.presenter;

import com.example.administrator.shengdoushi.business.public_business.view_interface.IWaitChorusFragmentView;
import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:等待合唱Fragment的Presenter
 */
public class WaitChorusFragmentPresenter extends BasePresenter<IWaitChorusFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iWaitChorusFragmentView View层接口
     */
    public WaitChorusFragmentPresenter(IWaitChorusFragmentView iWaitChorusFragmentView) {
        super(iWaitChorusFragmentView);
    }
}
