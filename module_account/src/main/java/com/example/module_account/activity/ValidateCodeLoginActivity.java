package com.example.module_account.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ValidateCodeLoginPresenter;
import com.example.module_account.view_interface.IValidateCodeLoginView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description: 验证码登陆Activity
 */
public class ValidateCodeLoginActivity extends BaseActivity<ValidateCodeLoginPresenter> implements
        IValidateCodeLoginView {
    EditText etValidateCode;
    TextView tvGetValidateCode;
    private Button btnLogin;
    private TextView tvPwdLogin;
    private TextView tvGetValidateCode1;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        etValidateCode = findViewById(R.id.et_validateCode);
        tvGetValidateCode = findViewById(R.id.tv_getValidateCode);
        btnLogin = findViewById(R.id.btn_login);
        tvPwdLogin = findViewById(R.id.tv_pwd_login);
        tvGetValidateCode1 = findViewById(R.id.tv_getValidateCode);
        tvPwdLogin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvGetValidateCode1.setOnClickListener(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 3);
        //设置状态栏为亮模式
        StatusBarUtil.setLightMode(ValidateCodeLoginActivity.this);
    }


    /**
     * 显示确认对话框
     */
    public void showComfirmDialog() {
        View view = View.inflate(this, R.layout.dialog_confirm, null);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvSuer = (TextView) view.findViewById(R.id.tv_suer);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        tvContent.setText("确认手机号码");
        tvTitle.setText("+165465165");
        final AlertDialog alertDialog = builder.create();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        tvSuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                getPresenter().startCountDonw();
            }
        });
        alertDialog.show();
    }

    /**
     * View层向Presenter层提供设置获取验证码按钮的文本的方法
     *
     * @param text 新文本
     */
    @Override
    public void setGetValidateCodeText(String text) {
        tvGetValidateCode.setText(text);
    }

    /**
     * View层向Presenter层提供设置获取验证码按钮的可点性的方法
     *
     * @param clickable 是否可点
     */
    @Override
    public void setClickable(Boolean clickable) {
        tvGetValidateCode.setClickable(clickable);
    }

    /**
     * View层向Presenter层提供上下文
     *
     * @return 上下文
     */
    @Override
    public Context getContext() {
        return ValidateCodeLoginActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().cancelTimer();
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_login_validatecode;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ValidateCodeLoginPresenter createPresenter() {
        return new ValidateCodeLoginPresenter(this);
    }

    /**
     * 点击事件的监听
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tvGetValidateCode) {
            showComfirmDialog();

        } else if (viewId == R.id.tv_pwd_login) {
            ActivityUtil.getInstance().finishActivity(ValidateCodeLoginActivity.class);
        } else if (viewId == R.id.btn_login) {
            startActivity(new Intent(ValidateCodeLoginActivity.this, SetPasswordActivity.class));
            ActivityUtil.getInstance().finishActivity(ValidateCodeLoginActivity.class);
        }
    }
}
