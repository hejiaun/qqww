package com.example.module_public_busniess.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class HotCoverFragmentAdapter extends BaseQuickAdapter {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public HotCoverFragmentAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }

}
