package com.example.module_pk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_pk.R;
import com.makeramen.roundedimageview.RoundedImageView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class RatingHeadView extends ConstraintLayout implements View.OnClickListener {
    ImageView ivLike;
    RoundedImageView ivHead;
    RoundedImageView ivMask;
    TextView tvSecond;
    ImageView ivSound;
    TextView tvName;
    ImageView ivDislike;
    TextView tvPraiseRate;
    private View view;
    private int textColor;
    private boolean isCountdown = false;
    private boolean isSpeaking = false;
    MyViewOnClickListener myViewOnClickListener = null;
    private int headResource;
    private String name;
    private TextView tvDoubleDisLike;
    private TextView tvDoubleLike;

    public void initView() {
        tvPraiseRate = view.findViewById(R.id.tv_praiseRate);
        ivDislike = view.findViewById(R.id.iv_dislike);
        tvName = view.findViewById(R.id.tv_name);
        ivSound = view.findViewById(R.id.ivSound);
        tvSecond = view.findViewById(R.id.tv_second);
        ivMask = view.findViewById(R.id.iv_mask);
        ivHead = view.findViewById(R.id.ivHead);
        ivLike = view.findViewById(R.id.iv_like);
        tvName.setTextColor(textColor);
        tvDoubleLike = view.findViewById(R.id.tvDoubleLike);
        tvDoubleDisLike = view.findViewById(R.id.tvDoubleDisLike);

        ivLike.setOnClickListener(this);
        ivDislike.setOnClickListener(this);
        ivHead.setOnClickListener(this);
    }

    public RatingHeadView(Context context) {
        this(context, null);
    }

    public RatingHeadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.view_rating_head, this);
        //----------------------获取自定义属性-----------------------//
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingHeadView);
        textColor = typedArray.getColor(R.styleable.RatingHeadView_rhvAllTextColor, Color.WHITE);
        headResource = typedArray.getResourceId(R.styleable.RatingHeadView_rhvHeadResource, R.drawable.jielun);
        name = typedArray.getString(R.styleable.RatingHeadView_rhvName);
        typedArray.recycle();

        initView();
        initConfig();
    }

    private void initConfig() {
        ivHead.setImageDrawable(getResources().getDrawable(headResource));
        tvName.setText(name);
    }

    public void release() {
        if (isCountdown) {
            isCountdown = false;
        }
        if (isSpeaking) {
            stopSpeaking();
        }
    }

    /**
     * 不可以投票
     */
    public void setDisVotable() {
        ivDislike.setClickable(false);
        ivLike.setClickable(false);
    }

    /**
     * 可以投票
     */
    public void setVotable() {
        ivLike.setClickable(true);
        ivDislike.setClickable(true);
    }

    /**
     * 显示倒数文字
     */
    public void showCountdown() {
        tvSecond.setVisibility(View.VISIBLE);
        ivMask.setVisibility(View.VISIBLE);
    }

    /**
     * 显示投票图标
     */
    public void showVoteIcon() {
        ivLike.setImageResource(R.drawable.play_btn_fabulous_n);
        ivDislike.setImageResource(R.drawable.play_btn_diss_n);
        ivDislike.setVisibility(View.VISIBLE);
        ivLike.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏投票图标
     */
    public void hideVoteIcon() {
        ivDislike.setVisibility(View.INVISIBLE);
        ivLike.setVisibility(View.INVISIBLE);
        ivLike.setClickable(false);
        ivDislike.setClickable(false);
    }

    /**
     * 隐藏倒数秒数和蒙版
     */
    public void hideCountDownSecond() {
        tvSecond.setVisibility(View.GONE);
        ivMask.setVisibility(View.GONE);
        if (isCountdown) {
            isCountdown = false;
        }
    }

    /**
     * 设置为正在说话状态,播放帧动画
     */
    public void startSpeaking() {
        ivSound.setVisibility(View.VISIBLE);
        ivSound.setImageResource(R.drawable.anim_list_sound);
        AnimationDrawable drawable = (AnimationDrawable) ivSound.getDrawable();
        drawable.start();
        isSpeaking = true;
    }

    /**
     * 停止说话
     */
    public void stopSpeaking() {
        if (isSpeaking) {
            AnimationDrawable drawable = (AnimationDrawable) ivSound.getDrawable();
            drawable.stop();
            ivSound.setVisibility(View.INVISIBLE);
            isSpeaking = false;
        }
    }

    /**
     * 开始倒数时间
     * <p>
     * //     * @param second 倒数秒数
     */
    public void startCountdown() {
        isCountdown = true;
        Observable.create(new Observable.OnSubscribe<Integer>() {
            int second = 60;

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                while (second >= 0 && isCountdown) {
                    try {
                        subscriber.onNext(second--);
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
                            tvSecond.setText(integer.intValue() + "秒");
                        } else {
                            hideCountDownSecond();
                        }
                    }
                });
    }

    /**
     * 设置名称字体颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        tvName.setTextColor(color);
    }

    public void setTextSize(float size) {
        tvName.setTextSize(size);
    }

    /**
     * 设置点击事件
     */
    public void setMyViewOnClickListener(MyViewOnClickListener myViewOnClickListener) {
        this.myViewOnClickListener = myViewOnClickListener;
    }

    /**
     * s设置头像圆角
     *
     * @param corner
     */
    public void setCorner(float corner) {
        ivMask.setCornerRadius(corner);
        ivHead.setCornerRadius(corner);
    }

    /**
     * 设置头像
     */
    public void setHead(int resource) {
        ivHead.setImageResource(resource);
    }

    /**
     * 设置为禁言状态
     */
    public void banVoice() {
        stopSpeaking();
        ivSound.setImageResource(R.drawable.play_btn_yuyin_s);
    }

    public void showDoubleLike() {
        tvDoubleLike.setVisibility(View.VISIBLE);
    }

    public void showDoubleDislike() {
        tvDoubleDisLike.setVisibility(View.VISIBLE);
    }

    public void hideDoubleLike() {
        tvDoubleLike.setVisibility(View.GONE);
    }

    public void hideDoubleDislike() {
        tvDoubleDisLike.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_like) {//点赞
            ivDislike.setVisibility(View.INVISIBLE);
            ivDislike.setClickable(false);
            ivLike.setImageResource(R.drawable.play_btn_fabulous_s);
        } else if (id == R.id.iv_dislike) {//点踩
            ivLike.setVisibility(View.INVISIBLE);
            ivLike.setClickable(false);
            ivDislike.setImageResource(R.drawable.play_btn_diss_s);
        } else if (id == R.id.ivHead) {
            if (myViewOnClickListener != null) {
                myViewOnClickListener.clickHead(this);
            }
        }

    }

    public interface MyViewOnClickListener {
        /**
         * 点赞
         */
        void clickLike(View view);

        /**
         * 点踩
         */
        void clickDisLike(View view);

        void clickHead(View view);
    }

}
