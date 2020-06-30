package com.example.module_account.activity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ArtificialEmailActivityPresenter;
import com.example.module_account.view_interface.IArtificialEmailActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.util.ActivityUtil;

public class ArtificialEmailActivity extends FadeTransitionBaseActivity<ArtificialEmailActivityPresenter> implements
        View.OnClickListener,
        IArtificialEmailActivityView {
    TextView tvTitle;
    ImageView ivBack;
    Button btnNext;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);
        btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    /**
     * 加载配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        //配置控件
        tvTitle.setText("人工申诉");
    }


    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_artificial_email;
    }

    /**
     * 创建presenter
     * @return
     */
    @Override
    public ArtificialEmailActivityPresenter createPresenter() {
        return new ArtificialEmailActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     * @param view
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.btn_next) {
            //验证邮箱格式
            //上传邮箱
            //跳转
            startActivity(new Intent(this, ArtificialFormActivity.class));
        }
    }
}
