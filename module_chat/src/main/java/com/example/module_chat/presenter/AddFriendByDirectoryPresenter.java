package com.example.module_chat.presenter;

import com.example.module_chat.view_interface.IAddFriendByDirectoryView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AddFriendByDirectoryPresenter extends BasePresenter<IAddFriendByDirectoryView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iAddFriendByDirectoryView View层接口
     */
    public AddFriendByDirectoryPresenter(IAddFriendByDirectoryView iAddFriendByDirectoryView) {
        super(iAddFriendByDirectoryView);
    }
}
