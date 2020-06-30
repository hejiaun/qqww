package com.example.module_task.presenter;

import com.example.module_task.model.ChallengetTaskFragmentModel;
import com.example.module_task.view_interface.IChallengeTaskView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.TaskEntity;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:挑战任务的Fragment的Presenter层
 */
public class ChallengeTaskPresenter extends BasePresenter<IChallengeTaskView> {
    private ChallengetTaskFragmentModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iChallengeTaskView View层接口
     */
    public ChallengeTaskPresenter(IChallengeTaskView iChallengeTaskView) {
        super(iChallengeTaskView);
        model = new ChallengetTaskFragmentModel();
    }

    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {
        model.getTaskListData(new Action1<ArrayList<TaskEntity>>() {
            @Override
            public void call(ArrayList<TaskEntity> taskEntities) {
                getView().getAdapter().addData(taskEntities);
            }
        });
    }

}
