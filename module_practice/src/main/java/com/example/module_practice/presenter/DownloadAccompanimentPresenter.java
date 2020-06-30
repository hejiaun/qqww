package com.example.module_practice.presenter;

import com.example.module_practice.R;
import com.example.module_practice.adapter.DownloadAccompanimentAdapter;
import com.example.module_practice.view_interface.IDownloadAccompanimentView;

import example.common_base.base.BasePresenter;
import example.common_base.entity.UserEntity;

import java.util.ArrayList;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:下载伴奏的Presenter
 */
public class DownloadAccompanimentPresenter extends BasePresenter<IDownloadAccompanimentView> {

    private ArrayList<UserEntity> entities = null;

    private DownloadAccompanimentAdapter adapter = null;

    public DownloadAccompanimentPresenter(IDownloadAccompanimentView iDownloadAccompanimentView) {
        super(iDownloadAccompanimentView);
    }

    public ArrayList getData() {
        if (entities == null) {
            entities = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                entities.add(new UserEntity());
            }
        }
        return entities;
    }

    public DownloadAccompanimentAdapter getAdapter() {
        if (adapter == null) {
            adapter = new DownloadAccompanimentAdapter(R.layout.item_download_song, getData());
        }
        return adapter;
    }
}
