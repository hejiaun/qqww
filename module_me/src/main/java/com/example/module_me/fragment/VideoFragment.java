package com.example.module_me.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.module_me.R;
import com.example.module_me.presenter.MyVideoFragmentPresenter;
import com.example.module_me.view_interface.IMyVideoFragmentView;
import com.flyco.tablayout.CommonTabLayout;

import example.common_base.base.BaseFragment;
import example.common_base.util.ConstantValuesUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的录像Fragment
 */
public class VideoFragment extends BaseFragment<MyVideoFragmentPresenter> implements IMyVideoFragmentView {
    CommonTabLayout ctl;
    ViewPager vp;

    /**
     * 判断
     */
    public static int who = 0;

    @Override
    public void initView() {
        super.initView();
        ctl = view.findViewById(R.id.ctlVideo);
        vp = view.findViewById(R.id.vpVideo);
    }

    /**
     * 获取MyVideoFragment对象
     *
     * @param who
     * @return
     */
    public static VideoFragment getInstence(int who) {
        VideoFragment.who = who;
        return new VideoFragment();
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_information_video;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyVideoFragmentPresenter createPresenter() {
        return new MyVideoFragmentPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        if (who == ConstantValuesUtil.OTHERS) {
            ctl.setVisibility(View.GONE);
        } else {
            ctl.setTabData(getPresenter().getTabs());
            ctl.setOnTabSelectListener(getPresenter().getTabSelectListener());
        }
        vp.setAdapter(getPresenter().getFragmentPagerAdapter());

        vp.addOnPageChangeListener(getPresenter().getOnPageChangeListener());
    }

    /**
     * View层向Presenter层提供VideoFragment
     *
     * @return VideoFragment
     */
    @Override
    public VideoFragment getFragment() {
        return this;
    }

    /**
     * View层向Presenter层提供CommonTabLayout
     *
     * @return CommonTabLayout
     */
    @Override
    public CommonTabLayout getCommonTabLayout() {
        return ctl;
    }

    /**
     * View层向Presenter层提供ViewPager
     *
     * @return ViewPager
     */
    @Override
    public ViewPager getViewPager() {
        return vp;
    }
}
