package com.example.module_me.presenter;

import com.example.module_me.view_interface.IChangePasswordActivityView;

import example.common_base.base.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class ChangePasswordActivityPresenter extends BasePresenter<IChangePasswordActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iChangePasswordActivityView View层接口
     */
    public ChangePasswordActivityPresenter(IChangePasswordActivityView iChangePasswordActivityView) {
        super(iChangePasswordActivityView);
    }

    /**
     * 获取验证码
     */
    public void getValidateCode() {
        getView().getValidateTextView().setClickable(false);
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        int i = 60;
                        while (i >= 0) {
                            i--;
                            subscriber.onNext(i);
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer > 0) {
                            getView().getValidateTextView().setText(integer.intValue() + "秒");
                        } else if (integer.intValue() == 0) {
                            getView().getValidateTextView().setText("获取验证码");
                            getView().getValidateTextView().setClickable(true);
                        }
                    }
                });
    }


}
