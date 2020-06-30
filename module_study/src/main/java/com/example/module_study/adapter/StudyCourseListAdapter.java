package com.example.module_study.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_study.R;

import java.util.List;

import example.common_base.entity.StudyCourseEntity;
import example.common_base.util.DensityUtils;
import example.common_base.util.WindowUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我要学音乐课程列表适配器
 */
public class StudyCourseListAdapter extends BaseQuickAdapter<StudyCourseEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public StudyCourseListAdapter(int layoutResId, @Nullable List<StudyCourseEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, StudyCourseEntity item) {
        //填充数据
        helper.setText(R.id.tvLevel, item.getLevel());
        helper.setText(R.id.tv_time, item.getDate());
        helper.setText(R.id.tv_teacher, item.getTeacher());
        helper.setText(R.id.tv_courseType, item.getCourseType());
        ViewGroup.LayoutParams layoutParams = helper.getConvertView().getLayoutParams();
        layoutParams.height = WindowUtil.getInstence().getWindowWidth(helper.getConvertView().getContext()) / 2 - DensityUtils.dp2px(helper.getConvertView().getContext(), 16);

    }
}
