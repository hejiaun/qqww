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
public class ChatGroupDetailsActivity extends BaseActivity<ChatDetailsActivityPresenter> implements

        IChatDetailsActivityView {
    TextView tvTitle;
    RecyclerView rcv;
    FunctionItemView fivGroupName;
    FunctionItemView fivQRCode;
    FunctionItemView fivGroupNotice;
    FunctionItemView fivMyGroupNickname;
    FunctionItemView fivShowMemberNickname;
    FunctionItemView fivSetTop;
    FunctionItemView fivDontDisturb;
    FunctionItemView fivFindChatRecord;
    FunctionItemView fivSetBackground;
    FunctionItemView fivCleanRecord;
    FunctionItemView fivComplaint;
    private ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        rcv = findViewById(R.id.rcv);
        fivGroupName = findViewById(R.id.fivGroupName);
        fivQRCode = findViewById(R.id.fivQRCode);
        fivGroupNotice = findViewById(R.id.fivGroupNotice);
        fivMyGroupNickname = findViewById(R.id.fivMyGroupNickname);
        fivShowMemberNickname = findViewById(R.id.fivShowMemberNickname);
        fivSetTop = findViewById(R.id.fivSetTop);
        fivDontDisturb = findViewById(R.id.fivDontDisturb);
        fivFindChatRecord = findViewById(R.id.fivFindChatRecord);
        fivSetBackground = findViewById(R.id.fivSetBackground);
        fivCleanRecord = findViewById(R.id.fivCleanRecord);
        fivComplaint = findViewById(R.id.fivComplaint);
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);
        fivGroupNotice.setOnClickListener(this);
        fivGroupName.setOnClickListener(this);
        fivQRCode.setOnClickListener(this);
        fivMyGroupNickname.setOnClickListener(this);
        fivShowMemberNickname.setOnClickListener(this);
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
        fivGroupName.setTextTitleTextRightStyle("群聊名称", "未命名");
        fivQRCode.setTextTitleRightCustomImage("群二维码", R.drawable.icon_mine_erweima);
        fivGroupNotice.setTextTitleTextRightStyle("群公告", "未设置");
        fivMyGroupNickname.setTextTitleTextRightStyle("我在本群的昵称", "未设置");
        fivShowMemberNickname.setTextTitleWithSwitchStyle("显示群员昵称");
        fivSetTop.setTextTitleWithSwitchStyle("顶置聊天");
        fivDontDisturb.setTextTitleWithSwitchStyle("消息免打扰");
        fivFindChatRecord.setRightWithTextTitleStyle("查找聊天内容");
        fivSetBackground.setRightWithTextTitleStyle("设置当前聊天背景");
        fivCleanRecord.setRightWithTextTitleStyle("清空聊天记录");
        fivComplaint.setRightWithTextTitleStyle("投诉");

        rcv.setLayoutManager(new GridLayoutManager(this, 5));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());
    }


    /**
     * 加载布局
     *
     * @return 布局Id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_chat_groupdetails;
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
     * View层提供Activity
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
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.fivShowMemberNickname) {//显示群员名称
            fivShowMemberNickname.setClickItemCheck();

        } else if (viewId == R.id.fivMyGroupNickname) {//我的群名称

        } else if (viewId == R.id.fivGroupName) {//群名称
            getPresenter().showUpdateGroupNameDialog();

        } else if (viewId == R.id.fivGroupNotice) {//群公告
            startActivity(new Intent(this, UpdateGroupNoticeActivity.class));
        } else if (viewId == R.id.fivQRCode) {//二维码

        } else if (viewId == R.id.fivSetTop) {//顶置
            fivSetTop.setClickItemCheck();

        } else if (viewId == R.id.fivDontDisturb) {//消息免打扰
            fivDontDisturb.setClickItemCheck();

        } else if (viewId == R.id.fivFindChatRecord) {//点击查找聊天记录
            startActivity(new Intent(this, SearchChatRecordActivity.class));

        } else if (viewId == R.id.fivDontDisturb) {//设置聊天背景
            startActivity(new Intent(this, SetChatBackground.class));

        } else if (viewId == R.id.fivCleanRecord) {//清空聊天记录
            getPresenter().showComfirmCleanRecordDailog();

        } else if (viewId == R.id.fivComplaint) {//投诉、
            startActivity(new Intent(this, ChatComplaintActivity.class));

        }
    }
}
