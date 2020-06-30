package com.example.module_chat.presenter;

import com.example.module_chat.model.AddFriendsActivityModel;
import com.example.module_chat.view_interface.IAddFriendsActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 添加朋友Activity的Presenter
 */
public class AddFriendsActivityPresenter extends BasePresenter<IAddFriendsActivityView> {
    /**
     * Model层
     */
    AddFriendsActivityModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iAddFriendsActivityView View层接口
     */
    public AddFriendsActivityPresenter(IAddFriendsActivityView iAddFriendsActivityView) {
        super(iAddFriendsActivityView);
    }


    public void findFriendByCondition() {

    }
}
