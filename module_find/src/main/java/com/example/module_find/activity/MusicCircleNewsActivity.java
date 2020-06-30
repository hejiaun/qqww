package com.example.module_find.activity;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_find.R;
import com.example.module_find.adapter.MusicCircleNewsListAdapter;
import com.example.module_find.presenter.MusicCircleNewsActivityPresenter;
import com.example.module_find.view_interface.IMusicCircleNewsActivityView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.entity.UserEntity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MusicCircleNewsActivity extends BaseActivity<MusicCircleNewsActivityPresenter>
        implements IMusicCircleNewsActivityView {

    private TextView tvClearNews;
    private RecyclerView rcv;
    private MusicCircleNewsListAdapter adapter;

    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.tvClearNews);
        tvClearNews = findViewById(R.id.tvClearNews);
        rcv = findViewById(R.id.rcv);
       findViewById(R.id.iv_back).setOnClickListener(this);
        tvClearNews.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new MusicCircleNewsListAdapter(R.layout.item_musiccircle_news, new ArrayList<UserEntity>());
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        for (int i = 0; i < 10; i++) {
            adapter.addData(new UserEntity());
        }
        rcv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(MusicCircleNewsActivity.this, 4);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.activity_musiccircle_news;
    }

    @Override
    public MusicCircleNewsActivityPresenter createPresenter() {
        return new MusicCircleNewsActivityPresenter(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvClearNews) {

        } else if (id == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
