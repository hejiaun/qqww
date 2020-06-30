package com.example.module_home.adapter;

import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_home.R;

import java.util.List;

import example.common_base.entity.RankingListEntity;
import example.common_base.util.CustomBaseViewHolder;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  名人榜列表适配器
 */
public class RankRecyclerViewAdapter extends BaseQuickAdapter<RankingListEntity, CustomBaseViewHolder> {
    public RankRecyclerViewAdapter(int layoutResId, @Nullable List<RankingListEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param entity   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, RankingListEntity entity) {
        helper.setText(R.id.tv_number, entity.getRank() + "");
        helper.setImageViewResourceByGlide(R.id.iv_rank, entity.getRankUserHead());
//        helper.setImageViewResourceByGlide(R.id.iv_guide, entity.getTeacherUserHead());
        helper.setImageViewResourceByGlide(R.id.iv_judge, entity.getRaterUserHead());
        helper.setImageViewResourceByGlide(R.id.iv_popularity, entity.getPropularity());
        helper.setImageViewResourceByGlide(R.id.iv_song, entity.getSongUserHead());
        helper.addOnClickListener(R.id.iv_rank);
//        helper.addOnClickListener(R.id.iv_guide);
        helper.addOnClickListener(R.id.iv_judge);
        helper.addOnClickListener(R.id.iv_popularity);
        helper.addOnClickListener(R.id.iv_song);
    }
}
