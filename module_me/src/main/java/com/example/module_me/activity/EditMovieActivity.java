package com.example.module_me.activity;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_me.R;
import com.example.module_me.presenter.EditMovieActivityPresenter;
import com.example.module_me.view_interface.IEditMovieActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人喜好电影信息的Activity
 */
public class EditMovieActivity extends BaseActivity<EditMovieActivityPresenter> implements
        IEditMovieActivityView
       {
    TextView tv_right;
    TextView tvTitle;
    EditText editMovie;
    public final static int RESULTCODE = 0xFFF95;
    private String resultMovie;
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        tv_right = findViewById(R.id.tv_right);
        tvTitle = findViewById(R.id.tv_title);
        editMovie = findViewById(R.id.editMovie);
        ivBack = findViewById(R.id.iv_back);
        tv_right.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);

        tv_right.setText("完成");
        tvTitle.setText("电影");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_movie;
    }

    @Override
    public EditMovieActivityPresenter createPresenter() {
        return new EditMovieActivityPresenter(this);
    }


    /**
     * 结束并回传参数
     */
    private void finishWithResult() {
        if (resultMovie != null && !resultMovie.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("result", resultMovie + "");
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
        if (viewId == R.id.tv_right) {//点击完成
            resultMovie = editMovie.getText().toString().trim();
            if (!resultMovie.isEmpty()) {
                getPresenter().commitMovie(100000001L, resultMovie);
            } else {
                Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
            }
        } else if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
