package com.example.module_pk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.module_pk.R;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 评委投票按钮
 */
public class RaterVoteButton extends LinearLayout implements View.OnClickListener {

    private View view;
    private RoundedImageView ivHead;
    private TextView tvVote;
    private VoteButtonClickListener voteButtonClickListener;
    private int headResource;
    private String name;
    private TextView tvName;
    private boolean isShowInfo;

    public RaterVoteButton(Context context) {
        this(context, null);
    }

    public RaterVoteButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RaterVoteButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.view_rater_vote_button, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RaterVoteButton);
        headResource = typedArray.getResourceId(R.styleable.RaterVoteButton_rbHeadResource, R.drawable.xueyou);
        isShowInfo = typedArray.getBoolean(R.styleable.RaterVoteButton_rbShowInfo, true);
        name = typedArray.getString(R.styleable.RaterVoteButton_rbName);
        typedArray.recycle();
        initView();
        initConfig();
    }

    private void initConfig() {
        if (!isShowInfo) {
            hideInfo();
            return;
        }

        tvName.setText(name);
        ivHead.setImageDrawable(getResources().getDrawable(headResource));
        ivHead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voteButtonClickListener != null) {
                    voteButtonClickListener.clickHead(RaterVoteButton.this);
                }
            }
        });
        tvVote.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voteButtonClickListener != null) {
                    voteButtonClickListener.clickVoteButton(RaterVoteButton.this);
                }
            }
        });
    }

    private void initView() {
        tvName = view.findViewById(R.id.tvName);
        ivHead = view.findViewById(R.id.ivHead);
        tvVote = view.findViewById(R.id.tvVote);

        ivHead.setOnClickListener(this);
        tvVote.setOnClickListener(this);
    }

    public void showInfo() {
        tvName.setVisibility(VISIBLE);
        ivHead.setVisibility(VISIBLE);
    }

    public void hideInfo() {
        tvName.setVisibility(GONE);
        ivHead.setVisibility(GONE);
    }

    public void setMyClickListener(VoteButtonClickListener voteButtonClickListener) {
        this.voteButtonClickListener = voteButtonClickListener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ivHead) {
            if (voteButtonClickListener != null) {
                voteButtonClickListener.clickHead(this);
            }
        } else if (id == R.id.tvVote) {
            if (voteButtonClickListener != null) {
                voteButtonClickListener.clickVoteButton(this);
            }
        }
    }

    public interface VoteButtonClickListener {
        /**
         * 点击投票按钮
         *
         * @param view
         */
        void clickVoteButton(View view);

        /**
         * 点击头像
         *
         * @param view
         */
        void clickHead(View view);
    }
}
