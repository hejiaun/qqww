package com.example.module_practice.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_practice.R;
import com.example.module_practice.fragment.DownloadAccompanimentFragment;
import com.example.module_practice.fragment.UploadAccompanimentFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.entity.TabEntity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:本地伴奏Activity
 */
public class LocalAccompanimentActivity extends BaseActivity implements
        ViewPager.OnPageChangeListener,
        OnTabSelectListener {

    private CommonTabLayout ctl;
    private ViewPager vp;
    private ImageView ivBack;
    private ArrayList tabEntities;
    private ArrayList<BaseFragment> fragments;
    private TextView tvMyVoiceRange;
    private TextView tvMyWork;
    private TextView tvLocalAccompaniment;
    private TextView tvPracticeSing;

    @Override
    public void initView() {
        super.initView();

        ctl = findViewById(R.id.ctl);
        vp = findViewById(R.id.vp);
        ivBack = findViewById(R.id.iv_back);
        tvMyVoiceRange = findViewById(R.id.tv_myVoiceRange);
        tvMyWork = findViewById(R.id.tv_myWork);
        tvLocalAccompaniment = findViewById(R.id.tv_localAccompaniment);
        tvPracticeSing = findViewById(R.id.tv_practiceSing);

        ivBack.setOnClickListener(this);
        tvLocalAccompaniment.setOnClickListener(this);
        tvMyVoiceRange.setOnClickListener(this);
        tvMyWork.setOnClickListener(this);
        tvPracticeSing.setOnClickListener(this);
    }


    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_local_accompaniment;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tabEntities = new ArrayList();
        tabEntities.add(new TabEntity("下载伴奏到电脑"));
        tabEntities.add(new TabEntity("上传伴奏到声斗士"));
        fragments = new ArrayList<>();
        fragments.add(new DownloadAccompanimentFragment());
        fragments.add(new UploadAccompanimentFragment());

        ctl.setTabData(tabEntities);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });

        vp.addOnPageChangeListener(this);
        ctl.setOnTabSelectListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ctl.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelect(int position) {
        vp.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.tv_myVoiceRange) {

        } else if (viewId == R.id.tv_myWork) {

        } else if (viewId == R.id.tv_localAccompaniment) {

        } else if (viewId == R.id.tv_practiceSing) {

        }
    }
}
