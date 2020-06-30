package com.example.module_practice.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.module_practice.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  模式选择Activity
 */
public class SingModeActivity extends BaseActivity  {
    ImageView ivLeft;
    CommonTabLayout ctl;
    Button btnSing;
    private ArrayList<CustomTabEntity> tabEntities;


    @Override
    public void initView() {
        super.initView();
        ivLeft = findViewById(R.id.iv_left);
        ctl = findViewById(R.id.ctl);
        btnSing = findViewById(R.id.btn_sing);
        ivLeft.setOnClickListener(this);
        btnSing.setOnClickListener(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_mode_sing;
    }

    /**
     * 创建Presenter
     *
     * @return presenter
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("独唱", R.drawable.duchang_icon_duchang, R.drawable.duchang_icon_duchang_n));
        tabEntities.add(new TabEntity("合唱", R.drawable.duchang_icon_hechang, R.drawable.duchang_icon_hechang_n));

        StatusBarUtil.setTransparent(this);
        ctl.setTabData(tabEntities);
        ctl.setCurrentTab(0);
    }


    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_left) {
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.btn_sing) {
            if (ctl.getCurrentTab() == 0) {
                startActivity(new Intent(this, SingleSingActivity.class));

            } else if (ctl.getCurrentTab() == 1) {

            }
        }
    }
}
