package example.common_base.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;

import example.common_base.convertor.StringlistConvertToStringConvertor;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  对话列表实体
 */
@Entity
public class ChatSessionEntity implements Comparable<ChatSessionEntity> {

    /**
     * 设置顶置的时间
     */
    @Transient
    Long setTopTime;

    @Transient
    boolean isSetTop = false;

    /**
     * 对话类型
     * <ul>
     * <li>1.群聊天:   0</li>
     * <li>2.一对一聊天: 1</li>
     * </ul>
     */
    int type;

    /**
     * 对话id
     */
    @Id(autoincrement = true)
    Long sessionID;
    /**
     * 对话标题
     */
    String name;

    /**
     * 对话最新接收的消息的发送时间
     */
    long time;

    /**
     * 对话最新消息简略内容
     */
    String content;
    /**
     * 对话用户头像的集合
     */
    @Convert(converter = StringlistConvertToStringConvertor.class, columnType = String.class)
    ArrayList<String> groupHeads;

//    /**
//     * 对话所有用户id的集合
//     */
////    @Transient
//    ArrayList<Integer> userIds;

    /**
     * @param name       聊天记录标题
     * @param content    聊天记录最后更新的缩略内容
     * @param time       聊天记录最后更新时间
     * @param groupHeads 聊天记录头像集合
     * @param type       对话类型:
     *                   <ul>
     *                   <li>1.群聊天</li>
     *                   <li>2.一对一聊天</li>
     *                   </ul>
     */
    public ChatSessionEntity(String name, String content, long time, ArrayList<String> groupHeads, int type) {
        this.groupHeads = groupHeads;
        this.name = name;
        this.content = content;
        this.time = time;
        this.type = type;
    }


    @Generated(hash = 1948211865)
    public ChatSessionEntity(int type, Long sessionID, String name, long time, String content,
                             ArrayList<String> groupHeads) {
        this.type = type;
        this.sessionID = sessionID;
        this.name = name;
        this.time = time;
        this.content = content;
        this.groupHeads = groupHeads;
    }


    @Generated(hash = 1506236656)
    public ChatSessionEntity() {
    }

    public void setSetTopTime(Long setTopTime) {
        this.setTopTime = setTopTime;
    }

    public Long getSetTopTime() {
        return setTopTime;
    }

    public boolean isSetTop() {
        return isSetTop;
    }

    public void setSetTop(boolean isSetTop) {
        this.isSetTop = isSetTop;
    }

    public ArrayList<String> getGroupHeads() {
        return groupHeads;
    }

    public void setGroupHeads(ArrayList<String> groupHeads) {
        this.groupHeads = groupHeads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Long getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(Long sessionID) {
        this.sessionID = sessionID;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public boolean getIsSetTop() {
        return this.isSetTop;
    }


    public void setIsSetTop(boolean isSetTop) {
        this.isSetTop = isSetTop;
    }


    @Override
    public int compareTo(ChatSessionEntity o) {
        if (isSetTop) {//如果是顶置对话集合
            return (int) (getSetTopTime() - o.getSetTopTime());
        } else {//如果是普通对话集合
            return (int) (getTime() - o.getTime());
        }
    }
}
