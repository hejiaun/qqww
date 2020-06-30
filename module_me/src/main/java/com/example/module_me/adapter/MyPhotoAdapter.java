package com.example.module_me.adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.module_me.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import example.common_base.entity.MyPhotoEntity;
import example.common_base.util.CustomBaseViewHolder;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyPhotoAdapter extends BaseMultiItemQuickAdapter<MyPhotoEntity, CustomBaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyPhotoAdapter(List<MyPhotoEntity> data) {
        super(data);
        //转发
        addItemType(MyPhotoEntity.FORWORD_WORK, R.layout.item_photo_forward);
        //纯文本
        addItemType(MyPhotoEntity.ONLY_TEXT, R.layout.item_photo_only_text);
        //文本加作品
        addItemType(MyPhotoEntity.TEXT_VIDEO, R.layout.item_photo_video_text);
        //图片加文本
        addItemType(MyPhotoEntity.TEXT_IMAGE, R.layout.item_photo_image_text);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, MyPhotoEntity item) {
        setTimeTab(helper, item.getDate());
        switch (item.getItemType()) {
            case MyPhotoEntity.FORWORD_WORK:
                break;
            case MyPhotoEntity.ONLY_TEXT:
                break;
            case MyPhotoEntity.TEXT_IMAGE:
                break;
            case MyPhotoEntity.TEXT_VIDEO:
                break;
        }
    }

    /**
     * 设置时间标签
     */
    private void setTimeTab(CustomBaseViewHolder helper, Date date) {
        helper.setVisible(R.id.tvDay, false);
        helper.setVisible(R.id.tvMonth, false);
        helper.setVisible(R.id.tvRecent, false);
        long time1 = date.getTime();
        Date date2 = new Date();
        long time2 = date2.getTime();
        int intervalTime = (int) ((time2 - time1) / 1000 / 60 / 60 / 24);
        if (intervalTime < 1) {//今天
            helper.setVisible(R.id.tvRecent, true);
            helper.setText(R.id.tvRecent, "今天");
        } else if (intervalTime == 1) {//昨天
            helper.setVisible(R.id.tvRecent, true);
            helper.setText(R.id.tvRecent, "昨天");
        } else {//直接显示日期
            helper.setVisible(R.id.tvMonth, true);
            helper.setVisible(R.id.tvDay, true);
            helper.setText(R.id.tvDay, new SimpleDateFormat("dd").format(date));
            helper.setText(R.id.tvMonth, new SimpleDateFormat("MM").format(date) + "月");
        }
    }
}
