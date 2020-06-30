package com.example.module_setting.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_setting.R;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * Author: HeJiaJun
 * Date:
 * Description: 账户与安全Activity
 */
public class SettingAccountActivity extends BaseActivity  {
    TextView tvTitle;
    FunctionItemView fivAccountName;
    FunctionItemView fivPhone;
    FunctionItemView fivChangeEmail;
    FunctionItemView fivChangePassword;
    ImageView iv_back;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        fivAccountName = findViewById(R.id.fivAccountName);
        fivPhone = findViewById(R.id.fivPhone);
        fivChangeEmail = findViewById(R.id.fivChangeEmail);
        fivChangePassword = findViewById(R.id.fivChangePassword);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        fivChangePassword.setOnClickListener(this);
        fivChangeEmail.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("账号与安全");
        fivAccountName.setTextTitleTextRightStyle("账号昵称", "志明");
        fivPhone.setTextTitleTextRightStyle("手机号", "13185746545");
        fivChangePassword.setRightWithTextTitleStyle("修改密码");
        fivChangeEmail.setRightWithTextTitleStyle("修改绑定邮箱");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_setting_account;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.fivChangeEmail) {//修改绑定邮箱

        } else if (viewId == R.id.fivChangePassword) {//修改密码

        }
    }
}
