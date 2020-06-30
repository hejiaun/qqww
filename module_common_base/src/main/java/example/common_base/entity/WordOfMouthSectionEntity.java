package example.common_base.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Author: HeJiaJun
 * Description:口碑实体
 */
public class WordOfMouthSectionEntity extends SectionEntity<WordOfMouthEvaluateEntity> {
    boolean isSort = true;
    WordOfMouthEvaluateEntity evaluateEntity = null;
    int index = 0;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public WordOfMouthSectionEntity(String header, boolean isSort) {
        super(true, header);
        this.isSort = isSort;
    }

    public WordOfMouthSectionEntity(WordOfMouthEvaluateEntity evaluateEntity) {
        super(evaluateEntity);
        this.evaluateEntity = evaluateEntity;
    }

    public boolean isSort() {
        return isSort;
    }

    public WordOfMouthEvaluateEntity getEvaluateEntity() {
        return evaluateEntity;
    }
}
