package com.example.module_me.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.example.module_me.R;
import com.example.module_me.presenter.StateDesActivityPresenter;
import com.example.module_me.view_interface.IStateDesActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 状态说明
 */
public class StateDesActivity extends BaseActivity<StateDesActivityPresenter> implements IStateDesActivityView {

    private SuperTextView stvTitleBar;
    private TextView tvWordCount;
    private EditText etStateDes;

    @Override
    public int initLayout() {
        return R.layout.activity_state_des;
    }

    @Override
    public void initView() {
        super.initView();
        etStateDes = findViewById(R.id.etStateDes);
        tvWordCount = findViewById(R.id.tvWordCount);
        stvTitleBar = findViewById(R.id.stvTitleBar);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                ActivityUtil.getInstance().finishActivity(StateDesActivity.class);
            }
        });
        etStateDes.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    public StateDesActivityPresenter createPresenter() {
        return new StateDesActivityPresenter(this);
    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int length = s.length();
            if (length <= 120) {
                tvWordCount.setText(length + "/120");
            }
        }
    }
}
