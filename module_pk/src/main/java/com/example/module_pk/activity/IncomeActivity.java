package com.example.module_pk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.example.module_pk.R;
import com.example.module_pk.presenter.IncomeActivityPresenter;
import com.example.module_pk.view_interface.IIncomeActivityView;

import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: Pk结算Activity
 */
public class IncomeActivity extends PkBaseActivity<IncomeActivityPresenter> implements IIncomeActivityView {

    private SuperButton stvSuer;
    private TextView tvFollow;
    private TextView tvSaveSong;
    private TextView tvSaveVideo;
    private TextView tvAgain;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存fragment
        getPresenter().saveAllFragment(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            getPresenter().restoreFragment(savedInstanceState);
        }
    }

    @Override
    public void initView() {
        super.initView();
        stvSuer = findViewById(R.id.stvSuer);
        tvFollow = findViewById(R.id.tvFollow);
        tvSaveSong = findViewById(R.id.tvSaveSong);
        tvSaveVideo = findViewById(R.id.tvSaveVideo);
        tvAgain = findViewById(R.id.tvAgain);

        stvSuer.setOnClickListener(this);
        tvSaveSong.setOnClickListener(this);
        tvSaveVideo.setOnClickListener(this);
        tvAgain.setOnClickListener(this);
        tvFollow.setOnClickListener(this);

        findViewById(R.id.tvAgain).setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            showFirstPage();
        }
    }

    /**
     * 自动匹配显示第一页面
     */
    private void showFirstPage() {
        if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_SINGER)) {
            getPresenter().showFragment(0);
        } else if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_RATER)) {
            getPresenter().showFragment(1);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.activity_income;
    }

    @Override
    public IncomeActivityPresenter createPresenter() {
        return new IncomeActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.stvSuer) {
            exitGame();
        } else if (id == R.id.tvFollow) {//点击关注
            Toast.makeText(this, "关注成功!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tvSaveSong) {//保存两首歌曲
            Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tvSaveVideo) {//保存比赛录像
            Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tvAgain) {//再来一把
            exitGame();
        }
    }


    private void exitGame() {
        ActivityUtil
                .getInstance()
                .finishActivity(this)
                .finishActivity(PkSingerMatchActivity.class)
                .finishActivity(PkRaterMatchActivity.class)
                .finishActivity(PkLoadingActivity.class)
                .finishActivity(PkSingerActivity.class)
                .finishActivity(PkRaterActivity.class);
    }

    /**
     * View层向Presenter提供IncomeActivity
     *
     * @return IncomeActivity
     */
    @Override
    public IncomeActivity getActivity() {
        return this;
    }

    @Override
    public void onBackPressed() {
    }
}
