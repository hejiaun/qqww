package com.example.module_chat.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;

import java.util.List;

import example.common_base.entity.ChatSessionEntity;
import example.common_base.util.TimeUtil;

/**
 * 聊天记录列表适配器
 */
public class ChatSessionListAdapter extends BaseQuickAdapter<ChatSessionEntity, CustomBaseViewHolder> {
    /**
     * 构造方法
     *
     * @param layoutResId 列表Item布局
     * @param data        列表数据集合
     */
    public ChatSessionListAdapter(int layoutResId, @Nullable List<ChatSessionEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, ChatSessionEntity item) {
        helper.setNineGridGroupHead(R.id.iv9, item.getGroupHeads());
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvContent, item.getContent());
        helper.setText(R.id.tvTime, TimeUtil.getInstence().secondTime2String((item.getTime()) / 1000));
        if (item.isSetTop()) {
            helper.setBackgroundRes(R.id.llItem, R.drawable.selector_shallowgray2deepgray);
        } else {
            helper.setBackgroundRes(R.id.llItem, R.drawable.selector_white2fontgray);
        }
    }
}
