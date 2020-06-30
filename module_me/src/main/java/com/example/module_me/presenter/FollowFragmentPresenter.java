package com.example.module_me.presenter;

import com.example.module_me.R;
import com.example.module_me.adapter.FollowAdapter;
import com.example.module_me.view_interface.IFollowFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.UserEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FollowFragmentPresenter extends BasePresenter<IFollowFragmentView> {
    ArrayList<UserEntity> userEntities = null;
    FollowAdapter adapter = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iFollowFragmentView View层接口
     */
    public FollowFragmentPresenter(IFollowFragmentView iFollowFragmentView) {
        super(iFollowFragmentView);
    }


    public FollowAdapter getAdapter() {
        if (adapter == null) {
            adapter = new FollowAdapter(R.layout.item_person_play, getData());
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
