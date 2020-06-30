package com.example.module_me.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.module_me.fragment.FansFragment;
import com.example.module_me.fragment.FollowFragment;
import com.example.module_me.fragment.ListenFragment;
import com.example.module_me.view_interface.IMyRelationshipFragmentView;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyRelationshipFragmentPresenter extends BasePresenter<IMyRelationshipFragmentView> {
    private ArrayList<CustomTabEntity> tabEntities = null;
    private ArrayList<Fragment> fragments = null;
    private FragmentPagerAdapter fragmentPagerAdapter = null;
    private OnTabSelectListener onTabSelectListener = null;
    private ViewPager.OnPageChangeListener onPageChangeListener = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyRelationshipFragmentView View层接口
     */
    public MyRelationshipFragmentPresenter(IMyRelationshipFragmentView iMyRelationshipFragmentView) {
        super(iMyRelationshipFragmentView);
    }

    public ArrayList getTabs() {
        if (tabEntities == null) {
            tabEntities = new ArrayList<>();
            tabEntities.add(new TabEntity("关注"));
            tabEntities.add(new TabEntity("粉丝"));
            tabEntities.add(new TabEntity("收听"));
        }
        return tabEntities;
    }

    public ArrayList<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments.add(new FollowFragment());
            fragments.add(new FansFragment());
            fragments.add(new ListenFragment());
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
