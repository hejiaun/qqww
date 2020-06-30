package com.example.administrator.shengdoushi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Timer;
import java.util.TimerTask;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ARouterUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FirstEntryActivity extends BaseActivity {
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.view_state);
        overridePendingTransition(com.example.common_base.R.anim.activity_fade_in, com.example.common_base.R.anim.activity_fade_out);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                ARouter
                        .getInstance()
                        .build(ARouterUtil.Login_Activity)
                        .withTransition(R.anim.fade_in, R.anim.fade_out)
                        .navigation();
            }
        }, 1000);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_firstentry;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

}
