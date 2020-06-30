package com.example.module_chat.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.module_chat.R;
import com.example.module_chat.adapter.AddFriendByCondtionSearchReusltAdapter;
import com.example.module_chat.presenter.AddFriendByConditionSearchResultPresenter;
import com.example.module_chat.view_interface.IAddFriendByConditionSearchResultView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.entity.UserEntity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 好友查询结果
 */
public class AddFriendByConditionSearchResult extends BaseActivity<AddFriendByConditionSearchResultPresenter> implements IAddFriendByConditionSearchResultView {
    private RecyclerView rcv;
    private AddFriendByCondtionSearchReusltAdapter adapter;
    private TextView tvTitle;

    /**
     * 加载布局
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        rcv = findViewById(R.id.rcv);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("查找结果");

        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddFriendByCondtionSearchReusltAdapter(R.layout.item_add_friend_search_result, new ArrayList<UserEntity>());
        rcv.setAdapter(adapter);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        adapter.addData(new UserEntity());
        adapter.addData(new UserEntity());
        adapter.addData(new UserEntity());
        adapter.addData(new UserEntity());
    }

    /**
     * 点击事件的监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }

    /**
     * 创建presenter
     * @return
     */
    @Override
    public AddFriendByConditionSearchResultPresenter createPresenter() {
        return null;
    }
}
