package com.example.module_pk.entity;

import java.util.ArrayList;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class GroupEntity {
    String tag;
    int count;
    ArrayList<SingerEntity> entities;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<SingerEntity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<SingerEntity> entities) {
        this.entities = entities;
    }
}
