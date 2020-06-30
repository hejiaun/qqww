package com.example.module_me.activity;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.CourseActivityPresenter;
import com.example.module_me.view_interface.ICourseActivityView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.DensityUtils;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的课程Activity
 */
public class CourseActivity extends BaseActivity<CourseActivityPresenter> implements  ICourseActivityView {

    TextView tvTitle;
    RecyclerView rcv;
    private ImageView ivBack;


    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tvTitle);
        rcv = findViewById(R.id.rcv);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        tvTitle.setText("课程");
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(getPresenter().getAdapter());

        rcv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(CourseActivity.this, 4);
            }
        });
    }


    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_course;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public CourseActivityPresenter createPresenter() {
        return new CourseActivityPresenter(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
