package com.example.module_practice.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.common_base.entity.UserEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 上传伴奏歌曲列表适配器
 */
public class UploadAccompanimentAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public UploadAccompanimentAdapter(int layoutResId, @Nullable List data) {
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
//        helper.setGone(R.id.btn_select, false);
//        helper.setText(R.id.btn_sing, "上传");
    }

}
