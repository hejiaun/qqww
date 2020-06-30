package com.example.module_task.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_task.R;
import com.example.module_task.fragment.ChallengeTaskFragment;
import com.example.module_task.fragment.DailyTaskFragment;
import com.example.module_task.presenter.TaskActivityPresenter;
import com.example.module_task.view_interface.ITaskActivityView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BaseFragment;
import example.common_base.entity.TabEntity;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  任务Activity
 */
@Route(path = ARouterUtil.Task_Activity)
public class TaskActivity extends BaseActivity<TaskActivityPresenter> implements ITaskActivityView,
        ViewPager.OnPageChangeListener,
        OnTabSelectListener {
    TextView tvTitle;
    CommonTabLayout ctl;
    ViewPager vp;
    ImageView ivBack;
    TextView tvRight;
    private ArrayList<BaseFragment> fragments;

    /**
     * 加载布局
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        ctl = findViewById(R.id.ctl);
        vp = findViewById(R.id.vp);
        tvRight = findViewById(R.id.tv_right);
        ivBack = findViewById(R.id.iv_back);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("任务");

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("每日任务"));
        tabEntities.add(new TabEntity("挑战任务"));
        ctl.setTabData(tabEntities);

        fragments = new ArrayList<>();
        fragments.add(new DailyTaskFragment());
        fragments.add(new ChallengeTaskFragment());

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        vp.addOnPageChangeListener(this);
        ctl.setOnTabSelectListener(this);
        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_task;
    }

    /**
     * 创建Presenter
     *
     * @return presenter
     */
    @Override
    public TaskActivityPresenter createPresenter() {
        return new TaskActivityPresenter(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        ctl.setCurrentTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelect(int position) {
        vp.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    /**
     * 点击事件的监听
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
