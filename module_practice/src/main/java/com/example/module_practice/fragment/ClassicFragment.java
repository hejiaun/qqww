package com.example.module_practice.fragment;


import com.example.module_practice.PracticeSongListView;
import com.example.module_practice.R;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  经典歌曲Fragment
 */
public class ClassicFragment extends BaseFragment {
    PracticeSongListView lvSongList;

    /**
     * 控件加载
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
     * @return 布局
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_practice_list;
    }


}
