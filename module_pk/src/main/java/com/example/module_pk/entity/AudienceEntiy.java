package com.example.module_pk.entity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AudienceEntiy {
    String name;
    int headResource;

    public AudienceEntiy(String name, int headResource) {
        this.name = name;
        this.headResource = headResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadResource() {
        return headResource;
    }

    public void setHeadResource(int headResource) {
        this.headResource = headResource;
    }
}
