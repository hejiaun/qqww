package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IAddFriendByConditionView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AddFriendByConditionPresenter extends BasePresenter<IAddFriendByConditionView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iAddFriendByConditionView View层接口
     */
    public AddFriendByConditionPresenter(IAddFriendByConditionView iAddFriendByConditionView) {
        super(iAddFriendByConditionView);
    }
}
