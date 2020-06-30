package example.common_base.entity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:评论实体
 */
public class CommentEntity {
    //时间
    String data;

    public CommentEntity(String content, String userName, String userHeadUrl) {
        this.content = content;
        this.userName = userName;
        this.userHeadUrl = userHeadUrl;
    }

    //内容
    String content;
    //用户名称
    String userName;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }
    //用户id

    //用户头像
    String userHeadUrl;


}
