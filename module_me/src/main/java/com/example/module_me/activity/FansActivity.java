package com.example.module_me.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.adapter.FansAdapter;
import com.example.module_me.presenter.PopularityActivityPresenter;
import com.example.module_me.view_interface.IPopularityActivityView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.entity.UserEntity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 粉丝列表Activity
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class FansActivity extends BaseActivity<PopularityActivityPresenter> implements IPopularityActivityView {

    private TextView tvTitle;
    private RecyclerView rcv;
    private FansAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FansAdapter(R.layout.item_person_access, new ArrayList<UserEntity>());
        rcv.setAdapter(adapter);
        tvTitle.setText("粉丝");
    }

    @Override
    public void initData() {
        super.initData();
        adapter.addData(new UserEntity());
        adapter.addData(new UserEntity());
        adapter.addData(new UserEntity());
    }

    @Override
    public void initView() {
        super.initView();
        rcv = findViewById(R.id.rcv);
        tvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public PopularityActivityPresenter createPresenter() {
        return new PopularityActivityPresenter(this);
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
