package example.common_base.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的多重类型列表实体(Base父类)
 */
public class MyMultiplyEntity implements MultiItemEntity {
    private int itemType;
    //布局类型
    //标题
    public static final int TITLE = 0XFFF1;
    //伴奏
    public static final int ACCOMPANIENT = 0XFFF2;
    //用户
    public static final int USER = 0XFFF3;
    //比赛
    public static final int MATCH = 0XFFF4;
    //作品
    public static final int WORK = 0XFFF5;
    //合唱
    public static final int CHORUS = 0XFFF6;
    //老师
    public static final int TEACHER = 0XFFF7;
    //课程
    public static final int COURSE = 0XFFF9;
    //聊天通讯录
    public static final int CHAT_CONTACTS = 0XFFF10;


    /**
     * 获取item类型
     *
     * @return
     */
    @Override
    public int getItemType() {
        return itemType;
    }

    public MyMultiplyEntity(int itemType) {
        this.itemType = itemType;
    }


}