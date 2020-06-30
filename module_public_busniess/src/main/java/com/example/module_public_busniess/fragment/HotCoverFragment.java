package com.example.module_public_busniess.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.shengdoushi.R;
import com.example.administrator.shengdoushi.business.public_business.presenter.HotCoverFragmentPresenter;
import com.example.administrator.shengdoushi.business.public_business.view_interface.IHotCoverFragmentView;

import butterknife.BindView;
import example.common_base.base.BaseFragment;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:热门翻唱Fragment
 */
public class HotCoverFragment extends BaseFragment<HotCoverFragmentPresenter> implements IHotCoverFragmentView {
    @BindView(R.id.rcv)
    RecyclerView rcv;

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public HotCoverFragmentPresenter createPresenter() {
        return new HotCoverFragmentPresenter(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(getPresenter().getAdapter());
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_hotcover;
    }
}
