package com.example.module_me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.entity.WorkEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 作品列表适配器
 */
public class WorkAdapter extends BaseQuickAdapter<WorkEntity, CustomBaseViewHolder> {

    public WorkAdapter(int layoutResId, @Nullable List<WorkEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, WorkEntity item) {

    }
}
