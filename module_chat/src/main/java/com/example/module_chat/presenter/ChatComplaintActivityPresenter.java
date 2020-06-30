package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IChatComplaintActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:聊天投诉Activity的Presenter
 */
public class ChatComplaintActivityPresenter extends BasePresenter<IChatComplaintActivityView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iChatComplaintActivityView
     */
    public ChatComplaintActivityPresenter(IChatComplaintActivityView iChatComplaintActivityView) {
        super(iChatComplaintActivityView);
    }
}
