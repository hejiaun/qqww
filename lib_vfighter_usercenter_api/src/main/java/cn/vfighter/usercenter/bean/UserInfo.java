/* Copyright (c) vfighter.cn, All Rights Reserved
 *             ____     __    __
 *   _  ______/ __/____/ / __/ /_________
 *  | |/ /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | / / / / / /__  / // // /_/ ___/ /
 *  |__/_/ /_/  __/ /_//_/ \__/\___/_/
 *             \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.usercenter.bean;

import java.util.Date;

/**
 * 用户信息持久化实体
 *
 * @author konlg
 */
public class UserInfo {

    /** ID，自增编号 */
    private long id;

    /** 用户帐号ID */
    private long accountId;

    /** 昵称 */
    private String nickName;

    /** 年龄(可以自动计算) */
    private int age;

    /** 生日 */
    private Date birthday;

    /** 身高(cm) */
    private int height;

    /** 性格描述 */
    private String nature;

    /** 自我描述 */
    private String desc;

    /** 星座 */
    private int constellation;

    /** 状态 */
    private int state;

    /** 用户头像 */
    private String photoUrl;

    /** 性别 */
    private int sex;

    /** 电影 */
    private String movie;

    /** 食物 */
    private String food;

    /**
     * 获取 id
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id
     *            要设置的 id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取 帐号ID
     *
     * @return accountId 帐号ID
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * 设置 帐号ID
     *
     * @param accountId
     *            要设置的 帐号ID
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取 昵称
     *
     * @return nickName 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置 昵称
     *
     * @param nickName
     *            要设置的 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取 年龄
     *
     * @return age 年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置 年龄
     *
     * @param age
     *            要设置的 年龄
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取 出生日期
     *
     * @return birthday 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置 出生日期
     *
     * @param birthday
     *            要设置的 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取 身高(cm)
     *
     * @return height 身高(cm)
     */
    public int getHeight() {
        return height;
    }

    /**
     * 设置 身高(cm)
     *
     * @param height
     *            要设置的 身高(cm)
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 获取 性格描述
     *
     * @return nature 性格描述
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置 性格描述
     *
     * @param nature
     *            要设置的 性格描述
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * 获取 自我描述
     *
     * @return desc 自我描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置 自我描述
     *
     * @param desc
     *            要设置的 自我描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 返回星座
     *
     * @return 星座
     */
    public int getConstellation() {
        return constellation;
    }

    /**
     * 设置星座
     *
     * @param constellation
     *            要设置的 星座
     */
    public void setConstellation(int constellation) {
        this.constellation = constellation;
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public int getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state
     *            要设置的 状态
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取头像路径
     *
     * @return 头像路径
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 设置头像路径
     *
     * @param photoUrl
     *            头像路径
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取性别
     *
     * @return 性别
     */
    public int getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex
     *            性别
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 获取电影
     *
     * @return 电影
     */
    public String getMovie() {
        return movie;
    }

    /**
     * 设置电影
     *
     * @param movie
     *            电影
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }

    /**
     * 获取食物
     *
     * @return 食物
     */
    public String getFood() {
        return food;
    }

    /**
     * 设置食物
     *
     * @param food
     *            食物
     */
    public void setFood(String food) {
        this.food = food;
    }

}
