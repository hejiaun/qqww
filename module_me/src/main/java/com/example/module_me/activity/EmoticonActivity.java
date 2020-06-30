package com.example.module_me.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.EmoticonActivityPresenter;
import com.example.module_me.view_interface.IEmoticonActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的表情Activity
 */
public class EmoticonActivity extends BaseActivity<EmoticonActivityPresenter> implements

        IEmoticonActivityView {
    TextView tvTitle;
    ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tvTitle);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        StatusBarUtil.setLightMode(this);

        super.initConfig();
        tvTitle.setText("表情");
    }


    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_emoticon;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public EmoticonActivityPresenter createPresenter() {
        return new EmoticonActivityPresenter(this);
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }

    }
}
