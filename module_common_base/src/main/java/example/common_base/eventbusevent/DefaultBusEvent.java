package example.common_base.eventbusevent;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:默认的EventBus事件
 */
public class DefaultBusEvent {
    public static final int USER_INFORMATION = 0xfff123;
    int type;
    int msgInt;
    String msgString;

    public int getType() {
        return type;
    }

    public DefaultBusEvent(int type) {
        this.type = type;
    }

    public String getMsgString() {
        return msgString;
    }

    public void setMsgString(String msgString) {
        this.msgString = msgString;
    }

    public int getMsgInt() {
        return msgInt;
    }

    public void setMsgInt(int msg) {
        this.msgInt = msg;
    }
}
