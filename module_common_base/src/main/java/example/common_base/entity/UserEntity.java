package example.common_base.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  用户实体
 */
@Entity
public class UserEntity {
    /**
     * 用户头像链接
     */
    String headURL;

    @Unique
    @Id
    Long userID;

    //头像
    @Transient
    int headImage;

    //等级
    public UserEntity(int headImage, String name) {
        this.headImage = headImage;
        this.name = name;
    }

    public UserEntity() {

    }

    @Generated(hash = 794270987)
    public UserEntity(String headURL, Long userID, String name) {
        this.headURL = headURL;
        this.userID = userID;
        this.name = name;
    }

    //名称
    String name;

    public int getHeadImage() {
        return headImage;
    }

    public void setHeadImage(int headImage) {
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //段位
    //星级
    //

    public String getHeadURL() {
        return this.headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


}
