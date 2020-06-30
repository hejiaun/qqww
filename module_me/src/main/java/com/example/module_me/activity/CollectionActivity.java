package com.example.module_me.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.CollectionActivityPresenter;
import com.example.module_me.view_interface.ICollectionActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的收藏Activity
 */
public class CollectionActivity extends BaseActivity<CollectionActivityPresenter> implements
        ICollectionActivityView {

    TextView tvTitle;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("收藏");
        StatusBarUtil.setLightMode(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_collection;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public CollectionActivityPresenter createPresenter() {
        return new CollectionActivityPresenter(this);
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
