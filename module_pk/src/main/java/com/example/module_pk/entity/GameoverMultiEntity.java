package com.example.module_pk.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class GameoverMultiEntity implements MultiItemEntity {
    public static final int TITLE = 0xFF1;
    public static final int ITEM_SINGER = 0xFF3;
    public static final int ITEM_RATER = 0xFF4;
    private SingerEntity singerEntity;
    private RaterEntity raterEntity;

    int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }

    public GameoverMultiEntity(int itemType) {
        this.itemType = itemType;
    }

    public GameoverMultiEntity(SingerEntity singerEntity) {
        this(ITEM_SINGER);
        this.singerEntity = singerEntity;

    }

    public GameoverMultiEntity(RaterEntity raterEntity) {
        this(ITEM_RATER);
        this.raterEntity = raterEntity;
    }

    public SingerEntity getSingerEntity() {
        return singerEntity;
    }

    public RaterEntity getRaterEntity() {
        return raterEntity;
    }
}
