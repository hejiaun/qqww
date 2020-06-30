package com.example.module_me.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.module_me.fragment.MyFightVideoFragment;
import com.example.module_me.fragment.MyWorkVideoFragment;
import com.example.module_me.fragment.VideoFragment;
import com.example.module_me.view_interface.IMyVideoFragmentView;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;
import example.common_base.util.ConstantValuesUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyVideoFragmentPresenter extends BasePresenter<IMyVideoFragmentView> {
    private ArrayList<CustomTabEntity> tabEntities = null;
    private ArrayList<Fragment> fragments = null;
    private FragmentPagerAdapter fragmentPagerAdapter = null;
    private OnTabSelectListener onTabSelectListener = null;
    private ViewPager.OnPageChangeListener onPageChangeListener = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyVideoFragmentView View层接口
     */
    public MyVideoFragmentPresenter(IMyVideoFragmentView iMyVideoFragmentView) {
        super(iMyVideoFragmentView);
    }

    public ArrayList getTabs() {
        if (tabEntities == null) {
            tabEntities = new ArrayList<>();
            tabEntities.add(new TabEntity("对战"));
            if (VideoFragment.who == ConstantValuesUtil.MYSELF) {
                tabEntities.add(new TabEntity("作品"));
            }
        }
        return tabEntities;
    }

    public ArrayList<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments.add(new MyFightVideoFragment());
            if (VideoFragment.who == ConstantValuesUtil.MYSELF) {
                fragments.add(new MyWorkVideoFragment());
            }
        }
        return fragments;
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

    public FragmentPagerAdapter getFragmentPagerAdapter() {
        if (fragmentPagerAdapter == null) {
            fragmentPagerAdapter = new FragmentPagerAdapter(getView().getFragment().getChildFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return getFragments().get(position);
                }

                @Override
                public int getCount() {
                    return getFragments().size();
                }
            };
        }
        return fragmentPagerAdapter;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
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
