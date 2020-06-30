package com.example.module_account.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ArtificialQuestion1ActivityPresenter;
import com.example.module_account.view_interface.IArtificialQuestion1ActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ArtificialQuestion1Activity extends FadeTransitionBaseActivity<ArtificialQuestion1ActivityPresenter> implements
        IArtificialQuestion1ActivityView, View.OnClickListener {
    TextView tvTitle;
    private ImageView ivBack;
    private Button btn_no;
    private Button btnYes;


    /**
     *加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        btnYes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        ivBack = findViewById(R.id.iv_back);
        btn_no.setOnClickListener(this);
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
     * 加载布局
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_artificial_question1;
    }


    /**
     * 创建presenter
     * @return
     */
    @Override
    public ArtificialQuestion1ActivityPresenter createPresenter() {
        return new ArtificialQuestion1ActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_yes) {
            startActivity(new Intent(this, ArtificialQuestion2Activity.class));
        } else if (viewId == R.id.btn_no) {
            startActivity(new Intent(this, ArtificialEmailActivity.class));

        } else if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
