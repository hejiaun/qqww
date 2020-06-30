package com.example.module_chat.activity;

import android.view.View;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.presenter.ChatSearchActivityPresenter;
import com.example.module_chat.view_interface.IChatSearchActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:聊天搜索界面
 */
public class ChatSearchActivity extends BaseActivity<ChatSearchActivityPresenter> implements

        IChatSearchActivityView {

    private TextView tvCancel;

    @Override
    public void initView() {
        super.initView();
        tvCancel = findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setLightMode(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_search_chat;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ChatSearchActivityPresenter createPresenter() {
        return new ChatSearchActivityPresenter(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tvCancel) {//点击取消
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
