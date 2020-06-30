package com.example.module_chat.view_interface;

import com.example.module_chat.adapter.ChatContactAdapter;
import com.example.module_chat.fragment.ChatContactsFragment;

import example.common_base.base.IBaseView;

/**
 * Creater: HeJiaJun
 * Description:聊天通讯录的View层接口
 */
public interface IChatContactsView extends IBaseView {

    ChatContactsFragment getFragment();

    ChatContactAdapter getAdapter();

}
