package com.example.module_account.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_account.R;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;

/**
 * 通过邮件找回密码
 *
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class RetrievePasswordByEmailActivity extends BaseActivity {
    private TextView tvTitle;
    private ImageView ivBack;
    private AlertDialog dialog;

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_retrieve_by_email;
    }

    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("验证邮箱");
        findViewById(R.id.btn_next).setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 点击事件的监听
     *
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.btn_next) {
            showCommitDialog();
        }
    }

    /**
     * 显示提交对话框
     */
    private void showCommitDialog() {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("消息");
            builder.setMessage("请到您绑定了该账号的邮箱中进行找回密码的陆续步骤");
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
