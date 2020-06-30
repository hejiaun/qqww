package com.example.module_pk.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
 * @Description: 匹配控件
 */
public class MatchView extends ConstraintLayout implements View.OnClickListener {

    private View view;
    private MatchHeadView ivPlayer1;
    private MatchHeadView ivPlayer2;
    private MatchHeadView ivRater1;
    private MatchHeadView ivRater3;
    private MatchHeadView ivRater2;
    private Button btnStart;
    private TextView tvSongName1;
    private TextView tvSongName2;
    private ImageView ivShare;
    private boolean isCountDown = false;
    private TextView tvSecond;

    public MatchView(Context context) {
        this(context, null);
    }

    public MatchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.include_pk_matching, this);
        initView();
        initConfig();
    }

    private void initConfig() {

    }

    private void initView() {
        tvSecond = view.findViewById(R.id.tv_second);
        ivPlayer1 = view.findViewById(R.id.ivPlayer1);
        ivPlayer2 = view.findViewById(R.id.ivPlayer2);
        ivRater1 = view.findViewById(R.id.rhv1);
        ivRater2 = view.findViewById(R.id.rhv2);
        ivRater3 = view.findViewById(R.id.rhv3);
        btnStart = view.findViewById(R.id.btn_start);
        ivShare = view.findViewById(R.id.ivShare);

        tvSongName1 = view.findViewById(R.id.tvSongName1);
        tvSongName2 = view.findViewById(R.id.tvSongName2);

        ivPlayer1.setOnClickListener(this);
        ivPlayer2.setOnClickListener(this);
        ivRater1.setOnClickListener(this);
        ivRater2.setOnClickListener(this);
        ivRater3.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_start) {//点击秒开
            mathViewOnClickListener.clickStart();
        } else if (id == R.id.ivShare) {
            mathViewOnClickListener.clickShare();
        } else if (id == R.id.ivPlayer1) {
            mathViewOnClickListener.clickHead(R.drawable.yizhen, "陈绮贞");
        } else if (id == R.id.ivPlayer2) {
            mathViewOnClickListener.clickHead(R.drawable.xueyou, "张学友");
        } else if (id == R.id.rhv1) {
            mathViewOnClickListener.clickHead(R.drawable.jielun, "周杰伦");
        } else if (id == R.id.rhv2) {
            mathViewOnClickListener.clickHead(R.drawable.wanfeng, "汪峰");
        } else if (id == R.id.rhv3) {
            mathViewOnClickListener.clickHead(R.drawable.yangkun, "杨坤");
        }
    }

    /**
     * 点击事件监听器
     */
    public interface MathViewOnClickListener {
        void clickStart();

        void clickShare();

        void clickHead(int headId, String name);
    }

    private MathViewOnClickListener mathViewOnClickListener = null;

    /**
     * 设置点击事件监听器
     *
     * @param mathViewOnClickListener
     */
    public void setMathViewOnClickListener(MathViewOnClickListener mathViewOnClickListener) {
        this.mathViewOnClickListener = mathViewOnClickListener;
    }

    /**
     * 开始倒数
     */
    public void startCountDown() {
        isCountDown = true;
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    int second = 60;

                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        if (second > 0 && isCountDown) {
                            subscriber.onNext(second--);
                        } else {
                            stopCountDown();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        tvSecond.setText(integer.intValue() + "秒");
                    }
                });
    }

    /**
     * 停止倒数
     */
    public void stopCountDown() {
        isCountDown = false;
    }


}
