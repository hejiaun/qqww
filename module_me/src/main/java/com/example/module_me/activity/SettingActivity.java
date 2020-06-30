package com.example.module_me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.module_me.R;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 系统设置
 */
public class SettingActivity extends BaseActivity {

    private TextView tvTitle;
    private FunctionItemView fivPhoneBinding;
    private FunctionItemView fivEmailBinding;
    private FunctionItemView fivChangePassword;
    private FunctionItemView fivClearCache;
    private FunctionItemView fivBlackList;
    private FunctionItemView fivFeedback;
    private FunctionItemView fivAboutUs;

    @Override
    public int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.iv_back).setOnClickListener(this);
        fivAboutUs = findViewById(R.id.fivAboutUs);
        fivBlackList = findViewById(R.id.fivBlackList);
        fivClearCache = findViewById(R.id.fivClearCache);
        fivChangePassword = findViewById(R.id.fivChangePassword);
        fivEmailBinding = findViewById(R.id.fivEmailBinding);
        fivPhoneBinding = findViewById(R.id.fivPhoneBinding);
        fivFeedback = findViewById(R.id.fivFeedback);

        fivAboutUs.setOnClickListener(this);
        fivFeedback.setOnClickListener(this);
        fivPhoneBinding.setOnClickListener(this);
        fivEmailBinding.setOnClickListener(this);
        fivChangePassword.setOnClickListener(this);
        fivClearCache.setOnClickListener(this);
        fivBlackList.setOnClickListener(this);

        findViewById(R.id.btnExit).setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("系统设置");
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (id == R.id.btnExit) {//点击退出
            ActivityUtil.getInstance().finishAllActivity();
        } else if (id == R.id.fivChangePassword) {
            startActivity(new Intent(this, ChangePasswordActivity.class));
        } else if (id == R.id.fivFeedback) {
            startActivity(new Intent(this, FeedBackActivity.class));
        } else if (id == R.id.fivAboutUs) {
            startActivity(new Intent(this, AboutUsActivity.class));
        } else if (id == R.id.fivBlackList) {
            startActivity(new Intent(this, BlackListActivity.class));
        } else if (id == R.id.fivPhoneBinding) {
            startActivity(new Intent(this, BindPhoneActivity.class));
        } else if (id == R.id.fivEmailBinding) {
            startActivity(new Intent(this, BindEmailActivity.class));
        } else if (id == R.id.fivClearCache) {
            startActivity(new Intent(this, ClearCacheActivity.class));
        }
    }
}
