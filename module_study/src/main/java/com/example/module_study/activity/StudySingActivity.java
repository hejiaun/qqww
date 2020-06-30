package com.example.module_study.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_study.R;
import com.example.module_study.fragment.StudyCourseListFragment;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.StudyDataSelectorView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我要学音乐Activity
 */
@Route(path = ARouterUtil.StudySing_Activity)
public class StudySingActivity extends BaseActivity implements
        ViewPager.OnPageChangeListener
     {

    TextView tvTitle;
    ViewPager vp;
    StudyDataSelectorView studyDataSelectorView;
    ImageView ivBack;
    TextView tvRight;
    private ArrayList<Fragment> fragments;
    private StudyCourseListFragment bigCourseFragment;
    private StudyCourseListFragment oneByOneFragment;
    private StudyCourseListFragment oneByMoreFragment;
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        tvRight = findViewById(R.id.tv_right);
        vp = findViewById(R.id.vp);
        tvTitle = findViewById(R.id.tv_title);
        studyDataSelectorView = findViewById(R.id.studyDataSelectorView);

        ivBack.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        fragments = new ArrayList<>();
        bigCourseFragment = new StudyCourseListFragment();
        oneByOneFragment = new StudyCourseListFragment();
        oneByMoreFragment = new StudyCourseListFragment();
        fragments.add(bigCourseFragment);
        fragments.add(oneByOneFragment);
        fragments.add(oneByMoreFragment);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        };

        tvTitle.setText("学音乐");
        vp.setAdapter(fragmentPagerAdapter);

        vp.addOnPageChangeListener(this);
        studyDataSelectorView.setSelectListener(new StudyDataSelectorView.MyOnSelectListener() {
            @Override
            public void clickBigCourse() {
                vp.setCurrentItem(0, true);
            }

            @Override
            public void clickOneByOne() {
                vp.setCurrentItem(1, true);
            }

            @Override
            public void clickOneByMore() {
                vp.setCurrentItem(2, true);
            }

            @Override
            public void clickMonday() {

            }

            @Override
            public void clickTuesday() {

            }

            @Override
            public void clickWednesday() {

            }

            @Override
            public void clickThursday() {

            }

            @Override
            public void clickFriday() {

            }

            @Override
            public void clickSaturday() {

            }

            @Override
            public void clickSunday() {

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
        return R.layout.activity_study;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        studyDataSelectorView.clickCourseType(i + 1);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        }
    }
}
