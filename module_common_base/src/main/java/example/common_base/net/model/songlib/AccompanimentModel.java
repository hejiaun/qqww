package example.common_base.net.model.songlib;


import java.util.Collection;

import cn.vfighter.songlib.bean.Accompaniment;
import cn.vfighter.songlib.param.SearchByKeyWordParam;
import example.common_base.net.controller.songlib.AccompanimentController;
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
public class AccompanimentModel {
    AccompanimentController controller = null;

    public AccompanimentModel() {
        controller = new AccompanimentController();
    }

    /**
     * 根据伴奏id获取公开伴奏
     *
     * @param accompanimentId 伴奏id
     * @param action1         异步回调
     * @return
     */
    public void getPubAccompanimentById(final long accompanimentId, Action1<Accompaniment> action1) {
        Observable.create(new Observable.OnSubscribe<Accompaniment>() {
            @Override
            public void call(Subscriber<? super Accompaniment> subscriber) {
                Accompaniment result = controller.getPubAccompanimentById(accompanimentId);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

    /**
     * 通过伴奏名称获取公开的伴奏
     *
     * @param searchByKeyWordParam 伴奏名称
     * @param action1              异步回调
     * @return
     */
    public void getPubKeyWordAccompanimentByName(final SearchByKeyWordParam searchByKeyWordParam, final Action1<Collection<Accompaniment>> action1) {
        Observable
                .create(new Observable.OnSubscribe<Collection<Accompaniment>>() {
                    @Override
                    public void call(Subscriber<? super Collection<Accompaniment>> subscriber) {
                        Collection<Accompaniment> result = controller.getPubKeyWordAccompanimentByName(searchByKeyWordParam);
                        subscriber.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 通过原唱歌手获取公开的伴奏
     *
     * @param searchByKeyWordParam 原唱歌手名称
     * @param action1              异步回调
     * @return
     */
    public void getPubKeyWordAccompanimentByOriginal(final SearchByKeyWordParam searchByKeyWordParam, Action1<Collection<Accompaniment>> action1) {
        Observable
                .create(new Observable.OnSubscribe<Collection<Accompaniment>>() {
                    @Override
                    public void call(Subscriber<? super Collection<Accompaniment>> subscriber) {
                        Collection<Accompaniment> result = controller.getPubKeyWordAccompanimentByOriginal(searchByKeyWordParam);
                        subscriber.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

}