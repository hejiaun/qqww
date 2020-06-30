package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IChatSearchActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ChatSearchActivityPresenter extends BasePresenter<IChatSearchActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iChatSearchActivityView View层接口
     */
    public ChatSearchActivityPresenter(IChatSearchActivityView iChatSearchActivityView) {
        super(iChatSearchActivityView);
    }
}
