package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IChatFragmentView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ChatFragmentPresenter extends BasePresenter<IChatFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iChatFragmentView View层接口
     */
    public ChatFragmentPresenter(IChatFragmentView iChatFragmentView) {
        super(iChatFragmentView);
    }
}
