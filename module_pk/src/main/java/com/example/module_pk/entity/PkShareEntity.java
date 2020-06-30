package com.example.module_pk.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PkShareEntity implements MultiItemEntity {
    int type;
    boolean isSelect = true;
    public static final int GROUP_TYPE = 0xfff12;
    public static final int PERSON_TYPE = 0xfff13;
    public SingerEntity singerEntity;

    public PkShareEntity(int type) {
        this.type = type;
    }

    public PkShareEntity(SingerEntity entity) {
        this(PERSON_TYPE);
        this.singerEntity = entity;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public SingerEntity getSingerEntity() {
        return singerEntity;
    }
}
