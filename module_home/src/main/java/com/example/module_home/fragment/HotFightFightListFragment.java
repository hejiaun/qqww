package com.example.module_home.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

//import com.example.administrator.shengdoushi.R;
//import com.example.administrator.shengdoushi.business.home.adapter.HotFightAdapter;
//import com.example.administrator.shengdoushi.business.home.presenter.HotFightListFragmentPresenter;
//import com.example.administrator.shengdoushi.business.home.view_interface.IHotFightListFragmentView;
import com.example.module_home.R;
import com.example.module_home.adapter.HotFightAdapter;
import com.example.module_home.presenter.HotFightListFragmentPresenter;
import com.example.module_home.view_interface.IHotFightListFragmentView;

import java.util.ArrayList;

import example.common_base.base.BaseListFragment;
import example.common_base.entity.HotFightEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:热门对战列表Fragment
 */
public class HotFightFightListFragment extends BaseListFragment<HotFightListFragmentPresenter> implements IHotFightListFragmentView {

    HotFightAdapter adapter = null;
    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public HotFightListFragmentPresenter createPresenter() {
        return new HotFightListFragmentPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new HotFightAdapter(R.layout.item_hot_fight, new ArrayList<HotFightEntity>());

        rcv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcv.setAdapter(adapter);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });

    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        getPresenter().requestFirstEntry();
    }

    /**
     * View层想Presenter层提供HotFightAdapter
     *
     * @return HotFightAdapter
     */
    @Override
    public HotFightAdapter getAdapter() {
        return adapter;
    }
}
