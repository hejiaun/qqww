package com.example.module_pk.entity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class SingerEntity {
    String name;
    int head;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public SingerEntity(String name, int head) {
        this.name = name;
        this.head = head;
    }
}
