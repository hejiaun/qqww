package com.example.module_multiplayerroom.activity;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_multiplayerroom.R;
import com.example.module_multiplayerroom.presenter.MultiplayerRoomActivityPresenter;
import com.example.module_multiplayerroom.view_interface.IMultiplayerRoomActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  多人房Activity
 */
@Route(path = ARouterUtil.MultiplayerRoom_Activity)
public class MultiplayerRoomActivity extends BaseActivity<MultiplayerRoomActivityPresenter> implements

        IMultiplayerRoomActivityView {
    TextView tvTitle;
    ImageButton ivClose;
    LinearLayout llChat;
    LinearLayout llMask;
    ImageView ivChat;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        ivClose = findViewById(R.id.iv_close);
        llChat = findViewById(R.id.ll_chat);
        llMask = findViewById(R.id.ll_mask);
        ivChat = findViewById(R.id.iv_chat);
        ivBack = findViewById(R.id.iv_back);
        ivChat.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        tvTitle.setText("多人房");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_multiplayer_room;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MultiplayerRoomActivityPresenter createPresenter() {
        return new MultiplayerRoomActivityPresenter(this);
    }


    /**
     * 打开聊天框
     */
    public void openChatView() {
        //设置聊天按钮可见性
        ivChat.setVisibility(View.GONE);
        //播放动画
        llChat.startAnimation(AnimationUtils.loadAnimation(this, R.anim.chat_view_show));
        llMask.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mask_show));
        //设置聊天框可见性
        llMask.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭聊天框
     */
    public void closeChatView() {
        //播放动画
        llChat.startAnimation(AnimationUtils.loadAnimation(this, R.anim.chat_view_hide));
        llMask.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mask_hide));
        //设置聊天框可见性
        llMask.setVisibility(View.GONE);
        //设置聊天按钮可见性
        ivChat.setVisibility(View.VISIBLE);
    }

    /**
     * 点击返回键
     */
    @Override
    public void onBackPressed() {
        if (llMask.getVisibility() == View.VISIBLE) {
            closeChatView();
        } else {
            super.onBackPressed();
        }
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

        } else if (viewId == R.id.iv_close) {
            closeChatView();

        } else if (viewId == R.id.iv_chat) {
            openChatView();

        }
    }
}
