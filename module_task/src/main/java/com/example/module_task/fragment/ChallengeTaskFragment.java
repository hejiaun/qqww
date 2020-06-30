package com.example.module_task.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.module_task.R;
import com.example.module_task.adapter.TaskAdapter;
import com.example.module_task.presenter.ChallengeTaskPresenter;
import com.example.module_task.view_interface.IChallengeTaskView;

import java.util.ArrayList;

import example.common_base.app.MyApplication;
import example.common_base.base.BaseListFragment;
import example.common_base.entity.TaskEntity;
import example.common_base.util.DensityUtils;
import example.common_base.util.RecyclerViewItemDecorationUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:挑战任务fragment
 */
public class ChallengeTaskFragment extends BaseListFragment<ChallengeTaskPresenter> implements IChallengeTaskView {

    private TaskAdapter adapter;

    /**
     * 创建Presenter
     *
     * @return presenter
     */
    @Override
    public ChallengeTaskPresenter createPresenter() {
        return new ChallengeTaskPresenter(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new TaskAdapter(R.layout.item_dialy_task, new ArrayList<TaskEntity>());
        adapter.setEmptyView(View.inflate(getActivity(), R.layout.view_state, null));
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(DensityUtils.dp2px(MyApplication.getApplication(), 2), 0, 0, 0));
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
     * View层向Presenter层提供TaskAdapter
     *
     * @return TaskAdapter
     */
    @Override
    public TaskAdapter getAdapter() {
        return adapter;
    }

}
