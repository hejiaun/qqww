package com.example.module_find.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.module_find.R;
import com.example.module_find.presenter.FindNearByPresenter;
import com.example.module_find.view_interface.IFindNearByView;
import com.flyco.tablayout.SegmentTabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.angmarch.views.NiceSpinner;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  发现附近的人Activity
 */
public class FindNearbyActivity extends BaseActivity<FindNearByPresenter> implements
        IFindNearByView, SwipeRefreshLayout.OnRefreshListener {
    SegmentTabLayout stl;
    Banner banner;
    NiceSpinner spCourse;
    NiceSpinner spLocate;
    NiceSpinner spUnderground;
    NiceSpinner spOther;
    RecyclerView rcv;
    SwipeRefreshLayout srl;
    String[] tabString = new String[]{"老师", "学生"};
    private ImageView ivBack;

    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        stl = findViewById(R.id.stl);
        banner = findViewById(R.id.banner);
        spCourse = findViewById(R.id.sp_course);
        spLocate = findViewById(R.id.sp_locate);
        spUnderground = findViewById(R.id.sp_underground);
        spOther = findViewById(R.id.sp_other);
        rcv = findViewById(R.id.rcv);
        srl = findViewById(R.id.srl);
        ivBack.setOnClickListener(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_find_nearby;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public FindNearByPresenter createPresenter() {
        return new FindNearByPresenter(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        stl.setTabData(tabString);
        stl.setTextsize(16);
        stl.setCurrentTab(0);

        setImageBanner();

        rcv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecoration());

        spOther.attachDataSource(getPresenter().getSpinnerData());
        spLocate.attachDataSource(getPresenter().getSpinnerData());
        spCourse.attachDataSource(getPresenter().getSpinnerData());
        spUnderground.attachDataSource(getPresenter().getSpinnerData());

        srl.setOnRefreshListener(this);
    }

    /**
     * 设置轮播图加载器
     */
    public void setImageBanner() {
        banner.setImages(getPresenter().getBannerData());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.start();
    }

    /**
     * View层向Presenter提供FindNearbyActivity
     *
     * @return FindNearbyActivity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 下拉刷新控件的刷新事件监听
     */
    @Override
    public void onRefresh() {
        srl.setRefreshing(false);
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
        }
    }
}
