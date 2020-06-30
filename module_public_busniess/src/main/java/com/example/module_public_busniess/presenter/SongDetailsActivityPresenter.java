package com.example.module_public_busniess.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;

import com.example.administrator.shengdoushi.business.public_business.fragment.HotCoverFragment;
import com.example.administrator.shengdoushi.business.public_business.fragment.SongStrategyFragment;
import com.example.administrator.shengdoushi.business.public_business.fragment.WaitChorusFragment;
import com.example.administrator.shengdoushi.business.public_business.view_interface.ISongDetailsActivityView;

import java.util.ArrayList;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SongDetailsActivityPresenter extends BasePresenter<ISongDetailsActivityView> {
    private ArrayList<BaseFragment> fragments;

    /**
     * 构造方法，初始化View层
     *
     * @param iSongDetailsActivityView View层接口
     */
    public SongDetailsActivityPresenter(ISongDetailsActivityView iSongDetailsActivityView) {
        super(iSongDetailsActivityView);
    }

    /**
     * 获取viewpager适配器
     *
     * @param fragmentManager
     * @return
     */
    public FragmentPagerAdapter getViewPageAdapter(FragmentManager fragmentManager) {
        return new FragmentPagerAdapter(fragmentManager) {
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

    public ArrayList<BaseFragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments.add(new SongStrategyFragment());
            fragments.add(new HotCoverFragment());
            fragments.add(new WaitChorusFragment());
        }
        return fragments;
    }

}
