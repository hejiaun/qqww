package com.example.module_me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.EditHeightAndWeightActivityPresenter;
import com.example.module_me.view_interface.IEditHeightAndWeightActivityView;
import com.jaeger.library.StatusBarUtil;
import com.lsp.RulerView;

import cn.vfighter.usercenter.param.UpdateUserInfoParam;
import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人身高体重的Activity
 */
public class EditHeightAndWeightActivity extends BaseActivity<EditHeightAndWeightActivityPresenter>
        implements IEditHeightAndWeightActivityView {
    TextView tv_right;
    TextView tvTitle;
    FunctionItemView fiv_height;
    FunctionItemView fiv_weight;
    RulerView rulerHeight;
    RulerView rulerWeight;
    public final static int RESULTCODE = 0xFFF98;
    private int currentScale = 0;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);
        fiv_height = findViewById(R.id.fiv_height);
        fiv_weight = findViewById(R.id.fiv_weight);
        rulerHeight = findViewById(R.id.rulerHeight);
        rulerWeight = findViewById(R.id.rulerWeight);
        ivBack = findViewById(R.id.iv_back);
        tv_right.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("身高体重");
        fiv_weight.setTextTitle("体重");
        fiv_height.setTextTitle("身高");

        rulerHeight.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String s) {

            }

            @Override
            public void onScrollResult(String s) {
                fiv_height.setOnlyRightText(s + "cm");
            }
        });

        rulerWeight.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String s) {

            }

            @Override
            public void onScrollResult(String s) {
                fiv_weight.setOnlyRightText(s + "kg");
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_height_weight;
    }

    @Override
    public EditHeightAndWeightActivityPresenter createPresenter() {
        return new EditHeightAndWeightActivityPresenter(this);
    }

    /**
     * 结束并回传参数
     */
    private void finishWithResult() {
        if (currentScale != 0) {
            Intent intent = new Intent();
            intent.putExtra("result", currentScale + "cm");
            setResult(RESULTCODE, intent);
        }
        ActivityUtil.getInstance().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        finishWithResult();
        super.onBackPressed();
    }

    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_right) {
            UpdateUserInfoParam updateUserInfoParam = new UpdateUserInfoParam();
            updateUserInfoParam.setHeight((int) rulerHeight.currentScale);
            updateUserInfoParam.setWeight((int) rulerWeight.currentScale);
            getPresenter().commitHeightAndWeight(updateUserInfoParam, currentScale);
        } else if (viewId == R.id.iv_back) {
            finishWithResult();
        }
        return;
    }
}
