package com.example.module_account.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.SetPasswordActivityPresenter;
import com.example.module_account.view_interface.ISetPasswordActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.main.MainActivity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  设置密码Activity
 */
public class SetPasswordActivity extends BaseActivity<SetPasswordActivityPresenter> implements
        ISetPasswordActivityView {
    ImageView ivBack;
    TextView tvTitle;
    EditText etPassword1;
    EditText etPassword2;
    Toolbar tb;
    private Button btnLogin;
    private TextView tvSkip;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        etPassword1 = findViewById(R.id.et_password1);
        etPassword2 = findViewById(R.id.et_password2);
        tb = findViewById(R.id.tb);
        btnLogin = findViewById(R.id.btn_login);
        tvSkip = findViewById(R.id.tv_skip);
        ivBack.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 3);
        StatusBarUtil.setLightMode(SetPasswordActivity.this);
        tb.setBackground(getResources().getDrawable(R.color.white));
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setText("建议您现在设置密码");
        ivBack.setVisibility(View.GONE);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_setpwd;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SetPasswordActivityPresenter createPresenter() {
        return new SetPasswordActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_login) {   //确认

        } else if (viewId == R.id.tv_skip) {    //跳过
            ActivityUtil.getInstance().finishAllActivity();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
