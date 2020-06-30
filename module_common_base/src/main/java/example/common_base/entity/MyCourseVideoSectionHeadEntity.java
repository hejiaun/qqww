package example.common_base.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 课堂录像列表Section实体
 */
public class MyCourseVideoSectionHeadEntity extends SectionEntity<CourseVideoHeadEntity> {

    public MyCourseVideoSectionHeadEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyCourseVideoSectionHeadEntity(CourseVideoHeadEntity courseVideoHeadEntity) {
        super(courseVideoHeadEntity);
    }
}
