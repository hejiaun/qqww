package com.example.module_public_busniess.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import example.common_base.base.BasePresenter;

import com.example.administrator.shengdoushi.business.me.fragment.ApprenticeFragment;
import com.example.administrator.shengdoushi.business.me.fragment.MyCourseVideoFragment;
import com.example.administrator.shengdoushi.business.me.fragment.MyFightVideoFragment;
import com.example.administrator.shengdoushi.business.me.fragment.MyWorkVideoFragment;
import com.example.administrator.shengdoushi.business.public_business.fragment.OthersWordOfMouthFragment;
import com.example.administrator.shengdoushi.business.public_business.view_interface.IOthersCourseFragmentView;
import example.common_base.entity.TabEntity;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:别人的课程Fragement的Presenter
 */
public class OthersCourseFragmentPresenter extends BasePresenter<IOthersCourseFragmentView> {
    private ArrayList<CustomTabEntity> tabEntities = null;
    private ArrayList<Fragment> fragments = null;
    private FragmentPagerAdapter fragmentPagerAdapter = null;
    private OnTabSelectListener onTabSelectListener = null;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    /**
     * 构造方法，初始化View层
     *
     * @param iOthersCourseFragmentView View层接口
     */
    public OthersCourseFragmentPresenter(IOthersCourseFragmentView iOthersCourseFragmentView) {
        super(iOthersCourseFragmentView);
    }


    public ArrayList<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<Fragment>();
            fragments.add(new MyFightVideoFragment());
            fragments.add(new MyWorkVideoFragment());
            fragments.add(new ApprenticeFragment());
            fragments.add(new MyCourseVideoFragment());
            fragments.add(new OthersWordOfMouthFragment());
        }
        return fragments;
    }

    public ArrayList getTabs() {
        if (tabEntities == null) {
            tabEntities = new ArrayList<>();
            tabEntities.add(new TabEntity("课表"));
            tabEntities.add(new TabEntity("师生"));
            tabEntities.add(new TabEntity("拜师"));
            tabEntities.add(new TabEntity("练声"));
            tabEntities.add(new TabEntity("口碑"));
        }
        return tabEntities;
    }

    public FragmentPagerAdapter getFragmentPagerAdapter() {
        if (fragmentPagerAdapter == null) {
            fragmentPagerAdapter = new FragmentPagerAdapter(getView().getFragment().getChildFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return getFragments().get(position);
                }

                @Override
                public int getCount() {
                    return tabEntities.size();
                }
            };
        }
        return fragmentPagerAdapter;
    }

    public OnTabSelectListener getTabSelectListener() {
        if (onTabSelectListener == null) {
            onTabSelectListener = new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    getView().getViewPager().setCurrentItem(position);
                }

                @Override
                public void onTabReselect(int position) {

                }
            };
        }
        return onTabSelectListener;
    }

    public ViewPager.OnPageChangeListener getPageChangeListener() {
        if (onPageChangeListener == null) {
            onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    getView().getCommonTabLayout().setCurrentTab(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            };
        }
        return onPageChangeListener;
    }
}
