package com.example.module_chat.model;


import java.util.ArrayList;

import example.common_base.base.BaseModel;
import example.common_base.entity.ChatMessageEntity;
import example.common_base.util.ConstantValuesUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:聊天Activity的Model
 */
public class ChatActivityModel extends BaseModel {

    /**
     * 聊天记录
     *
     * @param action1 异步获取完成后的回调的操作类
     */
    public void getChatRecord(Action1<ArrayList<ChatMessageEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<ChatMessageEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<ChatMessageEntity>> subscriber) {
                ArrayList<ChatMessageEntity> messageEntities = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    ChatMessageEntity chatMessageEntity;
                    if (i % 2 == 0) {
                        chatMessageEntity = new ChatMessageEntity(ChatMessageEntity.MESSAGE_TYPE_TEXT, ChatMessageEntity.LEFT, i + 0l, i + "", ConstantValuesUtil.IMAGE_URL_EXAMPLE2, System.currentTimeMillis());
                        chatMessageEntity.setTextContent("666");
                    } else if (i % 3 == 0) {
                        chatMessageEntity = new ChatMessageEntity(ChatMessageEntity.MESSAGE_TYPE_TEXT, ChatMessageEntity.RIGHT, i + 0l, i + "", ConstantValuesUtil.IMAGE_URL_EXAMPLE6, System.currentTimeMillis());
                        chatMessageEntity.setTextContent("666");
                    } else if (i % 5 == 0) {
                        chatMessageEntity = new ChatMessageEntity(ChatMessageEntity.MESSAGE_TYPE_IMAGE, ChatMessageEntity.LEFT, i + 0l, i + "", ConstantValuesUtil.IMAGE_URL_EXAMPLE8, System.currentTimeMillis());
                        chatMessageEntity.setImageUrl(ConstantValuesUtil.IMAGE_URL_EXAMPLE3);
                    } else if (i % 7 == 0) {
                        chatMessageEntity = new ChatMessageEntity(ChatMessageEntity.MESSAGE_TYPE_IMAGE, ChatMessageEntity.LEFT, i + 0l, i + "", ConstantValuesUtil.IMAGE_URL_EXAMPLE9, System.currentTimeMillis());
                        chatMessageEntity.setImageUrl(ConstantValuesUtil.IMAGE_URL_EXAMPLE3);
                    } else {
                        chatMessageEntity = new ChatMessageEntity(System.currentTimeMillis(), ChatMessageEntity.DATE);
                    }
                    messageEntities.add(chatMessageEntity);
                }
                subscriber.onNext(messageEntities);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
