package com.example.module_public_busniess.presenter;

import com.example.module_public_busniess.R;
import com.example.module_public_busniess.adapter.FansListAdapter;
import com.example.module_public_busniess.view_interface.IFansListInSongDetailActivityView;

import example.common_base.entity.UserEntity;
import example.common_base.base.BasePresenter;

import java.util.ArrayList;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:歌曲详情Activity的Presenter
 */
public class FansListInSongDetailActivityPresenter extends BasePresenter<IFansListInSongDetailActivityView> {
    FansListAdapter adapter = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iFansListInSongDetailActivityView View层接口
     */
    public FansListInSongDetailActivityPresenter(IFansListInSongDetailActivityView iFansListInSongDetailActivityView) {
        super(iFansListInSongDetailActivityView);
    }


    public FansListAdapter getAdapter() {
        if (adapter == null) {
            adapter = new FansListAdapter(R.layout.item_fans_rank, new ArrayList<UserEntity>());
        }
        return adapter;
    }

    public void getData() {
        if (adapter != null) {
            for (int i = 0; i < 20; i++) {
                adapter.addData(new UserEntity());
            }
        }

    }

}
