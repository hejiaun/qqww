package com.example.module_pk.entity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 弹幕实体
 */
public class BarrageEntity {

    String content = null;

    public BarrageEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
