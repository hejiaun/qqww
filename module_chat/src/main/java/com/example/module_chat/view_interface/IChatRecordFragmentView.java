package com.example.module_chat.view_interface;

import com.example.module_chat.adapter.ChatSessionListAdapter;

import example.common_base.base.IBaseView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  聊天记录Fragment的View层接口
 */
public interface IChatRecordFragmentView extends IBaseView {

    ChatSessionListAdapter getAdapter();

}
