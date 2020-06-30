package com.example.module_home.model;

import java.util.ArrayList;

import example.common_base.base.BaseModel;
import example.common_base.entity.MyMultiplyEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:主页搜索功能的Activity的Model
 */
public class SearchInHomeActivityModel extends BaseModel {

    /**
     * 获取所有类型的搜索结果
     */
    public void getAllData(String keyword) {

    }

    /**
     * 获取伴奏类型的搜索结果
     *
     * @param keyword 搜索伴奏的关键字
     */
    public void getAccompanimentData(String keyword, Action1<ArrayList<MyMultiplyEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<MyMultiplyEntity>> subscriber) {
                ArrayList<MyMultiplyEntity> data = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    data.add(new MyMultiplyEntity(MyMultiplyEntity.ACCOMPANIENT));
                }
                subscriber.onNext(data);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

    /**
     * 获取用户类型的搜索结果
     *
     * @param keyword 搜索用户的关键字
     */
    public void getUserData(String keyword, Action1<ArrayList<MyMultiplyEntity>> action1) {

        Observable.create(new Observable.OnSubscribe<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<MyMultiplyEntity>> subscriber) {
                ArrayList<MyMultiplyEntity> data = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    data.add(new MyMultiplyEntity(MyMultiplyEntity.USER));
                }
                subscriber.onNext(data);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 获取比赛类型的搜索结果
     *
     * @param keyword 搜索比赛的关键字
     */
    public void getMatchData(String keyword, Action1<ArrayList<MyMultiplyEntity>> action1) {

        Observable.create(new Observable.OnSubscribe<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<MyMultiplyEntity>> subscriber) {
                ArrayList<MyMultiplyEntity> data = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    data.add(new MyMultiplyEntity(MyMultiplyEntity.MATCH));
                }
                subscriber.onNext(data);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

    /**
     * 获取作品类型的搜索结果
     *
     * @param keyword 搜索作品的关键字
     */
    public void getWorkData(String keyword, Action1<ArrayList<MyMultiplyEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<MyMultiplyEntity>> subscriber) {
                ArrayList<MyMultiplyEntity> data = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    data.add(new MyMultiplyEntity(MyMultiplyEntity.WORK));
                }
                subscriber.onNext(data);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

}
