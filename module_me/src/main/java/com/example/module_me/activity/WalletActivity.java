package com.example.module_me.activity;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.WalletPresenter;
import com.example.module_me.view_interface.IWalletView;
import com.flyco.tablayout.CommonTabLayout;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BaseFragment;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:钱包Activity
 */
public class WalletActivity extends BaseActivity<WalletPresenter> implements IWalletView {
    TextView tvTitle;
    CommonTabLayout ctl;
    ViewPager vp;
    private ArrayList<BaseFragment> fragments;
    private ImageView ivBack;
    private TextView tvRight;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        ctl = findViewById(R.id.ctl);
        vp = findViewById(R.id.vp);
        ivBack = findViewById(R.id.iv_back);
        tvRight = findViewById(R.id.tv_right);
        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_wallet;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public WalletPresenter createPresenter() {
        return new WalletPresenter(this);
    }


    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        fragments = getPresenter().getFragments();

        tvTitle.setText("充值");
        ctl.setTabData(getPresenter().getTabList());
        vp.setAdapter(getPresenter().getViewPagerAdapter());

        vp.addOnPageChangeListener(getPresenter().getPagerChangeListener());
        ctl.setOnTabSelectListener(getPresenter().getCommonTabLayoutClickListener());
    }

    /**
     * View层向Presenter层提供Actiivty
     *
     * @return Activity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * View层向Presenter层提供CommonTabLayout
     *
     * @return CommonTabLayout
     */
    @Override
    public CommonTabLayout getCommonTabLayout() {
        return ctl;
    }

    /**
     * View层向Presenter提供
     *
     * @return
     */
    @Override
    public ViewPager getViewPager() {
        return vp;
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tv_right) {
        }
    }
}
