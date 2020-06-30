package com.example.module_me.fragment;

import android.support.v4.view.ViewPager;

import com.example.module_me.R;
import com.example.module_me.presenter.OthersCourseFragmentPresenter;
import com.example.module_me.view_interface.IOthersCourseFragmentView;
import com.flyco.tablayout.CommonTabLayout;

import example.common_base.base.BaseFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的课程Fragment
 */
public class OthersCourseFragment extends BaseFragment<OthersCourseFragmentPresenter> implements IOthersCourseFragmentView {
    CommonTabLayout ctl;
    ViewPager vp;

    @Override
    public void initView() {
        super.initView();
        ctl=view.findViewById(R.id.ctlCourse);
        vp=view.findViewById(R.id.vpCourse);

    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public OthersCourseFragmentPresenter createPresenter() {
        return new OthersCourseFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_information_course;
    }

    /**
     * 加载配置
     */
    @Override
    public void initConfig() {
        ctl.setTabData(getPresenter().getTabs());
        vp.setAdapter(getPresenter().getFragmentPagerAdapter());

        ctl.setOnTabSelectListener(getPresenter().getTabSelectListener());
        vp.addOnPageChangeListener(getPresenter().getPageChangeListener());
    }

    /**
     * View层向Presenter层提供OthersCourseFragment
     *
     * @return OthersCourseFragment
     */
    @Override
    public OthersCourseFragment getFragment() {
        return this;
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

    /**
     * View层向Presenter层提供CommonTabLayout
     *
     * @return CommonTabLayout
     */
    @Override
    public CommonTabLayout getCommonTabLayout() {
        return ctl;
    }
}
