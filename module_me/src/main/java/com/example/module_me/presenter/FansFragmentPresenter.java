package com.example.module_me.presenter;

import com.example.module_me.R;
import com.example.module_me.adapter.FansAdapter;
import com.example.module_me.view_interface.IFansFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.UserEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FansFragmentPresenter extends BasePresenter<IFansFragmentView> {
    ArrayList<UserEntity> userEntities = null;
    FansAdapter adapter = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iFansFragmentView View层接口
     */
    public FansFragmentPresenter(IFansFragmentView iFansFragmentView) {
        super(iFansFragmentView);
    }

    public FansAdapter getAdapter() {
        if (adapter == null) {
            adapter = new FansAdapter(R.layout.item_person_access, getData());
        }
        return adapter;
    }

    public ArrayList getData() {
        if (userEntities == null) {
            userEntities = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                userEntities.add(new UserEntity());
            }
        }
        return userEntities;
    }
}
