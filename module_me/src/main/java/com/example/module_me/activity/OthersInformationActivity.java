package com.example.module_me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_me.R;
import com.example.module_me.fragment.MyPhotoFragment;
import com.example.module_me.fragment.MyRelationshipFragment;
import com.example.module_me.fragment.OtherInformationFragment;
import com.example.module_me.fragment.VideoFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  别人的个人信息Activity
 */
@Route(path = ARouterUtil.OthersInformation_Activity)
public class OthersInformationActivity extends BaseActivity implements ViewPager.OnPageChangeListener, OnTabSelectListener {
    ImageView ivCut;
    TextView tvTitle;
    ImageView ivShare;
    ImageView ivNews;
    CommonTabLayout ctl;
    ViewPager vp;
    private ArrayList<CustomTabEntity> tabEntities;
    private ArrayList<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        vp = findViewById(R.id.vp);
        ctl = findViewById(R.id.ctl);
        ivNews = findViewById(R.id.iv_news);
        ivShare = findViewById(R.id.iv_share);
        tvTitle = findViewById(R.id.tvTitle);
        ivCut = findViewById(R.id.iv_cut);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tvTitle.setText("某用户的资料");

        //获取从哪个页面跳转过来
        tabEntities = new ArrayList<>();
        fragments = new ArrayList<Fragment>();
        fragments.add(new OtherInformationFragment());
        fragments.add(VideoFragment.getInstence(ConstantValuesUtil.OTHERS));
        fragments.add(new MyRelationshipFragment());
        fragments.add(new MyPhotoFragment());
//        fragments.add(new OthersCourseFragment());

        tabEntities.add(new TabEntity("资料"));
        tabEntities.add(new TabEntity("录像"));
        tabEntities.add(new TabEntity("关系"));
        tabEntities.add(new TabEntity("相册"));
//        tabEntities.add(new TabEntity("课程"));

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

        ctl.setTabData(tabEntities);
        vp.setOffscreenPageLimit(4);
        ivCut.setVisibility(View.GONE);
        ivNews.setVisibility(View.GONE);
        vp.setAdapter(fragmentPagerAdapter);

        vp.addOnPageChangeListener(this);
        ctl.setOnTabSelectListener(this);
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
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        ctl.setCurrentTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelect(int position) {
        vp.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的事件
     */
    @Override
    public void onClick(View view) {
        int viewId=view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
