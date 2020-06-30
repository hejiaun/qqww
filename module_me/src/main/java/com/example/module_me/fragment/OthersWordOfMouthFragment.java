package com.example.module_me.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.example.module_me.presenter.OthersWordOfMouthFragmentPresenter;
import com.example.module_me.view_interface.IOthersWordOfMouthFragmentView;

import example.common_base.base.BaseListFragment;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 其他人的口碑fragment
 */
public class OthersWordOfMouthFragment extends BaseListFragment<OthersWordOfMouthFragmentPresenter> implements IOthersWordOfMouthFragmentView {

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public OthersWordOfMouthFragmentPresenter createPresenter() {
        return new OthersWordOfMouthFragmentPresenter(this);
    }

    /**
     * View层向Presenter层提供OthersWordOfMouthFragment
     *
     * @return OthersWordOfMouthFragment
     */
    @Override
    public OthersWordOfMouthFragment getFragment() {
        return this;
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoraction());
    }


}
