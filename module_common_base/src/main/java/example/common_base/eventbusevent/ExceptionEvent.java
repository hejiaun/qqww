package example.common_base.eventbusevent;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:异常的EventBus事件
 */
public class ExceptionEvent {
    int exceptionCode = 0;

    public ExceptionEvent(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}
