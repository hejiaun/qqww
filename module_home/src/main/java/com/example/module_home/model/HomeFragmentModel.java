package com.example.module_home.model;

import java.util.ArrayList;

import example.common_base.base.BaseModel;
import example.common_base.entity.RankingListEntity;
import example.common_base.util.ConstantValuesUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 主页名人榜列表Model
 */
public class HomeFragmentModel extends BaseModel {
    // TODO: 2018/12/4 获取名人榜
    public void getFamousRankListData(Action1<ArrayList<RankingListEntity>> action1) {
        Observable.create(new Observable.OnSubscribe<ArrayList<RankingListEntity>>() {
            @Override
            public void call(Subscriber<? super ArrayList<RankingListEntity>> subscriber) {
                ArrayList<RankingListEntity> rankingListEntities = new ArrayList<RankingListEntity>();
                for (int i = 0; i < 50; i++) {
                    if (i % 3 == 0) {
                        rankingListEntities.add(new RankingListEntity
                                (i + 1
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE1
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE2
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE3
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE4
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE5
                                ));
                    } else if (i % 2 == 0) {
                        rankingListEntities.add(new RankingListEntity
                                (i + 1
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE6
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE7
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE8
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE8
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE10
                                ));
                    } else if (i % 4 == 0) {
                        rankingListEntities.add(new RankingListEntity
                                (i + 1
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE11
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE1
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE2
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE5
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE7
                                ));
                    } else {
                        rankingListEntities.add(new RankingListEntity
                                (i + 1
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE3
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE9
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE5
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE11
                                        , ConstantValuesUtil.IMAGE_URL_EXAMPLE4
                                ));
                    }
                }
                subscriber.onNext(rankingListEntities);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
