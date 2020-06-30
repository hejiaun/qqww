package com.example.module_me.presenter;

import com.example.module_me.R;
import com.example.module_me.adapter.ListenAdapter;
import com.example.module_me.view_interface.IListeneFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.UserEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ListeneFragmentPresenter extends BasePresenter<IListeneFragmentView> {
    ArrayList<UserEntity> userEntities = null;
    ListenAdapter adapter = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iListeneFragmentView View层接口
     */
    public ListeneFragmentPresenter(IListeneFragmentView iListeneFragmentView) {
        super(iListeneFragmentView);
    }


    public ListenAdapter getAdapter() {
        if (adapter == null) {
            adapter = new ListenAdapter(R.layout.item_person_play, getData());
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
