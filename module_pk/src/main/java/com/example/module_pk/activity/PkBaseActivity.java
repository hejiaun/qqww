package com.example.module_pk.activity;

import android.graphics.Color;
import android.os.Build;

import com.example.module_pk.widget.GameCountDownDialog;
import com.example.module_pk.widget.PersonalInformationDialog;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public abstract class PkBaseActivity<T extends BasePresenter> extends BaseActivity<T> {

    public static String gameRole;
    public static String gameMode;

    private PersonalInformationDialog informationDialog;

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setDarkMode(this);
        //----------------------设置底部导航栏颜色为黑色-----------------------//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.BLACK);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //activity切换动画
        overridePendingTransition(com.example.common_base.R.anim.activity_fade_in, com.example.common_base.R.anim.activity_fade_out);
    }

    /**
     * 显示个性简略信息对话框
     */
    public void showPersonalMessageDialog(int headResource, String name) {
        if (informationDialog == null) {
            informationDialog = new PersonalInformationDialog(this);
        }
        informationDialog.show(headResource, name);
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * 倒数对话框
     */
    public void showGameCountDownDialog() {
        GameCountDownDialog gameCountDownDialog = new GameCountDownDialog(this);
        gameCountDownDialog.show();
        gameCountDownDialog.startCountDown();
    }
}

