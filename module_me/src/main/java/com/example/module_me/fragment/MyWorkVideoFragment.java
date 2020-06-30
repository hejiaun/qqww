package com.example.module_me.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.example.module_me.presenter.MyWorkVideoFragmentPresenter;
import com.example.module_me.view_interface.IMyWorkVideoFragmentView;

import example.common_base.base.BaseListFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的作品录像Fragment
 */
public class MyWorkVideoFragment extends BaseListFragment<MyWorkVideoFragmentPresenter> implements IMyWorkVideoFragmentView {

    @Override
    public MyWorkVideoFragmentPresenter createPresenter() {
        return new MyWorkVideoFragmentPresenter(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(getPresenter().getAdapter());

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });
        getPresenter().getAdapter().setOnItemChildClickListener(getPresenter().getOnItemChildClickListener());
    }


    @Override
    public Context getFragment() {
        return getActivity();
    }
}