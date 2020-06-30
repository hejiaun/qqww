package com.example.module_chat.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;
import com.example.module_chat.entity.SelectBackgroundEntity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;
import example.common_base.util.DensityUtils;
import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 选择背景列表适配器
 */
public class SelectBackgroundAdapter extends BaseQuickAdapter<SelectBackgroundEntity, CustomBaseViewHolder> {

    public SelectBackgroundAdapter(int layoutResId, @Nullable List<SelectBackgroundEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, SelectBackgroundEntity item) {
        if (item.isSelect()) {
            helper.setVisible(R.id.ivSelect, true);
        } else {
            helper.setVisible(R.id.ivSelect, false);
        }
    }
}
