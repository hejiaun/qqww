package com.example.module_pk.presenter;

import com.example.module_pk.view_interface.IPkLoadingActivityView;

import example.common_base.base.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 加载Activity的Presenter层
 */
public class PkLoadingActivityPresenter extends BasePresenter<IPkLoadingActivityView> {

    private boolean isLoading = false;

    /**
     * 构造方法，初始化View层
     *
     * @param iPkLoadingActivityView View层接口
     */
    public PkLoadingActivityPresenter(IPkLoadingActivityView iPkLoadingActivityView) {
        super(iPkLoadingActivityView);
    }

    public void load(Action1<Integer> action1) {
        isLoading = true;
        Observable.create(new Observable.OnSubscribe<Integer>() {
            private int second;
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                while (second <= 100 && isLoading) {
                    try {
                        subscriber.onNext(second++);
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(action1);
    }

}
