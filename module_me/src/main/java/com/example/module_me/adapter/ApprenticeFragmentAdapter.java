package com.example.module_me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;

import java.util.List;

import example.common_base.entity.CourseEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:拜师fragmentAdapter
 */
public class ApprenticeFragmentAdapter extends BaseQuickAdapter<CourseEntity, BaseViewHolder> {
    private BaseViewHolder helper;
    private CourseEntity item;

    /**
     * 构造方法
     *
     * @param layoutResId 列表布局id
     * @param data        列表布局数据集合
     */
    public ApprenticeFragmentAdapter(int layoutResId, @Nullable List<CourseEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, final CourseEntity item) {
        this.helper = helper;
        this.item = item;
        helper.setText(R.id.tvNumber, item.getNum() + "");
        helper.addOnClickListener(R.id.tvReduce);
        helper.addOnClickListener(R.id.tvAdd);
    }

}
