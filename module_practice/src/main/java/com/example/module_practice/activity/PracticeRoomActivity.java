package com.example.module_practice.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_practice.R;
import com.example.module_practice.fragment.ChoseSongFragment;
import com.example.module_practice.fragment.TargetFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.entity.SongListEntity;
import example.common_base.entity.TabEntity;
import example.common_base.util.ARouterUtil;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  练哥房Activity
 */
@Route(path = ARouterUtil.Practice_Activity)
public class PracticeRoomActivity extends BaseActivity implements
        ViewPager.OnPageChangeListener,
        OnTabSelectListener {
    CommonTabLayout ctl;
    ViewPager vp;
    private ArrayList tabEntities;
    private ArrayList<Fragment> fragments;
    private TargetFragment targetFragment;
    private ChoseSongFragment choseSongFragment3;
    private ChoseSongFragment choseSongFragment2;
    private ChoseSongFragment choseSongFragment1;
    private TextView tvPracticeSing;
    private TextView tvLocalAccompaniment;
    private TextView tvMyWork;
    private TextView tvMyVoiceRange;
    private ImageView ivBack;


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
        tvMyVoiceRange.setOnClickListener(this);
        tvMyWork.setOnClickListener(this);
        tvLocalAccompaniment.setOnClickListener(this);
        tvPracticeSing.setOnClickListener(this);

    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("已点歌曲"));
        tabEntities.add(new TabEntity("适合我的"));
        tabEntities.add(new TabEntity("经典必学"));
        tabEntities.add(new TabEntity("目标歌曲"));

        fragments = new ArrayList<>();
        choseSongFragment1 = new ChoseSongFragment();
        choseSongFragment2 = new ChoseSongFragment();
        choseSongFragment3 = new ChoseSongFragment();
        choseSongFragment3.setTabVisibility(View.VISIBLE);
        targetFragment = new TargetFragment();
        fragments.add(choseSongFragment1);
        fragments.add(choseSongFragment2);
        fragments.add(choseSongFragment3);
        fragments.add(targetFragment);


        ctl.setTabData(tabEntities);
        ctl.setTextsize(14);
        ctl.setCurrentTab(0);

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        vp.addOnPageChangeListener(this);
        ctl.setOnTabSelectListener(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_practice_room;
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


    /**
     * 移除添加了的歌曲
     *
     * @param songListEntity
     */
    public void removeSong(SongListEntity songListEntity) {
        targetFragment.reomveSong(songListEntity);
        notifyAllListDataChange();
    }

    /**
     * 添加歌曲到targetFragment
     *
     * @param songListEntity
     */
    public void addSong(SongListEntity songListEntity) {
        targetFragment.addSong(songListEntity);
        notifyAllListDataChange();
    }

    /**
     * 刷新所有列表数据
     */
    public void notifyAllListDataChange() {
        choseSongFragment1.notifyListDataChange();
        choseSongFragment2.notifyListDataChange();
        choseSongFragment3.notifyListDataChange();
        targetFragment.notifyListDataChange();
    }

    /**
     * 获取已选择的歌曲实体集合
     */
    public ArrayList<SongListEntity> getSelectSongEntities() {
        return targetFragment.getSongListEntities();
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
            startActivity(new Intent(this, LocalAccompanimentActivity.class));
        } else if (viewId == R.id.tv_practiceSing) {

        }
    }
}
