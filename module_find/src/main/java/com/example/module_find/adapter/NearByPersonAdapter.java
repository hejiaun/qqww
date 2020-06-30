package com.example.module_find.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.common_base.entity.NearbyPersonEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  附近的人列表适配器
 */
public class NearByPersonAdapter extends BaseQuickAdapter<NearbyPersonEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局id
     * @param data        列表布局数据集合
     */
    public NearByPersonAdapter(int layoutResId, @Nullable List<NearbyPersonEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, NearbyPersonEntity item) {

    }
}
