package com.example.module_me.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.example.module_me.R;
import com.example.module_me.presenter.MyPhotoFragmentPresenter;
import com.example.module_me.view_interface.IMyPhotoFragmentView;

import example.common_base.base.BaseListFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的相册Fragment
 */
public class MyPhotoFragment extends BaseListFragment<MyPhotoFragmentPresenter> implements IMyPhotoFragmentView {

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyPhotoFragmentPresenter createPresenter() {
        return new MyPhotoFragmentPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(getPresenter().getRecyclerViewAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());
        rcv.setBackgroundColor(getResources().getColor(R.color.white));
    }

    /**
     * View层向Presenter层提供MyPhotoFragment
     *
     * @return MyPhotoFragment
     */
    @Override
    public MyPhotoFragment getFragment() {
        return this;
    }
}
