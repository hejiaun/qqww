package com.example.module_home.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_home.R;

import java.util.List;

import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.TitleMultiplyentity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  主页搜索列表适配器
 */
public class SearchSectionAdapter extends BaseMultiItemQuickAdapter<MyMultiplyEntity, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SearchSectionAdapter(List<MyMultiplyEntity> data) {
        super(data);
        //布局绑定
        addItemType(MyMultiplyEntity.TITLE, R.layout.view_section_head);
        addItemType(MyMultiplyEntity.ACCOMPANIENT, R.layout.item_song_practice);
        addItemType(MyMultiplyEntity.CHORUS, R.layout.item_chorus);
        addItemType(MyMultiplyEntity.COURSE, R.layout.item_course);
        addItemType(MyMultiplyEntity.MATCH, R.layout.item_match);
        addItemType(MyMultiplyEntity.USER, R.layout.item_person_play);
        addItemType(MyMultiplyEntity.WORK, R.layout.item_mywork);
        addItemType(MyMultiplyEntity.TEACHER, R.layout.item_teacher);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, MyMultiplyEntity item) {
        switch (helper.getItemViewType()) {
            case MyMultiplyEntity.TITLE://标题
                helper.setText(R.id.tvTitle, ((TitleMultiplyentity) item).getTitle());
                helper.addOnClickListener(R.id.tvMore);
                helper.setVisible(R.id.tvMore, ((TitleMultiplyentity) item).isMore());
                break;
            case MyMultiplyEntity.ACCOMPANIENT://伴奏
                helper.setGone(R.id.btn_select, false);
                break;
            case MyMultiplyEntity.USER://用户
                break;
            case MyMultiplyEntity.MATCH://比赛
                break;
            case MyMultiplyEntity.WORK://作品
                break;
            case MyMultiplyEntity.CHORUS://合唱
                break;
            case MyMultiplyEntity.COURSE://课程
                break;
            case MyMultiplyEntity.TEACHER:///老师
                break;
        }
    }
}
