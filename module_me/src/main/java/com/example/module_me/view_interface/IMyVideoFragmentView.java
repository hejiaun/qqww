package com.example.module_me.view_interface;

import android.support.v4.view.ViewPager;

import com.example.module_me.fragment.VideoFragment;
import com.flyco.tablayout.CommonTabLayout;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public interface IMyVideoFragmentView extends IBaseView {
    VideoFragment getFragment();

    ViewPager getViewPager();

    CommonTabLayout getCommonTabLayout();
}
