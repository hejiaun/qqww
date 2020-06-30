package com.example.module_account.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ArtificialQuestion2ActivityPresenter;
import com.example.module_account.view_interface.IArtificialQuestion2ActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ArtificialQuestion2Activity extends FadeTransitionBaseActivity<ArtificialQuestion2ActivityPresenter> implements
        View.OnClickListener,
        IArtificialQuestion2ActivityView {
    TextView tvTitle;
    private Button btnYes;
    private Button btnNo;
    private ImageView ivBack;


    /**
     * 控件加载
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        ivBack = findViewById(R.id.iv_back);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    /**
     * 加载配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        //配置控件
        tvTitle.setText("人工申诉");
    }


    /**
     * 布局加载
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_artificial_question2;
    }


    /**
     * 创建presenter
     * @return
     */
    @Override
    public ArtificialQuestion2ActivityPresenter createPresenter() {
        return new ArtificialQuestion2ActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.btn_yes) {
            startActivity(new Intent(this, ArtificialLoginBySmsActivity.class));

        } else if (viewId == R.id.btn_no) {
            startActivity(new Intent(this, ArtificialEmailActivity.class));
        }
    }
}
