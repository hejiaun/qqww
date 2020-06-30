package com.example.module_me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;

import java.util.List;

import example.common_base.entity.CoinEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  音乐币充值列表适配器
 */
public class CoinMenuAdapter extends BaseQuickAdapter<CoinEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表布局id
     * @param data        列表布局数据集合
     */
    public CoinMenuAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, CoinEntity item) {
        helper.setText(R.id.tv_coinNum, item.getMusicCoinNum() + "");
        helper.setText(R.id.btnPrice, "￥" + item.getRMB());
    }
}
