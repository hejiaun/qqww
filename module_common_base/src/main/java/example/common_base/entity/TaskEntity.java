package example.common_base.entity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:任务列表实体
 */
public class TaskEntity {
    //是否已经领取
    public boolean receivce;

    public boolean isReceivce() {
        return receivce;
    }

    public void setReceivce(boolean receivce) {
        this.receivce = receivce;
    }

    //签到任务
    public final static int SIGN_TASK = 0xfff1;

    //分享任务
    public final static int SHARE_TASK = 0XFFF2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFlower() {
        return flower;
    }

    public void setFlower(int flower) {
        this.flower = flower;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSignDay() {
        return signDay;
    }

    public void setSignDay(int signDay) {
        this.signDay = signDay;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    //对战任务
    public final static int FIGHT_TASK = 0XFFF3;

    //评价任务
    public final static int EVALUATE_TASK = 0XFFF4;

    //任务类型
    int type;

    //花的收益数量
    int flower;

    //经验的收益数量
    int experience;

    //标题
    String title;

    //连续签到的天数
    int signDay;

    //是否完成
    boolean isFinish;

    public TaskEntity(boolean isFinish, boolean isReceive, int type, String title) {
        this.isFinish = isFinish;
        this.receivce = isReceive;
        this.type = type;
        this.title = title;
    }


}

