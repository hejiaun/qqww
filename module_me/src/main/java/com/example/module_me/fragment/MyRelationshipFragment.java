package com.example.module_me.fragment;

import android.support.v4.view.ViewPager;

import com.example.module_me.R;
import com.example.module_me.presenter.MyRelationshipFragmentPresenter;
import com.example.module_me.view_interface.IMyRelationshipFragmentView;
import com.flyco.tablayout.CommonTabLayout;

import example.common_base.base.BaseFragment;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的关系Fragment
 */
public class MyRelationshipFragment extends BaseFragment<MyRelationshipFragmentPresenter> implements
        IMyRelationshipFragmentView {
    CommonTabLayout ctl;
    ViewPager vp;

    @Override
    public void initView() {
        super.initView();
        ctl = view.findViewById(R.id.ctlRelationship);
        vp = view.findViewById(R.id.vpRelationship);
    }

    /**
     * 基础配置
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_information_relationship;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyRelationshipFragmentPresenter createPresenter() {
        return new MyRelationshipFragmentPresenter(this);
    }


    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        ctl.setTabData(getPresenter().getTabs());
        vp.setAdapter(getPresenter().getFragmentPagerAdapter());

        ctl.setOnTabSelectListener(getPresenter().getTabSelectListener());
        vp.addOnPageChangeListener(getPresenter().getOnPageChangeListener());
    }


    /**
     * View层向Presenter层提供 MyRelationshipFragment
     *
     * @return MyRelationshipFragment
     */
    @Override
    public MyRelationshipFragment getFragment() {
        return this;
    }

    /**
     * View层向Presenter层提供 CommonTabLayout
     *
     * @return CommonTabLayout
     */
    @Override
    public CommonTabLayout getCommonTabLayout() {
        return ctl;
    }

    /**
     * View层向Presenter层提供 ViewPager
     *
     * @return ViewPager
     */
    @Override
    public ViewPager getViewPager() {
        return vp;
    }
}
