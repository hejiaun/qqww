package com.example.module_pk.presenter;

import com.example.module_pk.R;
import com.example.module_pk.adapter.PkShareAdapter;
import com.example.module_pk.entity.PkShareEntity;
import com.example.module_pk.entity.SingerEntity;
import com.example.module_pk.view_interface.IPkShareActivityView;

import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 分享Activity的Presenter层
 */
public class PkShareActivityPresenter extends BasePresenter<IPkShareActivityView> {

    private PkShareAdapter adapter;

    /**
     * 构造方法，初始化View层
     *
     * @param iPkShareActivityView View层接口
     */
    public PkShareActivityPresenter(IPkShareActivityView iPkShareActivityView) {
        super(iPkShareActivityView);
    }

    /**
     * 加载第一次进入界面的数据
     */
    public void initFirtEntryData() {
        if (adapter == null) {
            adapter = getView().getAdapter();
        }
        adapter.addData(new PkShareEntity(PkShareEntity.GROUP_TYPE));
        for (int i = 0; i < 6; i++) {
            adapter.addData(new PkShareEntity(new SingerEntity("汪峰", R.drawable.wanfeng)));
            adapter.addData(new PkShareEntity(new SingerEntity("周杰伦", R.drawable.jielun)));
            adapter.addData(new PkShareEntity(new SingerEntity("陈绮贞", R.drawable.yizhen)));
            adapter.addData(new PkShareEntity(new SingerEntity("杨坤", R.drawable.yangkun)));
            adapter.addData(new PkShareEntity(new SingerEntity("张学友", R.drawable.xueyou)));
        }
    }
}
