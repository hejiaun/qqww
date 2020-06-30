package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.ISetChatBackgroundView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SetChatBackgroundPresenter extends BasePresenter<ISetChatBackgroundView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iSetChatBackgroundView View层接口
     */
    public SetChatBackgroundPresenter(ISetChatBackgroundView iSetChatBackgroundView) {
        super(iSetChatBackgroundView);
    }
}
