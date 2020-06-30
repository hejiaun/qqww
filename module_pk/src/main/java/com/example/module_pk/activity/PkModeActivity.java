package com.example.module_pk.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_pk.R;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BasePresenter;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  Pk模式Activity
 */
@Route(path = ARouterUtil.PkMode_Activity)
public class PkModeActivity extends PkBaseActivity {
    TextView tvTitle;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setTextColor(getResources().getColor(R.color.white));

        findViewById(R.id.rlRank).setOnClickListener(this);
        findViewById(R.id.rlEntertain).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tb).setBackgroundColor(getResources().getColor(R.color.transparent));

        findViewById(R.id.divideLine).setVisibility(View.GONE);

    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setTransparent(this);
        gameRole = getIntent().getStringExtra("game_role");
        if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_SINGER)) {
            tvTitle.setText("PK模式");
        } else {
            tvTitle.setText("评委模式");
        }
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_mode_game;
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
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.rlRank) {//排位模式
            gameMode = ConstantValuesUtil.GAME_MODE_RANK;
            startGame();
        } else if (viewId == R.id.rlEntertain) {//娱乐模式
            gameMode = ConstantValuesUtil.GAME_MODE_ENTERTAINMENT;
            startGame();
        } else if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        }
    }

    private void startGame() {
        Intent intent = new Intent();
        if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_SINGER)) {
            intent.setClass(PkModeActivity.this, PkSingerMatchActivity.class);
        } else if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_RATER)) {
            intent.setClass(PkModeActivity.this, PkRaterMatchActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        ActivityUtil.getInstance().finishActivity(this);
    }
}
