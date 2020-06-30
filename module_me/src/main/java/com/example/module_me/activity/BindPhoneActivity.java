package com.example.module_me.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.example.module_me.R;
import com.example.module_me.presenter.BindPhoneActivityPresenter;
import com.example.module_me.view_interface.IBindPhoneActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 绑定手机号Activity
 */
public class BindPhoneActivity extends BaseActivity<BindPhoneActivityPresenter> implements IBindPhoneActivityView {

    private SuperTextView stvTitleBar;
    private EditText etPhone;
    private EditText etValidateCode;
    private TextView tvGetValidateCode;
    private TextView tvPhone;
    private SuperButton sbChange;

    @Override
    public int initLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initConfig() {
        super.initConfig();
        showCurrentPhone();
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
//                if (stvTitleBar.getRightTextView().getText().equals("完成")) {
//                    showCurrentPhone();
//                } else {
                    ActivityUtil.getInstance().finishActivity(BindPhoneActivity.class);
//                }
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        stvTitleBar = findViewById(R.id.stvTitleBar);
        etPhone = findViewById(R.id.etEmail);
        etValidateCode = findViewById(R.id.etValidateCode);
        tvGetValidateCode = findViewById(R.id.tvGetValidateCode);
        tvPhone = findViewById(R.id.tvEmail);
        sbChange = findViewById(R.id.sbChange);

        sbChange.setOnClickListener(this);
        tvGetValidateCode.setOnClickListener(this);
    }

    /**
     * 显示更改手机号
     */
    public void showChangePassword() {
        sbChange.setVisibility(View.GONE);
        tvPhone.setVisibility(View.GONE);
        stvTitleBar.setRightString("完成");

        etPhone.setVisibility(View.VISIBLE);
        etValidateCode.setVisibility(View.VISIBLE);
        tvGetValidateCode.setVisibility(View.VISIBLE);
    }

    /**
     * 显示当前绑定的手机号
     */
    public void showCurrentPhone() {
        sbChange.setVisibility(View.VISIBLE);
        tvPhone.setVisibility(View.VISIBLE);
        stvTitleBar.setRightString(null);

        etPhone.setVisibility(View.GONE);
        etValidateCode.setVisibility(View.GONE);
        tvGetValidateCode.setVisibility(View.GONE);
    }

    @Override
    public BindPhoneActivityPresenter createPresenter() {
        return new BindPhoneActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tvGetValidateCode) {//点击获取验证码
            getPresenter().getValidateCode();
        } else if (id == R.id.sbChange) {//点击修改绑定手机
            showChangePassword();
        }
    }

    @Override
    public TextView getGetValidateTextView() {
        return tvGetValidateCode;
    }
}
