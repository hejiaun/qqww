package com.example.module_chat.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.common_base.entity.UserEntity;


/**
 * 推荐老师列表适配器
 */
public class ChatTeacherAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public ChatTeacherAdapter(int layoutResId, @Nullable List<UserEntity> data) {
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
    }
}
