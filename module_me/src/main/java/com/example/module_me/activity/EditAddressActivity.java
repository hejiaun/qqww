package com.example.module_me.activity;

import android.view.View;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.EditAddressActivityPresenter;
import com.example.module_me.view_interface.IEditAddressActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人地址信息的Activity
 */
public class EditAddressActivity extends BaseActivity<EditAddressActivityPresenter> implements
        IEditAddressActivityView {
    TextView tv_right;
    TextView tvTitle;

    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.iv_back).setOnClickListener(this);
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        tv_right.setText("完成");
        tvTitle.setText("地址");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_address;
    }

    @Override
    public EditAddressActivityPresenter createPresenter() {
        return new EditAddressActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        }

    }
}
