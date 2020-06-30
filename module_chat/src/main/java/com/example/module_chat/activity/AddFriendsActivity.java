package com.example.module_chat.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.presenter.AddFriendsActivityPresenter;
import com.example.module_chat.view_interface.IAddFriendsActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;
import example.common_base.widget.ZxingUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  添加好友Activity
 */
public class AddFriendsActivity extends BaseActivity<AddFriendsActivityPresenter> implements
        View.OnClickListener,
        IAddFriendsActivityView {
    TextView tvTitle;
    FunctionItemView fivFindByCondition;
    FunctionItemView fivScan;
    FunctionItemView fivFindByDirectory;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        fivFindByCondition = findViewById(R.id.fivFindByCondition);
        fivScan = findViewById(R.id.fivScan);
        fivFindByDirectory = findViewById(R.id.fivFindByDirectory);

        fivFindByCondition.setOnClickListener(this);
        fivScan.setOnClickListener(this);
        fivFindByDirectory.setOnClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("添加朋友");
    }

    /**
     * @return 本Activity布局文件
     */
    @Override
    public int initLayout() {
        return R.layout.activity_add_friend;
    }

    /**
     * 创建Presenter
     *
     * @return
     */
    @Override
    public AddFriendsActivityPresenter createPresenter() {
        return new AddFriendsActivityPresenter(this);
    }

    /**
     * 点击事件
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.fivFindByCondition) {//点击按条件查找朋友
            startActivity(new Intent(this, AddFriendByConditionActivity.class));
        } else if (viewId == R.id.fivScan) {//点击扫一扫
            ZxingUtil.getInstence().startScanActivity(this);
        } else if (viewId == R.id.fivFindByDirectory) {//点击手机联系人
            startActivity(new Intent(this, AddFriendByDirectory.class));
            // TODO: 2018/10/8 从手机联系人添加朋友
        } else if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
