package com.example.module_account.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ArtificialLoginBySmsActivityPresenter;
import com.example.module_account.view_interface.IArtificialLoginBySmsActivityView;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.eventbusevent.LoginTypeBusEvent;
import example.common_base.util.ActivityUtil;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ArtificialLoginBySmsActivity extends FadeTransitionBaseActivity<ArtificialLoginBySmsActivityPresenter> implements
        IArtificialLoginBySmsActivityView, View.OnClickListener {
    TextView tvTitle;
    private Button btnLogin;
    private ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        ivBack = findViewById(R.id.iv_back);
        btnLogin.setOnClickListener(this);
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
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_artificial_login_sms;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public ArtificialLoginBySmsActivityPresenter createPresenter() {
        return new ArtificialLoginBySmsActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_login) {    //登录
            ActivityUtil.getInstance().finishActivity(this);
            ActivityUtil.getInstance().finishActivity(ForgetPwdActivity.class);
            ActivityUtil.getInstance().finishActivity(ArtificialQuestion1Activity.class);
            ActivityUtil.getInstance().finishActivity(ArtificialQuestion2Activity.class);
            EventBus.getDefault().postSticky(new LoginTypeBusEvent());
        } else if (viewId == R.id.iv_back) {//返回
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
