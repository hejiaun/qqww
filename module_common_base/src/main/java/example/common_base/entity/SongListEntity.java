package example.common_base.entity;

public class SongListEntity {

    /**
     * 歌曲名
     */
    String songName;
    /**
     * 歌曲文件大小
     */
    String songSize;
    /**
     * 歌手
     */
    String singer;
    /**
     * 歌曲封面
     */
    int coverImage;

    public SongListEntity(String songName, String songSize, String singer, int coverImage, int star) {
        this.songName = songName;
        this.songSize = songSize;
        this.singer = singer;
        this.coverImage = coverImage;
        this.star = star;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongSize() {
        return songSize;
    }

    public void setSongSize(String songSize) {
        this.songSize = songSize;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    /**
     * 星级
     */
    int star;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    /**
     * 是否被选中
     */
    boolean isSelect = false;
}
