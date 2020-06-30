package example.common_base.entity;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  多重类型列表的标题实体
 */
public class TitleMultiplyentity extends MyMultiplyEntity {
    private String title = null;
    private boolean isMore = false;

    public TitleMultiplyentity() {
        super(MyMultiplyEntity.TITLE);
    }

    /**
     * @param title  标题
     * @param isMore 是否有更多
     */
    public TitleMultiplyentity(String title, boolean isMore) {
        super(MyMultiplyEntity.TITLE);
        this.isMore = isMore;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
