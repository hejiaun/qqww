package com.example.module_me.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.ApprenticeFragmentPresenter;
import com.example.module_me.view_interface.IApprenticeFragmentView;

import example.common_base.base.BaseFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  拜师fragment
 */
public class ApprenticeFragment extends BaseFragment<ApprenticeFragmentPresenter> implements View.OnClickListener, IApprenticeFragmentView {
    RecyclerView rcv;
    TextView currentPriceTextView;
    TextView tvSure;

    @Override
    public void initView() {
        super.initView();
        rcv = view.findViewById(R.id.rcv);
        tvSure = view.findViewById(R.id.tvSure);
        currentPriceTextView = view.findViewById(R.id.tvCurrentPrice);
        tvSure.setOnClickListener(this);
    }

    /**
     * 创建Fragment视图
     *
     * @param inflater           视图布局
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    /**
     * View层向Presenter层提供currentPriceTextView
     *
     * @return currentPriceTextView
     */
    @Override
    public TextView getCurrentPriceTextView() {
        return currentPriceTextView;
    }

    /**
     * View层向Presenter层提供RecyclerView
     *
     * @return RecyclerView
     */
    @Override
    public RecyclerView getRecyclerView() {
        return rcv;
    }

    /**
     * View层向Presenter层提供ApprenticeFragment
     *
     * @return ApprenticeFragment
     */
    @Override
    public ApprenticeFragment getFragment() {
        return this;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ApprenticeFragmentPresenter createPresenter() {
        return new ApprenticeFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_apprentice;
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

        ((DefaultItemAnimator) rcv.getItemAnimator()).setSupportsChangeAnimations(false);
    }


    /**
     * 点击事件监听
     *
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tvSure) {
            getPresenter().showDialog();
        }
    }
}
