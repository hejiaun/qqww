package com.example.module_home.model;


import java.util.ArrayList;

import example.common_base.base.BaseModel;
import example.common_base.entity.HotFightEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 热门对战列表Fragment的Model
 */
public class HotFightListFragmentModel extends BaseModel {

    /**
     * @param action1
     */
    public void getFirstEntryData(Action1<ArrayList<HotFightEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<HotFightEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<HotFightEntity>> subscriber) {
                ArrayList<HotFightEntity> hotFightEntities = new ArrayList<HotFightEntity>();
                for (int i = 0; i < 50; i++) {
                    hotFightEntities.add(new HotFightEntity());
                }
                subscriber.onNext(hotFightEntities);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

}
