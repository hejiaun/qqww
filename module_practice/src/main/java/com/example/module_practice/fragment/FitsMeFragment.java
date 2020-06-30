package com.example.module_practice.fragment;

import com.example.module_practice.PracticeSongListView;
import com.example.module_practice.R;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  适合我的歌曲Fragment
 */
public class FitsMeFragment extends BaseFragment {
    PracticeSongListView lvSongList;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        lvSongList = view.findViewById(R.id.lv_songList);
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
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_practice_list;
    }
}
