package com.example.module_setting.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_setting.R;
import com.example.module_setting.presenter.SettingActivityPresenter;
import com.example.module_setting.view_interface.ISettingActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  设置Activity
 */
@Route(path = ARouterUtil.Setting_Activity)
public class SettingActivity extends BaseActivity<SettingActivityPresenter> implements
        ISettingActivityView{
    TextView tvTitle;
    FunctionItemView fivSafe;
    FunctionItemView fivMessage;
    FunctionItemView fivPrivacy;
    Button btnExit;
    FunctionItemView fivAbout;
    FunctionItemView fivCleanCache;
    ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        fivSafe = findViewById(R.id.fiv_safe);
        fivMessage = findViewById(R.id.fiv_message);
        fivPrivacy = findViewById(R.id.fiv_privacy);
        btnExit = findViewById(R.id.btnLogout);
        fivAbout = findViewById(R.id.fiv_about);
        fivCleanCache = findViewById(R.id.fivCleanCache);
        fivSafe.setOnClickListener(this);
        fivMessage.setOnClickListener(this);
        fivPrivacy.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("设置");
        fivSafe.setRightWithTextTitleStyle("账号与安全");
        fivMessage.setRightWithTextTitleStyle("消息设置");
        fivPrivacy.setRightWithTextTitleStyle("隐私设置");
        fivAbout.setRightWithTextTitleStyle("关于我们");
        fivCleanCache.setRightWithTextTitleStyle("清除缓存");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_setting;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SettingActivityPresenter createPresenter() {
        return new SettingActivityPresenter(this);
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

        } else if (viewId == R.id.fiv_safe) {//点击账号与安全
            startActivity(new Intent(this, SettingAccountActivity.class));

        } else if (viewId == R.id.fiv_message) {//点击消息提示
            startActivity(new Intent(this, SettingMessageActivity.class));

        } else if (viewId == R.id.fiv_privacy) {//点击屏蔽
            startActivity(new Intent(this, SettingPrivacyActivity.class));

        } else if (viewId == R.id.btnLogout) {//点击退出账号

        }
    }
}
