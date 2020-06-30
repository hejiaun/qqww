package com.example.module_chat.entity;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 通讯录好友实体
 */
public class DirectoryFriendEntity {
    //--未关注他---//
    public final static int STATE_UNFOLLOW = 1;
    //--相互关注---//
    public final static int STATE_FOLLOW_EACHOTHER = 2;
    //--我已关注他---//
    public final static int STATE_FOLLOW = 3;

    private int followState;

    public int getFollowState() {
        return followState;
    }

    public void setFollowState(int followState) {
        this.followState = followState;
    }

    public DirectoryFriendEntity() {

    }

    public DirectoryFriendEntity(int state) {
        this.followState = state;
    }
}
