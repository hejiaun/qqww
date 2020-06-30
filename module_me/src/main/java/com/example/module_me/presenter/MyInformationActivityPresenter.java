package com.example.module_me.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.module_me.fragment.MyInformationFragment;
import com.example.module_me.fragment.MyPhotoFragment;
import com.example.module_me.fragment.MyRelationshipFragment;
import com.example.module_me.fragment.VideoFragment;
import com.example.module_me.view_interface.IMyInformationActivityView;
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
public class MyInformationActivityPresenter extends BasePresenter<IMyInformationActivityView> {
    private ArrayList<CustomTabEntity> tabEntities;
    private ArrayList<Fragment> fragments;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyInformationActivityView View层接口
     */
    public MyInformationActivityPresenter(IMyInformationActivityView iMyInformationActivityView) {
        super(iMyInformationActivityView);
    }


    /**
     * 获取导航栏tab实体集合
     *
     * @return
     */
    public ArrayList<CustomTabEntity> getTabEntities() {
        if (tabEntities == null) {
            tabEntities = new ArrayList<>();
            tabEntities.add(new TabEntity("资料"));
            tabEntities.add(new TabEntity("录像"));
            tabEntities.add(new TabEntity("关系"));
            tabEntities.add(new TabEntity("相册"));
        }
        return tabEntities;
    }

    public ArrayList<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments = new ArrayList<Fragment>();
            fragments.add(new MyInformationFragment());
            fragments.add(VideoFragment.getInstence(ConstantValuesUtil.MYSELF));
            fragments.add(new MyRelationshipFragment());
            fragments.add(new MyPhotoFragment());
        }
        return fragments;
    }

    public FragmentPagerAdapter getFragmentPagerAdapter(FragmentManager fragmentManager) {
        return new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public ViewPager.OnPageChangeListener getViewPagerPagerChangerListener() {
        return new ViewPager.OnPageChangeListener() {
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

    public OnTabSelectListener getTabSelectListener() {
        return new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                getView().getViewPager().setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        };
    }
}
