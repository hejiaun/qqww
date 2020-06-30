package com.example.module_chat.view_interface;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.example.module_chat.adapter.ChatMessageAdapter;

import example.common_base.base.IBaseView;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  聊天Activity的View层接口
 */
public interface IChatActivityView extends IBaseView {

    RecyclerView getRecyclerView();

    Activity getActivity();

    ChatMessageAdapter getAdapter();

    void showEnlargeImageDailog(int position);

    void showEnlargeTextDailog(int position);
}
