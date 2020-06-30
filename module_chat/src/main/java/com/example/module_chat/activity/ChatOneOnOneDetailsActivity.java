package com.example.module_chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.presenter.ChatDetailsActivityPresenter;
import com.example.module_chat.view_interface.IChatDetailsActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:聊天详情Activity
 */
public class ChatOneOnOneDetailsActivity extends BaseActivity<ChatDetailsActivityPresenter> implements

        IChatDetailsActivityView {
    TextView tvTitle;
    FunctionItemView fivSetTop;
    FunctionItemView fivDontDisturb;
    FunctionItemView fivFindChatRecord;
    FunctionItemView fivSetBackground;
    FunctionItemView fivCleanRecord;
    FunctionItemView fivComplaint;
    RecyclerView rcv;
    private ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        rcv = findViewById(R.id.rcv);
        fivComplaint = findViewById(R.id.fivComplaint);
        fivCleanRecord = findViewById(R.id.fivCleanRecord);
        fivSetBackground = findViewById(R.id.fivSetBackground);
        fivFindChatRecord = findViewById(R.id.fivFindChatRecord);
        fivDontDisturb = findViewById(R.id.fivDontDisturb);
        fivSetTop = findViewById(R.id.fivSetTop);
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);

        ivBack.setOnClickListener(this);
        fivSetTop.setOnClickListener(this);
        fivDontDisturb.setOnClickListener(this);
        fivFindChatRecord.setOnClickListener(this);
        fivSetBackground.setOnClickListener(this);
        fivCleanRecord.setOnClickListener(this);
        fivComplaint.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("聊天详情");

        fivSetTop.setTextTitleWithSwitchStyle("顶置聊天");

        fivDontDisturb.setTextTitleWithSwitchStyle("消息免打扰");

        fivFindChatRecord.setRightWithTextTitleStyle("查找当前聊天内容");

        fivSetBackground.setRightWithTextTitleStyle("设置当前聊天背景");

        fivCleanRecord.setRightWithTextTitleStyle("清空聊天记录");

        fivComplaint.setRightWithTextTitleStyle("举报");

        rcv.setLayoutManager(new GridLayoutManager(this, 5));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());
    }


    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_chat_oneonone_details;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ChatDetailsActivityPresenter createPresenter() {
        return new ChatDetailsActivityPresenter(this);
    }

    /**
     * View层提供 Activity
     *
     * @return Activity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.fivSetTop) {//点击置顶
            fivSetTop.setClickItemCheck();

        } else if (viewId == R.id.fivDontDisturb) {//点击消息免打扰
            fivDontDisturb.setClickItemCheck();

        } else if (viewId == R.id.fivFindChatRecord) {//点击查找聊天记录
            startActivity(new Intent(this, SearchChatRecordActivity.class));

        } else if (viewId == R.id.fivSetBackground) {//设置聊天背景
            startActivity(new Intent(this, SetChatBackground.class));

        } else if (viewId == R.id.fivCleanRecord) {//清空聊天记录
            getPresenter().showComfirmCleanRecordDailog();

        } else if (viewId == R.id.fivComplaint) {//投诉
            startActivity(new Intent(this, ChatComplaintActivity.class));

        }
    }
}
