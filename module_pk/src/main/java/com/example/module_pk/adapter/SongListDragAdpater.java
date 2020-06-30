package com.example.module_pk.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;

import java.util.List;

import example.common_base.entity.SongListEntity;
import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class SongListDragAdpater extends BaseQuickAdapter<SongListEntity, CustomBaseViewHolder> {

    public SongListDragAdpater(List<SongListEntity> data) {
        super(R.layout.item_alternative_song, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, SongListEntity item) {

    }
}
