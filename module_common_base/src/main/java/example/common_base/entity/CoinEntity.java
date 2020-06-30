package example.common_base.entity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  音乐币实体
 */
public class CoinEntity {
    /**
     * 音乐币数量
     */
    int musicCoinNum;
    /**
     * 人民币
     */
    int RMB;

    public CoinEntity(int musicCoinNum, int RMB) {
        this.musicCoinNum = musicCoinNum;
        this.RMB = RMB;
    }

    public int getMusicCoinNum() {
        return musicCoinNum;
    }

    public void setMusicCoinNum(int musicCoinNum) {
        this.musicCoinNum = musicCoinNum;
    }

    public int getRMB() {
        return RMB;
    }

    public void setRMB(int RMB) {
        this.RMB = RMB;
    }
}
