package com.example.module_pk.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.widget.MatchView;

import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.PermissionUtil;
import example.common_base.util.WindowUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  Pk评委模式匹配Activity
 */
public class PkRaterMatchActivity extends PkBaseActivity implements MatchView.MathViewOnClickListener {
    TextView tvTitle;
    Toolbar tb;
    private ImageView ivBack;
    private MatchView matchView;
    private TextView tvTips;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        matchView = findViewById(R.id.matchView);
        tvTips = findViewById(R.id.tvTips);

        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);
        tb = findViewById(R.id.include);

        ivBack.setOnClickListener(this);
        matchView.setMathViewOnClickListener(this);

        findViewById(R.id.divideLine).setVisibility(View.GONE);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        WindowUtil.getInstence().setTransparentStatusBar(this);
        tb.setBackgroundColor(getResources().getColor(R.color.transparent));
        tvTitle.setTextColor(getResources().getColor(R.color.white));
        matchMode();
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

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_pk_rater_match_activity;
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
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }

    /**
     * 动态权限申请回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * 点击返回
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
        startActivity(new Intent(this, PkLoadingActivity.class));
    }

    /**
     * 点击分享
     */
    @Override
    public void clickShare() {
        startActivity(new Intent(this, PkShareActivity.class));
    }

    /**
     * 点击头像
     */
    @Override
    public void clickHead(int headId, String name) {
        showPersonalMessageDialog(headId, name);
    }

    /**
     * 设置提示
     *
     * @param tips
     */
    public void setTips(String tips) {
        tvTips.setText(tips);
    }

}
