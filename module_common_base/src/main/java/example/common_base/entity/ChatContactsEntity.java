package example.common_base.entity;

/**
 * Author: HeJiaJun
 * Description: 聊天通讯录实体
 */
public class ChatContactsEntity extends MyMultiplyEntity {
    //头像
    String headURL = null;
    //头像
    int headSrc;
    //名称
    String name;
    //是否有消息
    boolean haveNews;


    public ChatContactsEntity() {
        super(MyMultiplyEntity.CHAT_CONTACTS);
    }

    /**
     * @param headURL  头像链接
     * @param name     名称
     * @param haveNews 是否有消息
     */
    public ChatContactsEntity(String headURL, String name, boolean haveNews) {
        super(MyMultiplyEntity.CHAT_CONTACTS);
        this.headURL = headURL;
        this.name = name;
        this.haveNews = haveNews;
    }

    /**
     * @param headSrc  头像resource
     * @param name     名称
     * @param haveNews 是否有消息
     */
    public ChatContactsEntity(int headSrc, String name, boolean haveNews) {
        super(MyMultiplyEntity.CHAT_CONTACTS);
        this.headSrc = headSrc;
        this.name = name;
        this.haveNews = haveNews;
    }

    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHaveNews() {
        return haveNews;
    }

    public void setHaveNews(boolean haveNews) {
        this.haveNews = haveNews;
    }

    public int getHeadSrc() {
        return headSrc;
    }

    public void setHeadSrc(int headSrc) {
        this.headSrc = headSrc;
    }
}
