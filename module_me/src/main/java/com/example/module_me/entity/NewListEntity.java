package com.example.module_me.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class NewListEntity implements MultiItemEntity {
    int type;
    //--点击评论表情---//
    public final static int TYEP_EMOJI = 0xFF12;

    //--送礼物---//
    public final static int TYPE_PRESENT = 0xFF13;

    //--点评作品---//
    public final static int TYEP_WORK = 0xFF14;

    //---文字评论---//
    public final static int TYPE_FONT = 0xFF15;

    public NewListEntity(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
