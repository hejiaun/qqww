package com.example.module_chat.activity;

import android.view.View;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.presenter.SearchChatRecordActivityPresenter;
import com.example.module_chat.view_interface.ISearchChatRecordActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:搜索聊天记录Activity
 */
public class SearchChatRecordActivity extends BaseActivity<SearchChatRecordActivityPresenter> implements

        ISearchChatRecordActivityView {

    private TextView tvCancel;

    /**
     * 加载控件
     */
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
        return R.layout.activity_search_chatrecord;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SearchChatRecordActivityPresenter createPresenter() {
        return new SearchChatRecordActivityPresenter(this);
    }


    /**
     * 点击事件的监听
     * @param view
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tvCancel) {//点击取消
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
