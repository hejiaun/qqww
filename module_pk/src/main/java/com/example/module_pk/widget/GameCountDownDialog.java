package com.example.module_pk.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.module_pk.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 游戏倒数对话框
 */
public class GameCountDownDialog extends Dialog {

    private View view;
    private TextView tvSecond;
    //----------------------倒数时长-----------------------//
    private int duration = 5;
    private int progressSecond;
    private TextView tv;


    public GameCountDownDialog(@NonNull Context context) {
        this(context, R.style.FullWidthDialogStyle);
    }

    public GameCountDownDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        view = View.inflate(context, R.layout.view_game_countdown, null);
        setContentView(view);
        initView();
        initConfig();
    }

    private void initConfig() {
        setCancelable(false);

        //----------------------设置窗口位置、大小-----------------------//
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        tvSecond.setText(duration + "");
    }

    private void initView() {
        tvSecond = view.findViewById(R.id.tvSecond);
        tv = view.findViewById(R.id.tv);
    }

    /**
     * 设置时长
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
        tvSecond.setText(duration + "");
    }

    /**
     * 开始倒数
     */
    public void startCountDown() {
        progressSecond = duration;
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        while (progressSecond >= 0) {
                            try {
                                subscriber.onNext(progressSecond--);
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer.intValue() != 0) {
                            GameCountDownDialog.this.tvSecond.setText(integer.intValue() + "");
                        } else {
                            dismiss();
                        }
                    }
                });
    }


}
