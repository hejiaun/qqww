package com.example.module_me.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.example.module_me.R;
import com.example.module_me.presenter.AboutUsActivityPresenter;
import com.example.module_me.view_interface.IAboutUsActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AboutUsActivity extends BaseActivity<AboutUsActivityPresenter> implements IAboutUsActivityView {
    SuperTextView superTextView = null;

    @Override
    public int initLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        super.initView();
        superTextView = findViewById(R.id.stvTitleBar);
    }


    @Override
    public void initConfig() {
        super.initConfig();
        superTextView.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                ActivityUtil.getInstance().finishActivity(AboutUsActivity.class);
            }
        });
    }


    @Override
    public AboutUsActivityPresenter createPresenter() {
        return null;
    }


}
