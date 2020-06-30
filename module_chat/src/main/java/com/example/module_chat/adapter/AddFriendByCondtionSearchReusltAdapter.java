package com.example.module_chat.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import example.common_base.entity.UserEntity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 添加好友条件查询结果
 */
public class AddFriendByCondtionSearchReusltAdapter extends BaseQuickAdapter<UserEntity, CustomBaseViewHolder> {
    public AddFriendByCondtionSearchReusltAdapter(int layoutResId, @Nullable List<UserEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, UserEntity item) {

    }
}
