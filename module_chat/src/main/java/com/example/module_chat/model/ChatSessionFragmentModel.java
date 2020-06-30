package com.example.module_chat.model;


import java.util.ArrayList;

import example.common_base.app.MyApplication;
import example.common_base.base.BaseModel;
import example.common_base.dao.ChatSessionEntityDao;
import example.common_base.entity.ChatSessionEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ChatSessionFragmentModel extends BaseModel {

    /**
     * 获取所有聊天记录
     *
     * @param action1 异步获取完成后的回调的操作类
     */
    public void getAllChatSessionData(Action1<ArrayList<ChatSessionEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<ChatSessionEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<ChatSessionEntity>> subscriber) {
                ArrayList<ChatSessionEntity> result = (ArrayList<ChatSessionEntity>) MyApplication.getApplication()
                        .getDaoSession()
                        .getChatSessionEntityDao()
                        .queryBuilder()
                        .orderAsc(ChatSessionEntityDao.Properties.SessionID)
                        .build()
                        .list();
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

}
