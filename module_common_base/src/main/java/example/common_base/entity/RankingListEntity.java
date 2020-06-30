package example.common_base.entity;

public class RankingListEntity {

    //排名
    int Rank;
    //排位用户头像
    String rankUserHead;
    //名师用户头像
    String teacherUserHead;
    //单曲用户头像
    String songUserHead;
    //评委用户头像
    String raterUserHead;
    //人气用户头像
    String propularity;

    public RankingListEntity(int rank, String rankUserHead, String teacherUserHead, String songUserHead, String raterUserHead, String propularity) {
        Rank = rank;
        this.rankUserHead = rankUserHead;
        this.teacherUserHead = teacherUserHead;
        this.songUserHead = songUserHead;
        this.raterUserHead = raterUserHead;
        this.propularity = propularity;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getRankUserHead() {
        return rankUserHead;
    }

    public void setRankUserHead(String rankUserHead) {
        this.rankUserHead = rankUserHead;
    }

    public String getTeacherUserHead() {
        return teacherUserHead;
    }

    public void setTeacherUserHead(String teacherUserHead) {
        this.teacherUserHead = teacherUserHead;
    }

    public String getSongUserHead() {
        return songUserHead;
    }

    public void setSongUserHead(String songUserHead) {
        this.songUserHead = songUserHead;
    }

    public String getRaterUserHead() {
        return raterUserHead;
    }

    public void setRaterUserHead(String raterUserHead) {
        this.raterUserHead = raterUserHead;
    }

    public String getPropularity() {
        return propularity;
    }

    public void setPropularity(String propularity) {
        this.propularity = propularity;
    }

}
