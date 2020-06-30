package com.example.module_public_busniess.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.shengdoushi.R;
import com.example.administrator.shengdoushi.business.chat.adapter.CustomBaseViewHolder;
import example.common_base.entity.CommentEntity;

import java.util.List;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:评论列表适配器
 */
public class WorkDetailCommentAdapter extends BaseQuickAdapter<CommentEntity, CustomBaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public WorkDetailCommentAdapter(int layoutResId, @Nullable List<CommentEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, CommentEntity item) {
        helper.setText(R.id.tvName, item.getUserName());
        helper.setText(R.id.tvContent, item.getContent());
        helper.setImageViewResourceByGlide(R.id.ivHead, item.getUserHeadUrl());
    }


}
