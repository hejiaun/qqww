package example.common_base.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.Date;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:相册实体
 */
public class MyPhotoEntity implements MultiItemEntity {
    private int itemType;
    //相册纯文本类型
    public static final int ONLY_TEXT = 0XFFF11;
    //相册文本加图片类型
    public static final int TEXT_VIDEO = 0XFFF12;
    //相册文本加歌曲类型
    public static final int TEXT_IMAGE = 0XFFF13;
    //相册转载其他人作品类型
    public static final int FORWORD_WORK = 0XFFF14;

    //相册发送日期
    Date date;

    public MyPhotoEntity(int itemType, Date date) {
        this.itemType = itemType;
        this.date = date;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
