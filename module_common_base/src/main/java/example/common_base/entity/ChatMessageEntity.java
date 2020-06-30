package example.common_base.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  聊天消息实体(Base父类)
 */
@Entity
public class ChatMessageEntity implements MultiItemEntity {
    //聊天室外键
    Long chatRoomID;
    //自增主键
    @Id
    Long messageID;
    /**
     * 消息内容类型
     */
    int messageContentType;
    final public static int MESSAGE_TYPE_TEXT = 0XFFF3;
    final public static int MESSAGE_TYPE_IMAGE = 0XFFF4;
    final public static int MESSAGE_TYPE_VOICE = 0XFFF5;
    final public static int MESSAGE_TYPE_VIDEO = 0XFFF6;

    /**
     * 消息状态
     */
    int messageStatus = MESSAGE_STATUS_SENT;
    final public static int MESSAGE_STATUS_SENT = 2;
    final public static int MESSAGE_STATUS_SENDING = 0;
    final public static int MESSAGE_STATUS_FAIL = 1;

    /*消息布局类型*/
    int messageLayoutType;
    final public static int DATE = 0XFFFF11;
    final public static int LEFT = 0XFFFF12;
    final public static int RIGHT = 0XFFFF13;
    /**
     * 发送消息的用户ID
     */
    Long fromUserID;

    /**
     * 消息接收对象
     * <p>
     * 群聊，则是群id（groupID#[groupID]）；一对一，聊天对象的唯一标识。
     */
    String taker;

    /**
     * 接收消息的会话
     */
    long sessionTakerID;

    /**
     * 用户头像URL
     */
    String userHeadURL = "";

    /**
     * 消息发送时间
     */
    long sendDate;

    /**
     * 语音文件地址
     */
    String voiceFilePath = null;

    /**
     * 语音时长
     */
    int duration;

    /**
     * 是否正在播放
     */
    boolean isPlaying = false;

    /**
     * 聊天文本消息文本内容
     */
    String textContent = null;

    /**
     * 聊天图片消息图片url
     */
    String imageUrl = "";


    /**
     * @param messageContentType 消息内容类型
     *                           <ul>
     *                           <li>1.文本消息类型   MESSAGE_TYPE_TEXT</li>
     *                           <li>2.图片消息类型   MESSAGE_TYPE_IMAGE</li>
     *                           <li>3.语音消息类型   MESSAGE_TYPE_VOICE</li>
     *                           <li>4.视频消息类型   MESSAGE_TYPE_VIDEO</li>
     *                           </ul>
     * @param messageLayoutType  消息布局类型:
     *                           <ul>
     *                           <li>1.日期布局 DATE</li>
     *                           <li>2.左布局  LEFT</li>
     *                           <li>3.右布局  RIGHT</li>
     *                           </ul>
     * @param fromUserID         发送消息用户ID
     * @param userHeadURL        用户头像URL
     * @param taker              接收消息的对象:
     *                           <ul>
     *                           <li>1.用户 格式：userID#[用户ID]</li>
     *                           <li>2.群 格式：groupID#[群ID]</li>
     *                           </ul>
     * @param sendDate
     */
    public ChatMessageEntity(int messageContentType, int messageLayoutType, Long fromUserID, String taker, String userHeadURL, long sendDate) {
        this.messageContentType = messageContentType;
        this.messageLayoutType = messageLayoutType;
        this.fromUserID = fromUserID;
        this.taker = taker;
        this.sendDate = sendDate;
        this.userHeadURL = userHeadURL;
    }

    /**
     * @param date              消息发送日期
     * @param messageLayoutType
     */
    public ChatMessageEntity(long date, int messageLayoutType) {
        this.sendDate = date;
        this.messageLayoutType = messageLayoutType;
    }

    @Generated(hash = 32747805)
    public ChatMessageEntity(Long chatRoomID, Long messageID, int messageContentType, int messageStatus, int messageLayoutType, Long fromUserID,
                             String taker, long sessionTakerID, String userHeadURL, long sendDate, String voiceFilePath, int duration, boolean isPlaying,
                             String textContent, String imageUrl) {
        this.chatRoomID = chatRoomID;
        this.messageID = messageID;
        this.messageContentType = messageContentType;
        this.messageStatus = messageStatus;
        this.messageLayoutType = messageLayoutType;
        this.fromUserID = fromUserID;
        this.taker = taker;
        this.sessionTakerID = sessionTakerID;
        this.userHeadURL = userHeadURL;
        this.sendDate = sendDate;
        this.voiceFilePath = voiceFilePath;
        this.duration = duration;
        this.isPlaying = isPlaying;
        this.textContent = textContent;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 1666122499)
    public ChatMessageEntity() {
    }


    @Override
    public int getItemType() {
        return messageLayoutType;
    }

    public int getMessageContentType() {
        return messageContentType;
    }

    public void setMessageContentType(int messageContentType) {
        this.messageContentType = messageContentType;
    }

    /**
     * @return messageStatus 消息发送状态
     * <ul>
     * <li>1.已经发送  MESSAGE_STATUS_SENT</li>
     * <li>2.正在发送  MESSAGE_STATUS_SENDING</li>
     * <li>3.发送失败  MESSAGE_STATUS_FAIL</li>
     * </ul>
     */
    public int getMessageStatus() {
        return messageStatus;
    }

    /**
     * @param messageStatus 消息发送状态
     *                      <ul>
     *                      <li>1.已经发送  MESSAGE_STATUS_SENT</li>
     *                      <li>2.正在发送  MESSAGE_STATUS_SENDING</li>
     *                      <li>3.发送失败  MESSAGE_STATUS_FAIL</li>
     *                      </ul>
     */
    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public int getMessageLayoutType() {
        return messageLayoutType;
    }

    public void setMessageLayoutType(int messageLayoutType) {
        this.messageLayoutType = messageLayoutType;
    }

    public Long getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(Long fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getTaker() {
        return taker;
    }

    public void setTaker(String taker) {
        this.taker = taker;
    }

    public String getUserHeadURL() {
        return userHeadURL;
    }

    public void setUserHeadURL(String userHeadURL) {
        this.userHeadURL = userHeadURL;
    }

    public long getSendDate() {
        return sendDate;
    }

    public void setSendDate(long sendDate) {
        this.sendDate = sendDate;
    }

    public String getVoiceFilePath() {
        return voiceFilePath;
    }

    public void setVoiceFilePath(String voiceFilePath) {
        this.voiceFilePath = voiceFilePath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getChatRoomID() {
        return this.chatRoomID;
    }

    public void setChatRoomID(Long chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public Long getMessageID() {
        return this.messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public long getSessionTakerID() {
        return this.sessionTakerID;
    }

    public void setSessionTakerID(long sessionTakerID) {
        this.sessionTakerID = sessionTakerID;
    }
}
