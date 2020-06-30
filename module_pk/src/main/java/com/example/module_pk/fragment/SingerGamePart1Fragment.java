package com.example.module_pk.fragment;

import android.view.View;
import android.widget.ProgressBar;

import com.example.module_pk.R;
import com.example.module_pk.activity.PkSingerActivity;
import com.example.module_pk.presenter.SingerGamePart1FragmentPresenter;
import com.example.module_pk.view_interface.ISingerGamePart1FragmentView;

import example.common_base.base.BaseFragment;
import example.common_base.util.LrcUtil;
import me.wcy.lrcview.LrcView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 歌手模式的唱歌环节Fragment
 */
public class SingerGamePart1Fragment extends BaseFragment<SingerGamePart1FragmentPresenter> implements View.OnClickListener, ISingerGamePart1FragmentView {
    private LrcView lrcView;
    private ProgressBar progressBar;

    @Override
    public SingerGamePart1FragmentPresenter createPresenter() {
        return new SingerGamePart1FragmentPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        progressBar = view.findViewById(R.id.spv);
        progressBar.setOnClickListener(this);
        lrcView = view.findViewById(R.id.lrcView);
        view.findViewById(R.id.ll).setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        //----------------------配置歌词-----------------------//
        lrcView.loadLrc(LrcUtil.getInstence().getLrcText("chengdu.lrc", getThisActivity()));
        lrcView.setOnPlayClickListener(new LrcView.OnPlayClickListener() {
            @Override
            public boolean onPlayClick(long time) {
                return false;
            }
        });

    }

    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_singergame_part1;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.spv) {
            getThisActivity().getPresenter().showFragment(2, getThisActivity().getSupportFragmentManager().beginTransaction());
        }
    }

    public PkSingerActivity getThisActivity() {
        return (PkSingerActivity) getActivity();
    }

}
