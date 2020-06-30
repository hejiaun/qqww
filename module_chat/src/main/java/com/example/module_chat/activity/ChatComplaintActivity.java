package com.example.module_chat.activity;

import android.view.View;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.presenter.ChatComplaintActivityPresenter;
import com.example.module_chat.view_interface.IChatComplaintActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:投诉界面
 */
public class ChatComplaintActivity extends BaseActivity<ChatComplaintActivityPresenter> implements
        IChatComplaintActivityView {
    FunctionItemView fivFalseMessage;
    FunctionItemView fivTort;
    TextView tv_right;
    private TextView tvTitle;


    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        fivFalseMessage = findViewById(R.id.fivFalseMessage);
        fivTort = findViewById(R.id.fivTort);
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);

        tv_right.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        fivFalseMessage.setRightWithTextTitleStyle("存在侵权行为");
        fivTort.setRightWithTextTitleStyle("发布仿冒品信息");
        tv_right.setText("完成");
        tvTitle.setText("举报");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_complaint;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ChatComplaintActivityPresenter createPresenter() {
        return new ChatComplaintActivityPresenter(this);
    }


    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_right) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);

        }
    }
}