package com.example.module_me.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;

import java.util.List;

import example.common_base.entity.MyCourseVideoSectionHeadEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  课程录像设置老师或学生列表适配器
 */
public class CourseVideoPersonAdapter extends BaseSectionQuickAdapter<MyCourseVideoSectionHeadEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CourseVideoPersonAdapter(int layoutResId, int sectionHeadResId, List<MyCourseVideoSectionHeadEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    /**
     * 填充标题Item数据
     *
     * @param helper 数据绑定助手
     * @param item   标题数据实体
     */
    @Override
    protected void convertHead(BaseViewHolder helper, MyCourseVideoSectionHeadEntity item) {
        helper.setText(R.id.tvTitle, item.header);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, MyCourseVideoSectionHeadEntity item) {

    }
}
