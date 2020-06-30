package com.example.module_account.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.module_account.R;
import com.example.module_account.presenter.FeedBackActivityPresenter;
import com.example.module_account.view_interface.IFeedBackActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.FadeTransitionBaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class FeedBackActivity extends FadeTransitionBaseActivity<FeedBackActivityPresenter> implements IFeedBackActivityView {

    private EditText etMessage;
    private EditText etEmail;
    private EditText etPhone;
    private TextView tvCount;
    private AlertDialog dialog;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_account_feedback;
    }

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        etMessage = findViewById(R.id.etMessage);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        tvCount = findViewById(R.id.tvCount);
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText("反馈");
        findViewById(R.id.btnCommit).setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() <= 200) {
                    tvCount.setText(s.length() + "/200");
                }
            }
        });
    }

    @Override
    public FeedBackActivityPresenter createPresenter() {
        return new FeedBackActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (R.id.btnCommit == id) {//点击提交
            showCommitDialog();
        }
    }

    /**
     * 显示提交对话框
     */
    public void showCommitDialog() {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("消息");
            builder.setMessage("您已提交成功，感谢您的反馈！");
            builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    ActivityUtil.getInstance().finishAllActivityExcept(LoginActivity.class);
                }
            });
            dialog = builder.create();
        }
        dialog.show();
    }
}
