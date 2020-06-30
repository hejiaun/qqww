package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IAddFriendByConditionSearchResultView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AddFriendByConditionSearchResultPresenter extends BasePresenter<IAddFriendByConditionSearchResultView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iAddFriendByConditionSearchResultView View层接口
     */
    public AddFriendByConditionSearchResultPresenter(IAddFriendByConditionSearchResultView iAddFriendByConditionSearchResultView) {
        super(iAddFriendByConditionSearchResultView);
    }
}
