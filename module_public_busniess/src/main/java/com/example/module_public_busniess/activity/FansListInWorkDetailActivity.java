package com.example.module_public_busniess.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.module_public_busniess.R;
import com.example.module_public_busniess.R2;
import com.example.module_public_busniess.presenter.FansListInSongDetailActivityPresenter;
import com.example.module_public_busniess.view_interface.IFansListInSongDetailActivityView;

import butterknife.BindView;
import butterknife.OnClick;
import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:粉丝列表Activity
 */
public class FansListInWorkDetailActivity extends BaseActivity<FansListInSongDetailActivityPresenter> implements IFansListInSongDetailActivityView {
    @BindView(R2.id.rcv)
    RecyclerView rcv;
    @BindView(R2.id.tv_title)
    TextView tvTitle;

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("粉丝榜单");
        rcv.setAdapter(getPresenter().getAdapter());
        getPresenter().getData();
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @OnClick({R2.id.iv_back})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);

        }

    }

    /**
     * 布局加载
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_fanslist_workdetail;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public FansListInSongDetailActivityPresenter createPresenter() {
        return new FansListInSongDetailActivityPresenter(this);
    }
}
