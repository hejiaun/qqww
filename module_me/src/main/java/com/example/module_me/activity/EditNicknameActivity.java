package com.example.module_me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_me.R;
import com.example.module_me.presenter.EditNicknameActivityPresenter;
import com.example.module_me.view_interface.IEditNicknameActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑手机号码的Activity
 */
public class EditNicknameActivity extends BaseActivity<EditNicknameActivityPresenter> implements
        IEditNicknameActivityView {
    TextView tv_right;
    TextView tvTitle;
    EditText editNickname;
    private String nickname;
    public final static int RESULTCODE = 0xFFF99;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tv_right = findViewById(R.id.tv_right);
        editNickname = findViewById(R.id.editNickname);
        tvTitle = findViewById(R.id.tv_title);
        ivBack.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("昵称");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_nickname;
    }

    @Override
    public EditNicknameActivityPresenter createPresenter() {
        return new EditNicknameActivityPresenter(this);
    }


    /**
     * 结束并回传参数
     */
    private void finishWithResult() {
        if (nickname != null && !nickname.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("result", nickname);
            setResult(RESULTCODE, intent);
        }
        ActivityUtil.getInstance().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        finishWithResult();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {//点击返回
            finishWithResult();
        } else if (viewId == R.id.tv_right) {//点击完成
            nickname = editNickname.getText().toString();
            if (!nickname.isEmpty()) {
                getPresenter().commitNickname(100000001L, nickname);
            } else {
                Toast.makeText(this, "请填写您的昵称", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
