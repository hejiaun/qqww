package com.example.module_account.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.ForgetPwdActivityPresenter;
import com.example.module_account.view_interface.IForgetPwdActivityView;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.eventbusevent.LoginTypeBusEvent;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.ButtonForgetPasswordView;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ForgetPwdActivity extends FadeTransitionBaseActivity<ForgetPwdActivityPresenter> implements
        View.OnClickListener,
        IForgetPwdActivityView, ButtonForgetPasswordView.DoClickInterface {
    TextView tvTitle;
    ButtonForgetPasswordView bfpvSms;
    ButtonForgetPasswordView bfpvEmail;
    ButtonForgetPasswordView bfpvArtificial;

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
    }

    /**
     *加载布局
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        bfpvSms = findViewById(R.id.bfpv_sms);
        bfpvEmail = findViewById(R.id.bfpv_email);
        bfpvArtificial = findViewById(R.id.bfpv_artificial);

        //配置控件
        tvTitle.setText("忘记密码");
        String btnText = "GO";
        bfpvSms.setBtnText(btnText);
        bfpvArtificial.setBtnText(btnText);
        bfpvEmail.setBtnText(btnText);

        bfpvSms.setText("验证码登陆");
        bfpvEmail.setText("邮箱验证");
        bfpvArtificial.setText("人工验证");

        bfpvSms.setBtnOnClickListener(this);
        bfpvEmail.setBtnOnClickListener(this);
        bfpvArtificial.setBtnOnClickListener(this);
        findViewById(R.id.tvFeedBack).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }


    @Override
    public void doClick(String text) {
        switch (text) {
            case "人工验证":
                startActivity(new Intent(ForgetPwdActivity.this, ArtificialQuestion1Activity.class));
                break;
            case "验证码登陆":
                ActivityUtil.getInstance().finishActivity(this);
                EventBus.getDefault().postSticky(new LoginTypeBusEvent());
                break;
            case "邮箱验证":
                startActivity(new Intent(this, RetrievePasswordByEmailActivity.class));
                break;
        }
    }


    /**
     * 加载布局
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_forgetpws;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public ForgetPwdActivityPresenter createPresenter() {
        return new ForgetPwdActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param view 被的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tvFeedBack) {//点击有疑问
            startActivity(new Intent(this, FeedBackActivity.class));
        }
    }
}
