package com.example.module_chat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;
import com.example.module_chat.activity.ChatActivity;
import com.example.module_chat.adapter.ChatSessionListAdapter;
import com.example.module_chat.presenter.ChatSessionFragmentPresenter;
import com.example.module_chat.view_interface.IChatRecordFragmentView;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.entity.ChatSessionEntity;

/**
 * 聊天对话列表Fragment
 */
public class ChatSessionFragment extends BaseFragment<ChatSessionFragmentPresenter> implements IChatRecordFragmentView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener {
    RecyclerView rcvChatRecord;
    private ChatSessionListAdapter adapter;

    @Override
    public void initView() {
        super.initView();
        rcvChatRecord = view.findViewById(R.id.rcvChatRecord);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new ChatSessionListAdapter(R.layout.item_chat_record, new ArrayList<ChatSessionEntity>());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);

        rcvChatRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvChatRecord.setAdapter(adapter);
        rcvChatRecord.addItemDecoration(getPresenter().getItemDecoration());
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        getPresenter().requestFirtEntryData();
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ChatSessionFragmentPresenter createPresenter() {
        return new ChatSessionFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_chatrecord;
    }

    /**
     * 聊天对话列表Item长按事件监听
     *
     * @param adapter  聊天对话列表适配器
     * @param view     Item布局
     * @param position Item位置
     * @return
     */
    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        showPopupWindowMenu(view, position);
        return false;
    }

    /**
     * 聊天对话列表Item点击事件监听
     *
     * @param adapter  聊天对话列表适配器
     * @param view     Item布局
     * @param position Item位置
     * @return
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getContext(), ChatActivity.class);
        Bundle bundle = new Bundle();
        switch (((ChatSessionEntity) adapter.getData().get(position)).getType()) {
            case 1://群聊天
                bundle.putString("sessionType", "群聊天");
                break;
            case 2://一对一聊天
                bundle.putString("sessionType", "一对一聊天");
                break;
        }
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

    /**
     * 显示列表弹窗
     *
     * @param view     显示弹窗的Item的布局
     * @param position 显示弹窗的Item的位置
     */
    public void showPopupWindowMenu(final View view, final int position) {
        PopupMenu menu = new PopupMenu(getContext(), view, Gravity.RIGHT);
        final ChatSessionEntity chatSessionEntity = adapter.getItem(position);
        if (!chatSessionEntity.isSetTop()) {
            menu.inflate(R.menu.menu_chatsession_item_normal);
        } else {
            menu.inflate(R.menu.menu_chatsession_item_top);
        }

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int viewId = item.getItemId();
                if (viewId == R.id.setTop) {//顶置
                    getPresenter().setTop(chatSessionEntity);
                } else if (viewId == R.id.cancelSetTop) {//取消顶置
                    getPresenter().cancelSetTop(chatSessionEntity);
                } else if (viewId == R.id.delete) {//删除
                    getPresenter().deleteSession(position);
                }
                return false;
            }
        });
        menu.show();
    }

    /**
     * View层向Presenter层提供ChatSessionListAdapter
     *
     * @return ChatSessionListAdapter
     */
    @Override
    public ChatSessionListAdapter getAdapter() {
        return adapter;
    }

}
