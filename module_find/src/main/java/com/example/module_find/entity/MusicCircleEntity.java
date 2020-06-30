package com.example.module_find.entity;

import java.util.ArrayList;

import example.common_base.entity.UserEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:音乐圈实体
 */
public class MusicCircleEntity {

    ArrayList<UserEntity> comment;

    public void setComment(ArrayList<UserEntity> comment) {
        this.comment = comment;
    }

    public ArrayList<UserEntity> getComment() {
        return comment;
    }
}
