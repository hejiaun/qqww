package example.common_base.eventbusevent;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MusicCircleCommentEvent {
    int position = -1;

    public MusicCircleCommentEvent(int position) {
        this.position = position;
    }
    public int getPosition(){
        return position;
    }

}
