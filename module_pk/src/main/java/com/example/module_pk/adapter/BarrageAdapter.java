package com.example.module_pk.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.entity.BarrageEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 弹幕列表适配器
 */
public class BarrageAdapter extends BaseQuickAdapter<BarrageEntity, CustomBaseViewHolder> {
    public BarrageAdapter(int layoutResId, @Nullable List<BarrageEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, BarrageEntity item) {
        helper.setText(R.id.tvContent, item.getContent());
    }
}
