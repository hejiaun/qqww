package com.example.module_me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_me.R;
import com.example.module_me.presenter.EditFoodActivityPresenter;
import com.example.module_me.view_interface.IEditFoodActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人喜好食物信息的Activity
 */
public class EditFoodActivity extends BaseActivity<EditFoodActivityPresenter> implements
        IEditFoodActivityView {
    TextView tv_right;
    TextView tvTitle;
    EditText editFood;
    public final static int RESULTCODE = 0xFFF96;
    private String resultFood;

    @Override
    public void initView() {
        super.initView();
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);
        editFood = findViewById(R.id.editFood);
        tv_right.setOnClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);

    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("食物");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_edit_food;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public EditFoodActivityPresenter createPresenter() {
        return new EditFoodActivityPresenter(this);
    }

    /**
     * 结束并回传参数
     */
    private void finishWithResult() {
        if (resultFood != null && !resultFood.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("result", resultFood + "");
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
        if (viewId == R.id.iv_back) {//点击返回
            finishWithResult();
        } else if (viewId == R.id.tv_right) {//点击完成
            resultFood = editFood.getText().toString().trim();
            if (!resultFood.isEmpty()) {
                getPresenter().commitFood(100000001L, resultFood);
            } else {
                Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
