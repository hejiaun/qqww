package com.example.module_me.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.PopularityActivityPresenter;
import com.example.module_me.view_interface.IPopularityActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 人气列表Activity
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class PopularityActivity extends BaseActivity<PopularityActivityPresenter> implements IPopularityActivityView {

    private TextView tvTitle;
    private RecyclerView rcv;

    @Override
    public int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void initConfig() {
        super.initConfig();
        rcv.setLayoutManager(new LinearLayoutManager(this));
        tvTitle.setText("人气");
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
