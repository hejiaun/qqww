package com.example.module_public_busniess.presenter;

import com.example.administrator.shengdoushi.R;
import com.example.administrator.shengdoushi.business.public_business.adapter.HotCoverFragmentAdapter;
import com.example.administrator.shengdoushi.business.public_business.view_interface.IHotCoverFragmentView;
import example.common_base.base.BasePresenter;

import java.util.ArrayList;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:热门翻唱Fragment的Presenter
 */
public class HotCoverFragmentPresenter extends BasePresenter<IHotCoverFragmentView> {
    HotCoverFragmentAdapter adapter;

    public HotCoverFragmentPresenter(IHotCoverFragmentView iHotCoverFragmentView) {
        super(iHotCoverFragmentView);
    }

    public HotCoverFragmentAdapter getAdapter() {
        if (adapter == null) {
            adapter = new HotCoverFragmentAdapter(R.layout.item_hot_cover, new ArrayList());
            for (int i = 0; i < 20; i++) {
                adapter.addData(1);
            }
        }
        return adapter;

    }
}
