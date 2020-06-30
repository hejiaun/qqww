package com.example.module_me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.adapter.WorkAdapter;
import com.example.module_me.entity.WorkEntity;
import com.example.module_me.presenter.WorkActivityPresenter;
import com.example.module_me.view_interface.IWorkActivityView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 作品Activity
 */
public class WorkActivity extends BaseActivity<WorkActivityPresenter> implements IWorkActivityView {

    private RecyclerView rcv;
    private WorkAdapter adapter;
    private TextView tvTitle;

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
        tvTitle.setText("作品");

        //--列表配置---//
        rcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkAdapter(R.layout.item_work, new ArrayList<WorkEntity>());
        rcv.setAdapter(adapter);
        adapter.addData(new WorkEntity());
        adapter.addData(new WorkEntity());
        adapter.addData(new WorkEntity());
    }

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public WorkActivityPresenter createPresenter() {
        return new WorkActivityPresenter(this);
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
