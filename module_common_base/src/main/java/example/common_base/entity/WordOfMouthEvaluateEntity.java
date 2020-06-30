package example.common_base.entity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:口碑评论实体
 */
public class WordOfMouthEvaluateEntity {
    boolean isEvaluated = false;

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        isEvaluated = evaluated;
    }

    public WordOfMouthEvaluateEntity(boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
    }
}
