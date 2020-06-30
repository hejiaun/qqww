package com.example.module_me.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.module_me.R;
import com.example.module_me.presenter.MyInformationActivityPresenter;
import com.example.module_me.view_interface.IMyInformationActivityView;
import com.flyco.tablayout.CommonTabLayout;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的个人信息Activity
 */
public class MyInformationActivity extends BaseActivity<MyInformationActivityPresenter> implements IMyInformationActivityView {
    CommonTabLayout ctl;
    ViewPager vp;
    private String fromIntent = null;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        ctl = findViewById(R.id.ctl);
        vp = findViewById(R.id.vp);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        //获取从哪个页面跳转过来
        fromIntent = getIntent().getStringExtra("fromIntent");

        ctl.setTabData(getPresenter().getTabEntities());
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(getPresenter().getFragmentPagerAdapter(getSupportFragmentManager()));

        vp.addOnPageChangeListener(getPresenter().getViewPagerPagerChangerListener());
        ctl.setOnTabSelectListener(getPresenter().getTabSelectListener());

        if (fromIntent != null && fromIntent.equals("photoIntent")) {
            vp.setCurrentItem(3);
        }
    }


    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_information;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyInformationActivityPresenter createPresenter() {
        return new MyInformationActivityPresenter(this);
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
     * View层向Presenter层提供ViewPager
     *
     * @return ViewPager
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
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
