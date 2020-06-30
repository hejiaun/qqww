package com.example.module_task.model;

import java.util.ArrayList;

import example.common_base.base.BaseModel;
import example.common_base.entity.TaskEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:日常任务Fragment的Model层
 */
public class DailyTaskFragmentModel extends BaseModel {

    /**
     * 获取任务列表集合数据
     *
     * @param action1
     */
    public void getTaskListData(Action1<ArrayList<TaskEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<TaskEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<TaskEntity>> subscriber) {
                ArrayList<TaskEntity> data = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    if (i % 2 == 0) {
                        data.add(new TaskEntity(false, true, TaskEntity.SIGN_TASK, "每日签到"));
                    } else if (i % 3 == 0) {
                        data.add(new TaskEntity(false, false, TaskEntity.EVALUATE_TASK, "评价作品"));
                    } else if (i % 5 == 0) {
                        data.add(new TaskEntity(false, false, TaskEntity.SHARE_TASK, "分享作品"));
                    } else if (i % 7 == 0) {
                        data.add(new TaskEntity(false, false, TaskEntity.FIGHT_TASK, "进行一场对战"));
                    } else {
                        data.add(new TaskEntity(true, true, TaskEntity.SIGN_TASK, "每日签到"));
                    }
                }
                subscriber.onNext(data);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
