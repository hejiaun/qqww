package cn.vfighter.songlib.bean;

/**
 * 伴奏文件实体
 *
 * @author hanghuideng
 */
public class Accompaniment extends AbstractAudio {

    /**
     * 自增长主键
     */
    private long id;

    /**
     * 伴奏名称
     */
    private String accompanimentName;

    /**
     * 原唱
     */
    private String original;

    /**
     * 自增长主键
     *
     * @return 自增长主键
     */
    public long getId() {
        return id;
    }

    /**
     * 自增长主键
     *
     * @param id 自增长主键
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取 伴奏名称
     *
     * @return 伴奏名称
     */
    public String getAccompanimentName() {
        return accompanimentName;
    }

    /**
     * 设置 伴奏名称
     *
     * @param name 伴奏名称
     */
    public void setAccompanimentName(String accompanimentName) {
        this.accompanimentName = accompanimentName;
    }

    /**
     * 获取 原唱
     *
     * @return 原唱
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 设置 原唱
     *
     * @param original 原唱
     */
    public void setOriginal(String original) {
        this.original = original;
    }

}
