package example.common_base.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:对战记录实体
 */
public class FightRecordVideoEntity implements MultiItemEntity {
    public final static int PLAYER = 0Xfff1;//选手
    public final static int RATER = 0Xfff2;//评委
    public int type = PLAYER;

    public FightRecordVideoEntity(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
