package com.example.module_chat.model;


import java.util.ArrayList;

import example.common_base.app.MyApplication;
import example.common_base.base.BaseModel;
import example.common_base.dao.UserEntityDao;
import example.common_base.entity.ChatContactsEntity;
import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.UserEntity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:通讯录Activity的Model
 */
public class ChatContactsModel extends BaseModel {

    /**
     * 获取通讯录的所有数据
     *
     * @param action1 异步获取完成后的回调的操作类
     */
    public void getAllData(Action1<ArrayList<MyMultiplyEntity>> action1) {

        rx.Observable.create(new rx.Observable.OnSubscribe<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<MyMultiplyEntity>> subscriber) {
                ArrayList<MyMultiplyEntity> data = new ArrayList<MyMultiplyEntity>();
                ArrayList<UserEntity> userEntities = (ArrayList<UserEntity>) MyApplication.getApplication()
                        .getDaoSession()
                        .getUserEntityDao()
                        .queryBuilder()
                        .orderAsc(UserEntityDao.Properties.UserID)
                        .build()
                        .list();
                for (UserEntity entity : userEntities) {
                    data.add(new ChatContactsEntity(entity.getHeadURL(), entity.getName(), false));
                }
                subscriber.onNext(data);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }
}
