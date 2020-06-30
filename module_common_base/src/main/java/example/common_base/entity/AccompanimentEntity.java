package example.common_base.entity;

import cn.vfighter.songlib.bean.Accompaniment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  伴奏实体
 */
public class AccompanimentEntity extends MyMultiplyEntity {

    Accompaniment data = null;

    public AccompanimentEntity() {
        super(ACCOMPANIENT);
    }

    public AccompanimentEntity(Accompaniment data) {
        super(ACCOMPANIENT);
        this.data = data;
    }

    public Accompaniment getData() {
        return data;
    }

    public void setData(Accompaniment data) {
        this.data = data;
    }
}
