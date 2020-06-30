package com.example.module_home.presenter;


import com.example.module_home.model.HotFightListFragmentModel;
import com.example.module_home.view_interface.IHotFightListFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.HotFightEntity;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class HotFightListFragmentPresenter extends BasePresenter<IHotFightListFragmentView> {
    private final HotFightListFragmentModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iHotFightListFragmentView View层接口
     */
    public HotFightListFragmentPresenter(IHotFightListFragmentView iHotFightListFragmentView) {
        super(iHotFightListFragmentView);
        model = new HotFightListFragmentModel();
    }


    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntry() {
        model.getFirstEntryData(new Action1<ArrayList<HotFightEntity>>() {
            @Override
            public void call(ArrayList<HotFightEntity> hotFightEntities) {
                getView().getAdapter().addData(hotFightEntities);
            }
        });
    }


}
