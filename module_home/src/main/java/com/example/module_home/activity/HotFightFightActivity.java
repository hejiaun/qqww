package com.example.module_home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_home.R;
import com.example.module_home.fragment.HotFightFightListFragment;
import com.example.module_home.presenter.HotFightActivityPresenter;
import com.example.module_home.view_interface.IHotFightActivityView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.entity.TabEntity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:热门比赛Activity
 */
public class HotFightFightActivity extends BaseActivity<HotFightActivityPresenter> implements
        IHotFightActivityView {
    TextView tvTitle;
    CommonTabLayout commonTabLayout;
    ViewPager vp;
    private ArrayList<Fragment> fragments;
    private HotFightFightListFragment masterFragment;
    private HotFightFightListFragment friendFragment;
    private HotFightFightListFragment bandFragment;
    private ArrayList<CustomTabEntity> customTabEntities;
    private TextView tv_right;
    private ImageView ivBack;


    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        commonTabLayout = findViewById(R.id.ctl);
        vp = findViewById(R.id.vp);
        ivBack = findViewById(R.id.iv_back);
        tv_right = findViewById(R.id.tv_right);
        ivBack.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        customTabEntities = new ArrayList<>();
        customTabEntities.add(new TabEntity("大神"));
        customTabEntities.add(new TabEntity("好友"));
        customTabEntities.add(new TabEntity("乐队"));

        fragments = new ArrayList<>();
        masterFragment = new HotFightFightListFragment();
        friendFragment = new HotFightFightListFragment();
        bandFragment = new HotFightFightListFragment();
        fragments.add(friendFragment);
        fragments.add(masterFragment);
        fragments.add(bandFragment);

        commonTabLayout.setTabData(customTabEntities);
        tvTitle.setText("热门推荐");

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_hot;
    }


    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public HotFightActivityPresenter createPresenter() {
        return new HotFightActivityPresenter(this);
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
        } else if (viewId == R.id.tv_right) {


        }
    }
}
