package com.example.module_pk.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.entity.WordEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 弹幕输入框列表适配器
 */
public class BarrageDialogRecyclerViewAdapter extends BaseQuickAdapter<WordEntity, CustomBaseViewHolder> {

    public BarrageDialogRecyclerViewAdapter(int layoutResId, @Nullable List<WordEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, WordEntity item) {
        helper.setText(R.id.tvWord, item.getWord());
    }
}
