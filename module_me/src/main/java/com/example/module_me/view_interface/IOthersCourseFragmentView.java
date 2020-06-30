package com.example.module_me.view_interface;

import android.support.v4.view.ViewPager;

import com.example.module_me.fragment.OthersCourseFragment;
import com.flyco.tablayout.CommonTabLayout;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:别人的课程Fragment的View层接口
 */
public interface IOthersCourseFragmentView extends IBaseView {
    OthersCourseFragment getFragment();

    ViewPager getViewPager();

    CommonTabLayout getCommonTabLayout();
}
