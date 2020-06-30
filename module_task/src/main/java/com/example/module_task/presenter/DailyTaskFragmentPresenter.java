package com.example.module_task.presenter;

import com.example.module_task.model.DailyTaskFragmentModel;
import com.example.module_task.view_interface.IDailyTaskFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.TaskEntity;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:日常任务的Fragment的Presenter层
 */
public class DailyTaskFragmentPresenter extends BasePresenter<IDailyTaskFragmentView> {
    DailyTaskFragmentModel model;

    public DailyTaskFragmentPresenter(IDailyTaskFragmentView view) {
        super(view);
        model = new DailyTaskFragmentModel();
    }

    public void requestFirstEntryData() {
        model.getTaskListData(new Action1<ArrayList<TaskEntity>>() {
            @Override
            public void call(ArrayList<TaskEntity> taskEntities) {
                getView().getAdapter().addData(taskEntities);
            }
        });
    }

}
