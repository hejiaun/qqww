package com.example.module_pk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.module_pk.activity.PkModeActivity;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ConstantValuesUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FirstEntryActivity extends BaseActivity implements View.OnClickListener {

    private View btnPk;
    private View btnRater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
        btnPk = findViewById(R.id.btnPk);
        btnRater = findViewById(R.id.btnRater);
        btnPk.setOnClickListener(this);
        btnRater.setOnClickListener(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_module_fristentry;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnPk) {
            Intent intentPK = new Intent(this, PkModeActivity.class);
            intentPK.putExtra("game_role", ConstantValuesUtil.GAME_ROLE_SINGER);
            startActivity(intentPK);
        } else if (id == R.id.btnRater) {
            Intent intentRater = new Intent(this, PkModeActivity.class);
            intentRater.putExtra("game_role", ConstantValuesUtil.GAME_ROLE_RATER);
            startActivity(intentRater);

        }
    }
}
