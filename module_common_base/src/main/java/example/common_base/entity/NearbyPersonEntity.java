package example.common_base.entity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  附近的用户列表实体
 */
public class NearbyPersonEntity {

    //名称
    String name;
    //距离
    long distence;
    //性别
    int sex;
    //头像
    int head;

    /**
     * @param name     用户名称
     * @param distence 用户距离
     * @param sex      用户性别
     * @param head
     */
    public NearbyPersonEntity(String name, long distence, int sex, int head) {
        this.name = name;
        this.distence = distence;
        this.sex = sex;
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistence() {
        return distence;
    }

    public void setDistence(long distence) {
        this.distence = distence;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }
}
