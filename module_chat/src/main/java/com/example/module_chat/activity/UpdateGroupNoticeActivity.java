package com.example.module_chat.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.presenter.UpdateGroupNameActivityPresenter;
import com.example.module_chat.view_interface.IUpdateGroupNameActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:更新群名称Activity
 */
public class UpdateGroupNoticeActivity extends BaseActivity<UpdateGroupNameActivityPresenter>
        implements
        IUpdateGroupNameActivityView{
    TextView tvRight;
    private ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvRight = findViewById(R.id.tv_right);
        ivBack = findViewById(R.id.iv_back);
        tvRight.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvRight.setText("完成");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_update_groupnotice;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public UpdateGroupNameActivityPresenter createPresenter() {
        return new UpdateGroupNameActivityPresenter(this);
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tv_right) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
