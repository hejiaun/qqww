package com.example.module_me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_me.R;

import java.util.List;

import example.common_base.entity.MyWorkEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的作品列表适配器
 */
public class MyWorkListAdapter extends BaseQuickAdapter<MyWorkEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表布局id
     * @param data        列表布局数据集合
     */
    public MyWorkListAdapter(int layoutResId, @Nullable List<MyWorkEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, MyWorkEntity item) {
        //填充数据
//        helper.setText(R.id.iv_songFace, item.getLevel());
        helper.setText(R.id.tv_songName, item.getSongName());
        helper.setText(R.id.tv_singer, item.getSinger());
        helper.setText(R.id.tv_size, item.getSize());
        helper.addOnClickListener(R.id.ivMore);
    }


}
