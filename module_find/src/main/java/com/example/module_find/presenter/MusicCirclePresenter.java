package com.example.module_find.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_find.entity.MusicCircleEntity;
import com.example.module_find.model.MusicCircleNewsModel;
import com.example.module_find.view_interface.IMusicCircleView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.UserEntity;
import example.common_base.util.DensityUtils;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  音乐圈Activity服务
 */
public class MusicCirclePresenter extends BasePresenter<IMusicCircleView> {

    private MusicCircleNewsModel model = null;

    public MusicCirclePresenter(IMusicCircleView iMusicCircleView) {
        super(iMusicCircleView);
        model = new MusicCircleNewsModel();
    }


    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {
        ArrayList<MusicCircleEntity> musicCircleEntities = new ArrayList<MusicCircleEntity>();
        for (int i = 1; i < 10; i++) {
            MusicCircleEntity musicCircleEntity = new MusicCircleEntity();
            ArrayList<UserEntity> userEntities = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                userEntities.add(new UserEntity());
            }
            musicCircleEntity.setComment(userEntities);
            musicCircleEntities.add(musicCircleEntity);
        }
        getView().getAdapter().addData(musicCircleEntities);
    }



}
