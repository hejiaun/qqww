package com.example.module_find.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import example.common_base.entity.UserEntity;
import example.common_base.util.CustomBaseViewHolder;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 *
 */
public class MusicCircleNewsListAdapter extends BaseQuickAdapter<UserEntity, CustomBaseViewHolder> {

    public MusicCircleNewsListAdapter(int layoutResId, @Nullable List<UserEntity> data) {
        super(layoutResId, data);
    }

    /**
     * @param helper
     * @param item
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, UserEntity item) {
    }
}
