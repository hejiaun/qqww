package com.example.module_me.fragment;

import android.graphics.Color;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.module_me.presenter.MyCourseVideoFragmentPresenter;
import com.example.module_me.view_interface.IMyCourseVideoFragmentView;

import example.common_base.base.BaseListFragment;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的课程录像Fragment
 */
public class MyCourseVideoFragment extends BaseListFragment<MyCourseVideoFragmentPresenter> implements IMyCourseVideoFragmentView {

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyCourseVideoFragmentPresenter createPresenter() {
        return new MyCourseVideoFragmentPresenter(this);
    }

    /**
     * View层向Presenter层提供MyCourseVideoFragment
     *
     * @return MyCourseVideoFragment
     */
    @Override
    public MyCourseVideoFragment getFragment() {
        return this;
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());
        rcv.setBackgroundColor(Color.WHITE);
    }


}
