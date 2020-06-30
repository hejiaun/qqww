package com.example.module_me.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;

import java.util.List;

import example.common_base.entity.FightRecordVideoEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  对战记录录像列表适配器
 */
public class FightRecordVideoAdapter extends BaseMultiItemQuickAdapter<FightRecordVideoEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FightRecordVideoAdapter(List<FightRecordVideoEntity> data) {
        super(data);
        //绑定布局
        addItemType(FightRecordVideoEntity.PLAYER, R.layout.item_record_player);
        addItemType(FightRecordVideoEntity.RATER, R.layout.item_record_rater);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, FightRecordVideoEntity item) {

    }
}
