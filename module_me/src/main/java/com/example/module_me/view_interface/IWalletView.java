package com.example.module_me.view_interface;

import android.app.Activity;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:钱包界面回调接口
 */
public interface IWalletView extends IBaseView {
    Activity getActivity();

    CommonTabLayout getCommonTabLayout();

    ViewPager getViewPager();
}
