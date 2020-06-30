package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.ISearchChatRecordActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SearchChatRecordActivityPresenter extends BasePresenter<ISearchChatRecordActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iSearchChatRecordActivityView View层接口
     */
    public SearchChatRecordActivityPresenter(ISearchChatRecordActivityView iSearchChatRecordActivityView) {
        super(iSearchChatRecordActivityView);
    }
}
