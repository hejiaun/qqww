package com.example.module_account.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ArtificialResultActivityPresenter;
import com.example.module_account.view_interface.IArtificialResultActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ArtificialResultActivity extends FadeTransitionBaseActivity<ArtificialResultActivityPresenter> implements
        View.OnClickListener,
        IArtificialResultActivityView {
    TextView tvTitle;
    private Button btnNext;
    private ImageView ivBack;

    /**
     *控件加载
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);
        btnNext = findViewById(R.id.btn_next);
        ivBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    /**
     * 加载配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        //配置控件
        tvTitle.setText("申诉结果");
    }


    /**
     * 加载布局
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_artificial_result;
    }


    /**
     * 创建presenter
     * @return
     */
    @Override
    public ArtificialResultActivityPresenter createPresenter() {
        return new ArtificialResultActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.btn_next) {
            ActivityUtil.getInstance().finishAllActivityExcept(LoginActivity.class);
        }

    }
}
