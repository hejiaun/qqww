package com.example.module_practice.activity;

import android.view.View;
import android.widget.TextView;

import com.example.module_practice.R;
import com.example.module_practice.presenter.SingleSingPresenter;
import com.example.module_practice.view_interface.ISingleSingView;
import com.jaeger.library.StatusBarUtil;

import example.common_base.base.BaseActivity;
import example.common_base.util.LrcUtil;
import example.common_base.widget.MusicControlerView;
import example.common_base.widget.SongProgressView;
import me.wcy.lrcview.LrcView;

/**
 * Author: HeJiaJun
 * Date:
 * Description: 独唱Activity
 */
public class SingleSingActivity extends BaseActivity<SingleSingPresenter> implements
        ISingleSingView {
    TextView tvTitle;
    LrcView lrc;
    SongProgressView spv;
    MusicControlerView mcv;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        lrc = findViewById(R.id.lrc);
        spv = findViewById(R.id.spv);
        mcv = findViewById(R.id.mcv);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        mcv.setVisibility(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
        mcv.setSingleSingRight();
        //设置状态栏
        StatusBarUtil.setTransparent(this);
        //设置歌词控件
        lrc.loadLrc(LrcUtil.getInstence().getLrcText("chengdu.lrc", this));
        //设置进度条控件
        spv.setDuration(283);
        spv.setCurrentProgress(0);

        lrc.setOnPlayClickListener(getPresenter().getOnPlayClickListener());
        spv.setProgressChangeListener(getPresenter().getSeekBarChangeListener());
    }

    /**
     * View层向Presenter层提供SingleSingActivity
     *
     * @return SingleSingActivity
     */
    @Override
    public SingleSingActivity getActivity() {
        return this;
    }

    /**
     * View层向Presenter层提供LrcView
     *
     * @return LrcView
     */
    @Override
    public LrcView getLrcView() {
        return lrc;
    }

    /**
     * View层向Presenter层提供SongProgressView
     *
     * @return SongProgressView
     */
    @Override
    public SongProgressView getSongProgressView() {
        return spv;
    }


    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_single_sing;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SingleSingPresenter createPresenter() {
        return new SingleSingPresenter(this);
    }
}
