package example.common_base.entity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  课程名称
 */
public class StudyCourseEntity {
    private String courseType;
    private String teacher;
    private String leave;
    private String musicCoin;
    private String status;
    private String date;

    public StudyCourseEntity() {

    }

    /**
     * @param courseType 课程类型
     * @param teacher    授课老师
     * @param level      课程等级
     * @param musicCoin  课程学费
     * @param status     课程状态
     * @param date       课程日期
     */
    public StudyCourseEntity(String courseType, String teacher, String level, String musicCoin, String status, String date) {
        this.courseType = courseType;
        this.teacher = teacher;
        this.leave = level;
        this.musicCoin = musicCoin;
        this.status = status;
        this.date = date;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLevel() {
        return leave;
    }

    public void setLevel(String leave) {
        this.leave = leave;
    }

    public String getMusicCoin() {
        return musicCoin;
    }

    public void setMusicCoin(String musicCoin) {
        this.musicCoin = musicCoin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
