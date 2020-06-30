package com.example.module_me.activity;

import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.example.module_me.R;
import com.example.module_me.presenter.BlackListActivityPresenter;
import com.example.module_me.view_interface.IBlackListActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class BlackListActivity extends BaseActivity<BlackListActivityPresenter> implements IBlackListActivityView {

    private SuperTextView stvTitleBar;

    @Override
    public void initView() {
        super.initView();
        stvTitleBar = findViewById(R.id.stvTitleBar);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                ActivityUtil.getInstance().finishActivity(BlackListActivity.class);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.activity_blacklist;
    }

    @Override
    public BlackListActivityPresenter createPresenter() {
        return null;
    }
}
