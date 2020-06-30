package com.example.module_me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.common_base.entity.CourseEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  课程录像列表适配器
 */
public class CourseVideoAdapter extends BaseQuickAdapter<CourseEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表布局id
     * @param data        列表布局数据集合
     */
    public CourseVideoAdapter(int layoutResId, @Nullable List<CourseEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, CourseEntity item) {

    }
}
