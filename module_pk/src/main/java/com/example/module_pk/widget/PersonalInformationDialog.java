package com.example.module_pk.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_pk.R;

import example.common_base.activity.ReportActivity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PersonalInformationDialog extends Dialog implements View.OnClickListener {
    private final View view;
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvFansNum;
    private TextView tvChat;
    private TextView tvRankLevel;
    private TextView tvFollow;
    private TextView tvReport;

    public PersonalInformationDialog(@Nullable Context context) {
        super(context);
        view = View.inflate(getContext(), R.layout.dialog_brief_information, null);
        setContentView(view);
        initView();
        initConfig();
    }

    private void initConfig() {
        //设置窗口背景
        getWindow().setBackgroundDrawable(getContext().getResources().getDrawable(R.color.transparent));
    }

    private void initView() {
        //----------------------加载布局控件-----------------------//
        tvRankLevel = view.findViewById(R.id.tvRankLevel);
        tvFansNum = view.findViewById(R.id.tvFansNum);
        tvReport = view.findViewById(R.id.tvReport);
        tvFollow = view.findViewById(R.id.tvFollow);
        ivHead = view.findViewById(R.id.ivHead);
        tvName = view.findViewById(R.id.tvName);
        tvChat = view.findViewById(R.id.tvChat);

        //----------------------设置事件-----------------------//
        tvReport.setOnClickListener(this);
        tvFollow.setOnClickListener(this);
        tvChat.setOnClickListener(this);
    }

    public void setHead() {
        // TODO: 2019/3/16 设置本项目的图片加载工具
        // TODO: 2019/3/16 设置头像
    }

    public void show(int headResoure, String name) {
        ivHead.setImageResource(headResoure);
        tvName.setText(name);
        show();
    }

    /**
     * 点击事件监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvReport) {//点击举报
            startReportActivity();
        } else if (id == R.id.tvChat) {//点击聊天

        } else if (id == R.id.tvFollow) {//点击关注
            clickFollow();
        }
    }

    /**
     * 点击关注
     */
    private void clickFollow() {
        if (tvFollow.getText().equals("+关注")) {
            tvFollow.setText("已关注");
            tvFollow.setBackgroundResource(R.drawable.shape_border_gray_round_4dp);
        } else if (tvFollow.getText().equals("已关注")) {
            tvFollow.setText("+关注");
            tvFollow.setBackgroundResource(R.drawable.shape_border_red_round_4dp);
        }
    }

    /**
     * 进入举报界面
     */
    private void startReportActivity() {
        Intent intent = new Intent(getContext(), ReportActivity.class);
        getContext().startActivity(intent);
    }
}
