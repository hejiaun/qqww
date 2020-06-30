package com.example.module_pk.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.adapter.PkShareAdapter;
import com.example.module_pk.entity.PkShareEntity;
import com.example.module_pk.presenter.PkShareActivityPresenter;
import com.example.module_pk.view_interface.IPkShareActivityView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 分享
 */
public class PkShareActivity extends BaseActivity<PkShareActivityPresenter> implements
        BaseQuickAdapter.OnItemClickListener,
        IPkShareActivityView {
    private SuperTextView stv;
    private EditText etSearch;
    private RecyclerView rcv;
    private PkShareAdapter adapter;
    private TextView tvSelectAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(com.example.common_base.R.anim.activity_fade_in, com.example.common_base.R.anim.activity_fade_out);
    }

    @Override
    protected void onStop() {
        super.onStop();
        overridePendingTransition(com.example.common_base.R.anim.activity_fade_in, com.example.common_base.R.anim.activity_fade_out);
    }

    @Override
    public void initView() {
        super.initView();
        stv = findViewById(R.id.stv);
        etSearch = findViewById(R.id.etSearch);
        rcv = findViewById(R.id.rcv);
        tvSelectAll = findViewById(R.id.tvSelectAll);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        //----------------------设置状态栏为光亮模式-----------------------//
        StatusBarUtil.setLightMode(this);

        //----------------------设置SuperTextView点击事件-----------------------//
        stv.setLeftTvClickListener(new SuperTextView.OnLeftTvClickListener() {
            @Override
            public void onClickListener() {//点击返回
                ActivityUtil.getInstance().finishActivity(PkShareActivity.this);
            }
        }).setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {//点击确定

            }
        });

        //----------------------设置列表是适配器-----------------------//
        adapter = new PkShareAdapter(new ArrayList<PkShareEntity>());
        rcv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        //----------------------关闭列表项更新动画-----------------------//
        closeRecyclerViewReflashAnimate();

        //----------------------点击事件监听设置-----------------------//
        tvSelectAll.setOnClickListener(this);

        //----------------------搜索框的输入监听设置-----------------------//
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.textScreen(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        getPresenter().initFirtEntryData();
    }

    @Override
    public int initLayout() {
        return R.layout.share_activity;
    }

    @Override
    public PkShareActivityPresenter createPresenter() {
        return new PkShareActivityPresenter(this);
    }

    /**
     * 列表项点击事件监听方法
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PkShareEntity pkShareEntity = (PkShareEntity) adapter.getData().get(position);
        pkShareEntity.setSelect(!pkShareEntity.isSelect());
        adapter.notifyItemChanged(position);
    }

    /**
     * 点击事件监听方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tvSelectAll) {//点击全选
            clickSelectAllButton();
        }
    }

    /**
     * 点击全选\取消全选按钮
     */
    private void clickSelectAllButton() {
        String selectText = (String) tvSelectAll.getText();
        if (selectText.equals("取消全选")) {
            tvSelectAll.setText("全选");
            for (PkShareEntity entity : adapter.getData()) {
                entity.setSelect(false);
            }
        } else if (selectText.equals("全选")) {
            tvSelectAll.setText("取消全选");
            for (PkShareEntity entity : adapter.getData()) {
                entity.setSelect(true);
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 关闭RecyclerView刷新动画
     */
    private void closeRecyclerViewReflashAnimate() {
        ((DefaultItemAnimator) rcv.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    /**
     * View层向Presenter层提供PkShareAdapter
     *
     * @return PkShareAdapter
     */
    @Override
    public PkShareAdapter getAdapter() {
        return adapter;
    }
}
