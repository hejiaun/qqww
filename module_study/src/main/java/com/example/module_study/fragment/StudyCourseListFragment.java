package com.example.module_study.fragment;

import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_study.R;
import com.example.module_study.adapter.StudyCourseListAdapter;
import com.example.module_study.presenter.StudyCourseListFragmentPresenter;
import com.example.module_study.view_interface.IStudyCourseListFragmentView;

import java.util.ArrayList;

import example.common_base.base.BaseListFragment;
import example.common_base.entity.StudyCourseEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我要学音乐Fragment
 */
public class StudyCourseListFragment extends BaseListFragment<StudyCourseListFragmentPresenter> implements
        BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        IStudyCourseListFragmentView,
        SwipeRefreshLayout.OnRefreshListener{
    private StudyCourseListAdapter adapter = null;
    private Dialog dialog;

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public StudyCourseListFragmentPresenter createPresenter() {
        return new StudyCourseListFragmentPresenter(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new StudyCourseListAdapter(R.layout.item_study_course, new ArrayList<StudyCourseEntity>());

        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(getPresenter().getItemDecoration());
        //监听点击
        adapter.setOnItemClickListener(this);
        adapter.setOnLoadMoreListener(this, rcv);
        srl.setOnRefreshListener(this);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        getPresenter().requestFirstEntryData();
    }

    /**
     * 列表刷新
     */
    @Override
    public void onRefresh() {
        getPresenter().requestRefreshData();
    }

    /**
     * 列表Item点击
     *
     * @param adapter  列表适配器
     * @param view     Item布局
     * @param position Item位置
     */
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showDialog();
    }

    /**
     * 显示订阅课程对话框
     */
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_subscribe);
        }
        dialog.show();
    }

    @Override
    public StudyCourseListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return srl;
    }

    @Override
    public void onLoadMoreRequested() {
        getPresenter().requestMoreData();
    }

}
