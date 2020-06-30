package com.example.module_me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.presenter.FollowFragmentPresenter;
import com.example.module_me.view_interface.IFollowFragmentView;

import example.common_base.base.BaseListFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:我关注的人列表Fragment
 */
public class
FollowFragment extends BaseListFragment<FollowFragmentPresenter> implements IFollowFragmentView {
    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public FollowFragmentPresenter createPresenter() {
        return new FollowFragmentPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(getPresenter().getAdapter());
        getPresenter().getAdapter().loadMoreEnd();
        getPresenter().getAdapter().setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getAdapter().loadMoreComplete();
            }
        }, rcv);
        
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });
    }
}
