package com.example.module_pk.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.adapter.SongListDragAdpater;
import com.example.module_pk.widget.MatchView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.SongListEntity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.DensityUtils;
import example.common_base.util.RecyclerViewItemDecorationUtil;
import example.common_base.util.WindowUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  Pk选手模式匹配Activity
 */
public class PkSingerMatchActivity extends PkBaseActivity implements
        AdapterView.OnItemClickListener,
        MatchView.MathViewOnClickListener {
    private TextView tvSearchSongName;
    private Toolbar tb;
    private TextView tvTitle;
    private ArrayList<SongListEntity> songListEntities;
    private RecyclerView rcvSongList;
    private SongListDragAdpater adapter;
    private MatchView matchView;

    /**
     * 从布局中加载控件
     */
    @Override
    public void initView() {
        super.initView();
        rcvSongList = findViewById(R.id.rcv);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setTextColor(getResources().getColor(R.color.white));

        tb = findViewById(R.id.tb);
        matchView = findViewById(R.id.matchView);
        matchView.setMathViewOnClickListener(this);

        findViewById(R.id.rlSearch)
                .setOnClickListener(this);
        findViewById(R.id.iv_back)
                .setOnClickListener(this);
        findViewById(R.id.tb)
                .setBackgroundColor(getResources().getColor(R.color.transparent));

        findViewById(R.id.divideLine).setVisibility(View.GONE);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        matchMode();
        initSongList();
        WindowUtil.getInstence().setTransparentStatusBar(this);
        tb.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    /**
     * 适配对应模式
     */
    private void matchMode() {
        if (gameMode.equals(ConstantValuesUtil.GAME_MODE_ENTERTAINMENT)) {//娱乐模式
            tvTitle.setText("娱乐");
        } else if (gameMode.equals(ConstantValuesUtil.GAME_MODE_RANK)) {//排位模式
            tvTitle.setText("排位");
        }
    }

    private void initSongList() {
        songListEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            songListEntities.add(new SongListEntity("33", "33", "44", R.drawable.example, 3));
        }
        adapter = new SongListDragAdpater(songListEntities);
        rcvSongList.setAdapter(adapter);
        rcvSongList.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, DensityUtils.dp2px(this,12), 0));
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_pk_singer_match_activity;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 返回Activity的回调
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        意图
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            switch (requestCode) {
                case 1://搜索歌曲
                    String songName = data.getStringExtra("songName");
                    tvSearchSongName.setText(songName);
                    break;
            }
        }
    }

    /**
     * 列表Item点击事件监听
     *
     * @param parent
     * @param view
     * @param position 被点击的item的位置
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.rlSearch) {
            startActivity(new Intent(this, SearchSongInPkActivity.class));
        }
    }

    /**
     * 点击返回按钮
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtil.getInstance().finishActivity(this);
    }

    /**
     * 点击秒开
     */
    @Override
    public void clickStart() {
        startActivity(new Intent(PkSingerMatchActivity.this, PkLoadingActivity.class));

    }

    /**
     * 点击分享
     */
    @Override
    public void clickShare() {
        startActivity(new Intent(PkSingerMatchActivity.this, PkShareActivity.class));

    }

    /**
     * 点击头像
     */
    @Override
    public void clickHead(int headId,String name) {
        showPersonalMessageDialog(headId,name);
    }
}
