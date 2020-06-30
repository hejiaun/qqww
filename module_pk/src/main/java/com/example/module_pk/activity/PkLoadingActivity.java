package com.example.module_pk.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.presenter.PkLoadingActivityPresenter;
import com.example.module_pk.view_interface.IPkLoadingActivityView;
import com.example.module_pk.widget.MatchHeadView;

import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;
import rx.functions.Action1;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PkLoadingActivity extends PkBaseActivity<PkLoadingActivityPresenter> implements IPkLoadingActivityView {

    private ProgressBar progressBar;
    private MatchHeadView ivRater1;
    private MatchHeadView ivRater2;
    private MatchHeadView ivRater3;
    private MatchHeadView ivPlayer1;
    private MatchHeadView ivPlayer2;
    private boolean isLoading = false;
    private TextView tvTips;
    private TextView tvLoadProgress;


    @Override
    public int initLayout() {
        return R.layout.activity_match_loading;
    }

    /**
     * 记载布局控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTips = findViewById(R.id.tvTips);
        progressBar = findViewById(R.id.pb);
        ivRater1 = findViewById(R.id.rhv1);
        ivRater2 = findViewById(R.id.rhv2);
        ivRater3 = findViewById(R.id.rhv3);
        ivPlayer1 = findViewById(R.id.ivPlayer1);
        ivPlayer2 = findViewById(R.id.ivPlayer2);
        tvLoadProgress = findViewById(R.id.tvLoadProgress);

        ivPlayer1.setOnClickListener(this);
        ivPlayer2.setOnClickListener(this);
        ivRater1.setOnClickListener(this);
        ivRater2.setOnClickListener(this);
        ivRater3.setOnClickListener(this);

        findViewById(R.id.iv_back).setVisibility(View.GONE);
        findViewById(R.id.divideLine).setVisibility(View.GONE);
        findViewById(R.id.tb).setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    @Override
    public void initConfig() {
        super.initConfig();

    }

    @Override
    public void initData() {
        super.initData();
        getPresenter().load(getLoadAction());
    }

    public Action1 getLoadAction() {
        return new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer.intValue() != -1) {
                    progressBar.setProgress(integer.intValue());
                    tvLoadProgress.setText(integer.intValue() + "");
                    if (integer.intValue() == 100) {
                        startActivity();
                    }
                }
            }
        };
    }

    @Override
    public PkLoadingActivityPresenter createPresenter() {
        return new PkLoadingActivityPresenter(this);
    }

    /**
     * 跳转到对应Activity
     */
    private void startActivity() {
        if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_RATER)) {
            startActivity(new Intent(PkLoadingActivity.this, PkRaterActivity.class));
        } else if (gameRole.equals(ConstantValuesUtil.GAME_ROLE_SINGER)) {
            startActivity(new Intent(PkLoadingActivity.this, PkSingerActivity.class));
        }
        isLoading = false;
        ActivityUtil.getInstance().finishActivity(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.ivPlayer1) {
            showPersonalMessageDialog(ivPlayer1.getHeadId(), "陈绮贞");
        } else if (id == R.id.ivPlayer2) {
            showPersonalMessageDialog(ivPlayer2.getHeadId(), "张学友");
        } else if (id == R.id.rhv1) {
            showPersonalMessageDialog(ivRater1.getHeadId(), "周杰伦");
        } else if (id == R.id.rhv2) {
            showPersonalMessageDialog(ivRater2.getHeadId(), "汪峰");
        } else if (id == R.id.rhv3) {
            showPersonalMessageDialog(ivRater3.getHeadId(), "杨坤");
        }
    }

    /**
     * 设置提示
     *
     * @param tips
     */
    public void setTips(String tips) {
        tvTips.setText(tips);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoading = false;
    }
}
