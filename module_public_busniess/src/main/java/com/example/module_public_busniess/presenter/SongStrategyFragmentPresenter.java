package com.example.module_public_busniess.presenter;

import com.example.administrator.shengdoushi.business.public_business.view_interface.ISongStrategyFragmentView;
import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:歌曲攻略Fragment的Presenter
 */
public class SongStrategyFragmentPresenter extends BasePresenter<ISongStrategyFragmentView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iSongStrategyFragmentView View层接口
     */
    public SongStrategyFragmentPresenter(ISongStrategyFragmentView iSongStrategyFragmentView) {
        super(iSongStrategyFragmentView);
    }
}
