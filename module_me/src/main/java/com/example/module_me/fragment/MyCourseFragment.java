package com.example.module_me.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.module_me.R;
import com.example.module_me.presenter.MyCourseFragmentPresenter;
import com.example.module_me.view_interface.IMyCourseFragmentView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.entity.TabEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的课程Fragment
 */
public class MyCourseFragment extends BaseFragment<MyCourseFragmentPresenter> implements IMyCourseFragmentView {
    CommonTabLayout ctl;
    ViewPager vp;
    private ArrayList<CustomTabEntity> tabEntities;
    private ArrayList<Fragment> fragments;
    static int position = 0;


    @Override
    public void initView() {
        super.initView();
        ctl = view.findViewById(R.id.ctl);
        vp = view.findViewById(R.id.vpCourse);

    }

    /**
     * 设置当前的tab位置
     *
     * @param position
     */
    public void setCurrentTab(int position) {
        this.position = position;
    }


    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyCourseFragmentPresenter createPresenter() {
        return new MyCourseFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_information_course;
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        fragments = new ArrayList<Fragment>();
        tabEntities = new ArrayList<>();
        fragments.add(new MyFightVideoFragment());
        fragments.add(new MyWorkVideoFragment());
        fragments.add(new ApprenticeFragment());
        fragments.add(new MyCourseVideoFragment());
        fragments.add(new MyCourseVideoFragment());
        fragments.add(new WordOfMouthFragment());

        tabEntities.add(new TabEntity("课表"));
        tabEntities.add(new TabEntity("师生"));
        tabEntities.add(new TabEntity("拜师"));
        tabEntities.add(new TabEntity("练声"));
        tabEntities.add(new TabEntity("选课"));
        tabEntities.add(new TabEntity("口碑"));

        ctl.setTabData(tabEntities);
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tabEntities.size();
            }
        });

        ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override

            public void onPageSelected(int position) {
                ctl.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        vp.setCurrentItem(position);
    }
}
