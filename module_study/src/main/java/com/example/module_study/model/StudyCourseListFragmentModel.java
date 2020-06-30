package com.example.module_study.model;

import java.util.ArrayList;

import example.common_base.base.BaseModel;
import example.common_base.entity.StudyCourseEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:课程列表Fragment的Model层
 */
public class StudyCourseListFragmentModel extends BaseModel {

    /**
     * 异步获取首次进入界面的数据
     *
     * @param action1 异步获取数据成功后的回调
     */
    public void getFristEntryData(Action1<ArrayList<StudyCourseEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<StudyCourseEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<StudyCourseEntity>> subscriber) {
                ArrayList<StudyCourseEntity> studyCourseEntities = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    StudyCourseEntity studyCourseEntity = new StudyCourseEntity();
                    studyCourseEntity.setCourseType("流行唱法");
                    studyCourseEntity.setDate("6月17日" + '\n' + "    " + "7：00-19：00");
                    studyCourseEntity.setTeacher("晨曦");
                    studyCourseEntity.setLevel("流行唱法" + i + "级");
                    studyCourseEntities.add(studyCourseEntity);
                }
                subscriber.onNext(studyCourseEntities);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 异步获取更多的列表数据
     *
     * @param action1 异步获取数据成功后的回调
     */
    public void getMoreCourseListData(Action1<ArrayList<StudyCourseEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<StudyCourseEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<StudyCourseEntity>> subscriber) {
                ArrayList<StudyCourseEntity> studyCourseEntities = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    StudyCourseEntity studyCourseEntity = new StudyCourseEntity();
                    studyCourseEntity.setCourseType("流行唱法");
                    studyCourseEntity.setDate("6月17日" + '\n' + "    " + "7：00-19：00");
                    studyCourseEntity.setTeacher("晨曦");
                    studyCourseEntity.setLevel("流行唱法" + i + "级");
                    studyCourseEntities.add(studyCourseEntity);
                }
                subscriber.onNext(studyCourseEntities);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
