package com.example.module_me.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.example.module_me.presenter.WordOfMouthFragmentPresenter;
import com.example.module_me.view_interface.IWordOfMouthFragmentView;

import example.common_base.base.BaseListFragment;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 口碑fragment
 */
public class WordOfMouthFragment extends BaseListFragment<WordOfMouthFragmentPresenter> implements IWordOfMouthFragmentView {

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public WordOfMouthFragmentPresenter createPresenter() {
        return new WordOfMouthFragmentPresenter(this);
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
    }

    /**
     * View层向Presenter层提供WordOfMouthFragment
     *
     * @return WordOfMouthFragment
     */
    @Override
    public WordOfMouthFragment getFragment() {
        return this;
    }
}
