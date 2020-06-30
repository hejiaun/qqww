package example.common_base.entity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的作品实体
 */
public class MyWorkEntity {
    //封面
    String songFaceUrl = null;
    //歌曲名
    String songName;
    //歌手
    String singer;
    //歌曲大小
    String size;
    //发布时间
    String time;


    /**
     * @param songFaceUrl 作品封面url
     * @param songName    歌曲名称
     * @param singer      歌手
     * @param size        歌曲文件大小
     * @param time        发布时间
     */
    public MyWorkEntity(String songFaceUrl, String songName, String singer, String size, String time) {
        this.songFaceUrl = songFaceUrl;
        this.songName = songName;
        this.singer = singer;
        this.size = size;
        this.time = time;
    }

    public String getSongFaceUrl() {
        return songFaceUrl;
    }

    public void setSongFaceUrl(String songFaceUrl) {
        this.songFaceUrl = songFaceUrl;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
