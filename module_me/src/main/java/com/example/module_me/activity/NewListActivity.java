package com.example.module_me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.billy.android.loading.Gloading;
import com.example.module_me.R;
import com.example.module_me.adapter.NewListAdapter;
import com.example.module_me.entity.NewListEntity;
import com.example.module_me.presenter.NewListActivityPresenter;
import com.example.module_me.view_interface.INewListActivityView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 消息列表
 */
public class NewListActivity extends BaseActivity<NewListActivityPresenter> implements INewListActivityView {

    private RecyclerView rcv;
    private NewListAdapter adapter;
    private TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        rcv = findViewById(R.id.rcv);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new NewListAdapter(R.layout.item_news_list_present, new ArrayList<NewListEntity>());
        rcv.setAdapter(adapter);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        tvTitle.setText("消息列表");
    }

    @Override
    public void initData() {
        super.initData();
        adapter.addData(new NewListEntity(NewListEntity.TYPE_FONT));
        adapter.addData(new NewListEntity(NewListEntity.TYPE_PRESENT));
        adapter.addData(new NewListEntity(NewListEntity.TYEP_EMOJI));
        adapter.addData(new NewListEntity(NewListEntity.TYEP_WORK));
    }

    @Override
    public NewListActivityPresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
