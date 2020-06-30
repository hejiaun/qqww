package com.example.module_find.presenter;

import com.example.module_find.model.MusicCircleNewsModel;
import com.example.module_find.view_interface.IMusicCircleNewsActivityView;

import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MusicCircleNewsActivityPresenter extends BasePresenter<IMusicCircleNewsActivityView> {

    MusicCircleNewsModel model = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iMusicCircleNewsActivityView View层接口
     */
    public MusicCircleNewsActivityPresenter(IMusicCircleNewsActivityView iMusicCircleNewsActivityView) {
        super(iMusicCircleNewsActivityView);
    }

    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {

    }
}
