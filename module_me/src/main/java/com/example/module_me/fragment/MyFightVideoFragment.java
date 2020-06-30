package com.example.module_me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.example.module_me.presenter.MyFightVideoFragmentPresenter;
import com.example.module_me.view_interface.IMyFightVideoFragmentView;

import example.common_base.base.BaseListFragment;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的对战记录录像Fragment
 */
public class MyFightVideoFragment extends BaseListFragment<MyFightVideoFragmentPresenter> implements IMyFightVideoFragmentView {

    /**创建presenter
     * @return presenter
     */
    @Override
    public MyFightVideoFragmentPresenter createPresenter() {
        return new MyFightVideoFragmentPresenter(this);
    }


    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });
    }
}
