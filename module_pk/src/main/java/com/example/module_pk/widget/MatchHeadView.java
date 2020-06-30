package com.example.module_pk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module_pk.R;
import com.makeramen.roundedimageview.RoundedImageView;

import example.common_base.util.DensityUtils;

public class MatchHeadView extends LinearLayout {
    RoundedImageView roundHead;
    ImageView ivReadyCircle;
    TextView tvName;
    TextView tvPraiseRate;
    RelativeLayout rl;
    private View view;
    private Context context;
    private int headId;
    private String name;
    private int nameColor;

    public MatchHeadView(Context context) {
        this(context, null);
    }

    public MatchHeadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatchHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.view_match_head, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MatchHeadView);
        headId = typedArray.getResourceId(R.styleable.MatchHeadView_mhvMatchHead, R.drawable.example);
        name = typedArray.getString(R.styleable.MatchHeadView_mhvName);
        nameColor = typedArray.getColor(R.styleable.MatchHeadView_mhvNameColor, getResources().getColor(R.color.white));
        typedArray.recycle();
        initView();
        initConfig();
    }

    private void initConfig() {
        tvName.setText(name);
        tvName.setTextColor(nameColor);
        roundHead.setImageResource(headId);
    }

    private void initView() {
        rl = view.findViewById(R.id.rl);
        tvPraiseRate = view.findViewById(R.id.tv_praiseRate);
        tvName = view.findViewById(R.id.tv_name);
        ivReadyCircle = view.findViewById(R.id.ivReadyCircle);
        roundHead = view.findViewById(R.id.roundHead);
    }

    public int getHeadId() {
        return headId;
    }

    /**
     * 设置好评率
     */
    public void setPraiseRate(String text) {
        tvPraiseRate.setVisibility(View.VISIBLE);
        tvPraiseRate.setText(text);
    }

    /**
     * 设置头像
     *
     * @param headImage
     */
    public void setHead(int headImage) {
        roundHead.setImageResource(headImage);
    }

    /**
     * 设置头像
     *
     * @param name
     */
    public void setName(String name) {
        tvName.setText(name);
    }

    /**
     * 设置已经准备
     */
    public void setReady() {
        ivReadyCircle.setVisibility(VISIBLE);
    }

    /**
     * 设置没有准备
     */
    public void setUnReady() {
        ivReadyCircle.setVisibility(GONE);
    }

    public void setImageSize(int size) {
        int i = DensityUtils.dp2px(context, size);
        ViewGroup.LayoutParams rlParams = rl.getLayoutParams();
        rlParams.width = i;
        rlParams.height = i;
        rl.setLayoutParams(rlParams);
        ViewGroup.LayoutParams headParams = roundHead.getLayoutParams();
        headParams.width = i;
        headParams.height = i;
        roundHead.setLayoutParams(headParams);
    }

    /**
     * 设置文本颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        tvName.setTextColor(color);
        tvPraiseRate.setTextColor(color);
    }

}
