package com.example.module_public_busniess.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.shengdoushi.R;
import example.common_base.entity.UserEntity;

import java.util.List;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:粉丝列表适配器
 */
public class FansListAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public FansListAdapter(int layoutResId, @Nullable List<UserEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {
//        helper.setVisible(R.id.tvRank, f);
        helper.setVisible(R.id.ivRank, true);
    }

}
