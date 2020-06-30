package com.example.module_public_busniess.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shenzgdoushi.R;
import com.example.administrator.shengdoushi.business.public_business.presenter.SongDetailsActivityPresenter;
import com.example.administrator.shengdoushi.business.public_business.view_interface.ISongDetailsActivityView;

import butterknife.BindView;
import butterknife.OnClick;
import example.common_base.base.BaseActivity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 歌曲详情Activity
 */
public class SongDetailsActivity extends BaseActivity<SongDetailsActivityPresenter> implements ViewPager.OnPageChangeListener, ISongDetailsActivityView {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tvSongStrategy)
    TextView tvSongStrategy;
    @BindView(R.id.tvHotCover)
    TextView tvHotCover;
    @BindView(R.id.tvWaitChorus)
    TextView tvWaitChorus;

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("歌曲详情");
        setTab(0);

        vp.setAdapter(getPresenter().getViewPageAdapter(getSupportFragmentManager()));
        vp.addOnPageChangeListener(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_song_detail;
    }

    /**
     * 传presenter
     *
     * @return presenter
     */
    @Override
    public SongDetailsActivityPresenter createPresenter() {
        return new SongDetailsActivityPresenter(this);
    }

    /**
     * 点击事件监听
     * @param view 被点击的控件
     */
    @OnClick({R.id.tvSongStrategy, R.id.tvHotCover, R.id.tvWaitChorus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSongStrategy:
                setTab(0);
                vp.setCurrentItem(0);
                break;
            case R.id.tvHotCover:
                setTab(1);
                vp.setCurrentItem(1);
                break;
            case R.id.tvWaitChorus:
                setTab(2);
                vp.setCurrentItem(2);
                break;
        }
    }

    /**
     * 选中当前标签
     *
     * @param position
     */
    public void setTab(int position) {
        tvSongStrategy.setTextColor(getResources().getColor(R.color.fontGray));
        tvHotCover.setTextColor(getResources().getColor(R.color.fontGray));
        tvWaitChorus.setTextColor(getResources().getColor(R.color.fontGray));
        switch (position) {
            case 0:
                tvSongStrategy.setTextColor(getResources().getColor(R.color.red_select));
                break;
            case 1:
                tvHotCover.setTextColor(getResources().getColor(R.color.red_select));
                break;
            case 2:
                tvWaitChorus.setTextColor(getResources().getColor(R.color.red_select));
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
