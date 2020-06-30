package com.example.module_me.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.example.module_me.R;
import com.example.module_me.presenter.ChangePasswordActivityPresenter;
import com.example.module_me.view_interface.IChangePasswordActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class ChangePasswordActivity extends BaseActivity<ChangePasswordActivityPresenter> implements IChangePasswordActivityView {

    private EditText etPhone;
    private EditText etValidateCode;
    private TextView tvGetValidateCode;
    private TextView tv1;
    private TextView tv2;
    private EditText etPassword1;
    private EditText etPassword2;
    private SuperTextView stvTitleBar;

    @Override
    public int initLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    public void initView() {
        super.initView();
        etPhone = findViewById(R.id.etEmail);
        etValidateCode = findViewById(R.id.etValidateCode);
        tvGetValidateCode = findViewById(R.id.tvGetValidateCode);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        etPassword1 = findViewById(R.id.etPassword1);
        etPassword2 = findViewById(R.id.etPassword2);
        stvTitleBar = findViewById(R.id.stvTitleBar);

        tvGetValidateCode.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        showFirstStep();
        stvTitleBar.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
                String s = stvTitleBar.getRightTextView().getText().toString();
                if (s.equals("下一步")) {
                    showSecondStep();
                } else if (s.equals("完成")) {

                }
            }
        });
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                String s = stvTitleBar.getRightTextView().getText().toString();
                if (s.equals("下一步")) {
                    ActivityUtil.getInstance().finishActivity(ChangePasswordActivity.this);
                } else if (s.equals("完成")) {
                    showFirstStep();
                }
            }
        });
    }

    @Override
    public ChangePasswordActivityPresenter createPresenter() {
        return new ChangePasswordActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tvGetValidateCode) {
            tvGetValidateCode.setClickable(false);
            getPresenter().getValidateCode();
        }
    }

    /**
     * 显示第一步的控件
     */
    private void showFirstStep() {
        etPassword1.setVisibility(View.GONE);
        etPassword2.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        stvTitleBar.setRightString("下一步");

        tvGetValidateCode.setVisibility(View.VISIBLE);
        etPhone.setVisibility(View.VISIBLE);
        etValidateCode.setVisibility(View.VISIBLE);
    }

    /**
     * 显示第二步的控件
     */
    private void showSecondStep() {
        stvTitleBar.setRightString("完成");
        etPassword1.setVisibility(View.VISIBLE);
        etPassword2.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);

        tvGetValidateCode.setVisibility(View.GONE);
        etPhone.setVisibility(View.GONE);
        etValidateCode.setVisibility(View.GONE);
    }

    @Override
    public TextView getValidateTextView() {
        return tvGetValidateCode;
    }
}
