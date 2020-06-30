package com.example.module_me.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.module_me.activity.WalletActivity;
import com.example.module_me.fragment.MusicCoinChargeFragment;
import com.example.module_me.fragment.VipChargeFragment;
import com.example.module_me.view_interface.IWalletView;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:钱包界面服务
 */
public class WalletPresenter extends BasePresenter<IWalletView> {
    ArrayList<CustomTabEntity> tabEntities = null;
    private ArrayList<BaseFragment> fragments;

    /**
     * 构造方法，初始化View层
     *
     * @param iWalletView View层接口
     */
    public WalletPresenter(IWalletView iWalletView) {
        super(iWalletView);
    }


    /**
     * 获取标题标签实体集合
     *
     * @return
     */
    public ArrayList<CustomTabEntity> getTabList() {
        if (tabEntities == null) {
            tabEntities = new ArrayList<>();
            tabEntities.add(new TabEntity("音乐币充值"));
            tabEntities.add(new TabEntity("会员充值"));
        }
        return tabEntities;
    }

    /**
     * 获取fragment集合
     *
     * @return
     */
    public ArrayList<BaseFragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments.add(new MusicCoinChargeFragment());
            fragments.add(new VipChargeFragment());
        }
        return fragments;
    }

    /**
     * 获取viewPager适配器
     *
     * @return
     */
    public FragmentPagerAdapter getViewPagerAdapter() {
        return new FragmentPagerAdapter(((WalletActivity) getView().getActivity()).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    /**
     * 获取ViewPager页面切换监听器
     *
     * @return
     */
    public ViewPager.OnPageChangeListener getPagerChangeListener() {
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

    /**
     * 获取标题标签点击监听器
     *
     * @return
     */
    public OnTabSelectListener getCommonTabLayoutClickListener() {
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
