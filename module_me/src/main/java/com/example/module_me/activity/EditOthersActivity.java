package com.example.module_me.activity;

import android.view.View;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.EditOthersActivityPresenter;
import com.example.module_me.view_interface.IEditOthersActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人喜好电影信息的Activity
 */
public class EditOthersActivity extends BaseActivity<EditOthersActivityPresenter> implements
        IEditOthersActivityView {
    TextView tv_right;
    TextView tvTitle;

    @Override
    public void initView() {
        super.initView();
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);

    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("其他");

        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_others;
    }

    @Override
    public EditOthersActivityPresenter createPresenter() {
        return new EditOthersActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
