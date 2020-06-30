package example.common_base.entity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  课程实体
 */
public class CourseEntity {
    int num = 0;

    /**
     * 价格
     */
    int price = 0;

    public CourseEntity(int price, int duration, String courseName) {
        this.price = price;
        this.duration = duration;
        this.courseName = courseName;
    }

    public CourseEntity() {

    }

    /**
     * 时长
     */
    int duration = 0;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 课程名称
     */
    String courseName = null;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
