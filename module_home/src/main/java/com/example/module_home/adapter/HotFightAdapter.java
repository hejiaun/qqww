package com.example.module_home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.common_base.entity.HotFightEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  热门比赛列表的适配器
 */
public class HotFightAdapter extends BaseQuickAdapter<HotFightEntity, BaseViewHolder> {

    public HotFightAdapter(int layoutResId, @Nullable List<HotFightEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, HotFightEntity item) {

    }
}
