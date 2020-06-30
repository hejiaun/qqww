package com.example.module_pk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.module_pk.R;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PlayerIncomeView extends ConstraintLayout {

    private View view;
    private RoundedImageView riv;
    private RoundedImageView ivHead;
    private TextView tvName;
    private RatingBar ratingBar;
    private TextView tvMusicCoin;
    private TextView tvFlow;
    private ProgressBar pb;
    private ImageView ivRankLevel;
    private TextView tvPopularity;
    private TextView tvRankLevel;
    private int headResource;
    private String name;
    private RaterVoteButton rvb;

    public PlayerIncomeView(Context context) {
        this(context, null);
    }

    public PlayerIncomeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerIncomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(getContext(), R.layout.view_pk_player_income, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayerIncomeView);
        headResource = typedArray.getResourceId(R.styleable.PlayerIncomeView_pivHeadResource, R.drawable.jielun);
        name = typedArray.getString(R.styleable.PlayerIncomeView_pivName);
        typedArray.recycle();
        initView();
        initConfig();
    }

    private void initConfig() {
        ivHead.setImageDrawable(getResources().getDrawable(headResource));
        tvName.setText(name);

    }

    private void initView() {
        rvb = view.findViewById(R.id.rvb);
        riv = view.findViewById(R.id.ivHead);
        ivHead = view.findViewById(R.id.ivHead);
        tvName = view.findViewById(R.id.tvName);
        ratingBar = view.findViewById(R.id.ratingBar);
        tvMusicCoin = view.findViewById(R.id.tvMusicCoin);
        tvFlow = view.findViewById(R.id.tvFlower);
        pb = view.findViewById(R.id.pb);
        ivRankLevel = view.findViewById(R.id.ivRankLevel);
        view.findViewById(R.id.tvPopularity);
        tvRankLevel = view.findViewById(R.id.tvRankLevel);

        rvb.setMyClickListener(new RaterVoteButton.VoteButtonClickListener() {
            /**
             * 点击投票按钮
             * @param view
             */
            @Override
            public void clickVoteButton(View view) {
                hideVoteBotton();
            }

            @Override
            public void clickHead(View view) {

            }
        });
    }

    /**
     * 设置点击头像的监听事件
     *
     * @param onClickListener
     */
    public void setHeadViewClickListener(View.OnClickListener onClickListener) {
        riv.setOnClickListener(onClickListener);
    }

    /**
     * 显示投票按钮
     */
    public void showVoteBotton() {
        rvb.setVisibility(VISIBLE);
        setRankInfoVisibility(View.GONE);
    }

    /**
     * 隐藏投票按钮
     */
    public void hideVoteBotton() {
        rvb.setVisibility(GONE);
        setRankInfoVisibility(View.VISIBLE);
    }

    /**
     * 设置排位信息(段位名称、图标、星级)是否可见
     *
     * @param visibility
     */
    public void setRankInfoVisibility(int visibility) {
        ratingBar.setVisibility(visibility);
        ivRankLevel.setVisibility(visibility);
        tvRankLevel.setVisibility(visibility);
    }


    public void setHead(int drawableId) {
        ivHead.setImageDrawable(getResources().getDrawable(drawableId));
    }

    public void setFlower() {

    }

    public void setMusicCoin() {

    }

    public void setProgress() {

    }

    public void setRating() {

    }

    public void setRankIcon() {

    }

    public void setRankLevel() {

    }

    public void sepPopularity() {

    }
}
