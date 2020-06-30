package com.example.module_me.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_me.R;
import com.example.module_me.presenter.EditCharacterActivityPresenter;
import com.example.module_me.view_interface.IEditCharacterActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人性格的Activity
 */
public class EditCharacterActivity extends BaseActivity<EditCharacterActivityPresenter> implements

        IEditCharacterActivityView {
    /**
     * 已选中的标签
     */
    TextView selectTag1;
    TextView selectTag2;
    private String resultCharacter = "";
    public final static int RESULTCODE = 0xFFF97;
    TextView tv_right;
    TextView tvTitle;
    TextView tag1;
    TextView tag2;
    TextView tag3;
    TextView tag4;
    TextView tag5;
    TextView tag6;
    TextView tag7;
    TextView tag8;
    TextView tag9;
    TextView tag10;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);
        tag4 = findViewById(R.id.tag4);
        tag5 = findViewById(R.id.tag5);
        tag6 = findViewById(R.id.tag6);
        tag7 = findViewById(R.id.tag7);
        tag8 = findViewById(R.id.tag8);
        tag9 = findViewById(R.id.tag9);
        tag10 = findViewById(R.id.tag10);
        ivBack = findViewById(R.id.iv_back);

        tag1.setOnClickListener(this);
        tag2.setOnClickListener(this);
        tag3.setOnClickListener(this);
        tag4.setOnClickListener(this);
        tag5.setOnClickListener(this);
        tag6.setOnClickListener(this);
        tag7.setOnClickListener(this);
        tag8.setOnClickListener(this);
        tag9.setOnClickListener(this);
        tag10.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tv_right.setOnClickListener(this);


    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("性格");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_character;
    }

    @Override
    public EditCharacterActivityPresenter createPresenter() {
        return new EditCharacterActivityPresenter(this);
    }

    /**
     * 选中标签
     *
     * @param tag
     */
    private void selectTag(TextView tag) {
        if (selectTag1 == tag || selectTag2 == tag) {
            return;
        }
        if (selectTag1 != null) {
            selectTag1.setTextColor(Color.BLACK);
            selectTag1.setBackground(getResources().getDrawable(R.drawable.shape_gray_round_10dp));
        }
        tag.setTextColor(Color.WHITE);
        tag.setBackground(getResources().getDrawable(R.drawable.shape_blue_round_10dp));
        if (selectTag2 != selectTag1) {
            selectTag1 = selectTag2;
        }
        selectTag2 = tag;
    }

    /**
     * 结束并回传参数
     */
    private void finishWithResult() {
        if (resultCharacter != null && !resultCharacter.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("result", resultCharacter);
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
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            finishWithResult();
        } else if (viewId == R.id.tv_right) {//点击完成
            finishSelect();
        } else if (viewId == R.id.tag1) {
            selectTag(tag1);
        } else if (viewId == R.id.tag2) {
            selectTag(tag2);
        } else if (viewId == R.id.tag3) {

            selectTag(tag3);
        } else if (viewId == R.id.tag4) {
            selectTag(tag4);
        } else if (viewId == R.id.tag5) {
            selectTag(tag5);
        } else if (viewId == R.id.tag6) {
            selectTag(tag6);
        } else if (viewId == R.id.tag7) {
            selectTag(tag7);
        } else if (viewId == R.id.tag8) {
            selectTag(tag8);
        } else if (viewId == R.id.tag9) {
            selectTag(tag9);
        } else if (viewId == R.id.tag10) {
            selectTag(tag10);
        }
    }

    /**
     * 完成选择
     */
    private void finishSelect() {
        if (selectTag1 == null && selectTag2 == null) {
            Toast.makeText(this, "请选择你的性格描述", Toast.LENGTH_SHORT).show();
        } else {
            if (selectTag1 != null) {
                resultCharacter = resultCharacter + selectTag1.getText().toString().trim() + "  ";
            }
            if (selectTag2 != null) {
                resultCharacter = resultCharacter + selectTag2.getText().toString().trim();
            }
            getPresenter().commnitCharacter(100000001L, resultCharacter);
        }
    }
}
