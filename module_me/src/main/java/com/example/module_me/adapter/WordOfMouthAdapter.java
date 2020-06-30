package com.example.module_me.adapter;

import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import example.common_base.entity.WordOfMouthEvaluateEntity;
import example.common_base.entity.WordOfMouthSectionEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:口碑列表适配器
 */
public class WordOfMouthAdapter extends BaseSectionQuickAdapter<WordOfMouthSectionEntity, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public WordOfMouthAdapter(int layoutResId, int sectionHeadResId, List<WordOfMouthSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    /**
     * 填充标题Item数据
     *
     * @param helper 数据绑定助手
     * @param item   标题数据实体
     */
    @Override
    protected void convertHead(BaseViewHolder helper, WordOfMouthSectionEntity item) {
        helper.setText(R.id.tvTitle, item.header);
        if (item.isSort()) {
            helper.setVisible(R.id.spCourseType, true);
            setSpinner(helper, item);
        } else {
            helper.setGone(R.id.spCourseType, false);
        }
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, WordOfMouthSectionEntity item) {
        WordOfMouthEvaluateEntity evaluateEntity = item.getEvaluateEntity();
        if (evaluateEntity.isEvaluated()) {
            //已经评价
            helper.setBackgroundRes(R.id.llCourse, R.color.listBackground);
            helper.setVisible(R.id.rlStudent, true);
            helper.setVisible(R.id.rlTeacher, true);
            helper.setGone(R.id.btnEvaluate, false);
        } else {
            //未评价
            helper.addOnClickListener(R.id.btnEvaluate);
            helper.setVisible(R.id.btnEvaluate, true);
            helper.setBackgroundRes(R.id.llCourse, R.color.white);
            helper.setGone(R.id.rlStudent, false);
            helper.setGone(R.id.rlTeacher, false);
        }
    }

    private void setSpinner(BaseViewHolder helper, final WordOfMouthSectionEntity item) {
        NiceSpinner spinner = helper.getView(R.id.spCourseType);
        ArrayList<String> data = new ArrayList<>();
        data.add("综合");
        data.add("声乐");
        data.add("钢琴");
        data.add("吉他");
        spinner.attachDataSource(data);
        spinner.setSelectedIndex(item.getIndex());
        spinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item.setIndex(position);
            }
        });
    }
}
