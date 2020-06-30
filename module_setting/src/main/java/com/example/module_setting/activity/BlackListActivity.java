package com.example.module_setting.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_setting.R;
import com.example.module_setting.presenter.BlackListActivityPresenter;
import com.example.module_setting.view_interface.IBlackListActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:黑名单列表Activity
 */
public class BlackListActivity extends BaseActivity<BlackListActivityPresenter> implements
        IBlackListActivityView {

    ImageView ivBack;
    TextView tvTitle;
    RecyclerView rcv;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        rcv = findViewById(R.id.rcv);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("黑名单管理");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_blacklist;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public BlackListActivityPresenter createPresenter() {
        return new BlackListActivityPresenter(this);
    }


    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
