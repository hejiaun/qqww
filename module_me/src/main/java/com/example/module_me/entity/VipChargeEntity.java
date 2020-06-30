package com.example.module_me.entity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 会员充值实体
 */
public class VipChargeEntity {
    //--租期---//
    int tenancy = -1;

    //--原价--//
    int originalPrice = -1;

    //--折扣---//
    int discount = -1;

    //--价钱---//
    int price = -1;

    /**
     * @param tenancy       租期
     * @param originalPrice 原价
     * @param discount      折扣
     * @param price         折扣价
     */
    public VipChargeEntity(int tenancy, int originalPrice, int discount, int price) {
        this.tenancy = tenancy;
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.price = price;
    }

    public int getTenancy() {
        return tenancy;
    }

    public void setTenancy(int tenancy) {
        this.tenancy = tenancy;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int original) {
        this.originalPrice = original;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
