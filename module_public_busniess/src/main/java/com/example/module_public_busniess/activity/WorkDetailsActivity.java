package com.example.module_public_busniess.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shengdoushi.R;
import com.example.administrator.shengdoushi.business.public_business.presenter.WorkDetailsActivityPresenter;
import com.example.administrator.shengdoushi.business.public_business.view_interface.IWorkDetailsActivityView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import example.common_base.base.BaseActivity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  歌曲详情Activity
 */
public class WorkDetailsActivity extends BaseActivity<WorkDetailsActivityPresenter> implements IWorkDetailsActivityView {
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    //    @BindView(R.id.iv_head)
//    RoundedImageView ivHead;
//    @BindView(R.id.tv_name)
//    TextView tvName;
//    @BindView(R.id.tv_listen_count)
//    TextView tvListenCount;
//    @BindView(R.id.tv_popularity)
//    TextView tvPopularity;
//    @BindView(R.id.tv_diary_time)
//    TextView tvDiaryTime;
//    @BindView(R.id.tv_diary)
//    TextView tvDiary;
//    @BindView(R.id.appbar)
//    AppBarLayout appbar;
//    @BindView(R.id.rv_fans)
//    RecyclerView rvFans;
//    @BindView(R.id.ll)
//    LinearLayout ll;
//    @BindView(R.id.mcv)
//    MusicControlerView mcv;

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setTransparent(this);
        rcv.setAdapter(getPresenter().getAdapter());
        rcv.addItemDecoration(getPresenter().getItemDecotation());
        getPresenter().getData();
    }


    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_workdetails;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public WorkDetailsActivityPresenter createPresenter() {
        return new WorkDetailsActivityPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @OnClick({R.id.ivMore, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivMore://点击更多菜单
                getPresenter().showMoreMenuDialog();
                break;
            case R.id.iv_back://点击返回
                finish();
                break;
        }
    }

    /**
     * View层向Presenter层提供
     *
     * @return
     */
    @Override
    public WorkDetailsActivity getActivity() {
        return this;
    }
}
