package cn.vfighter.comment.bean;

/**
 * 音乐圈评论实体
 * 
 * @author hanghuideng
 *
 */
public class MusicCircleComment extends AbstractComment {
	/** 音乐圈id */
	private long musicCircleId;

	/**
	 * 获取音乐圈id
	 * 
	 * @return
	 */
	public long getMusicCircleId() {
		return musicCircleId;
	}

	/**
	 * 设置音乐圈id
	 * 
	 * @param musicCircle
	 */
	public void setMusicCircleId(long musicCircleId) {
		this.musicCircleId = musicCircleId;
	}

}
