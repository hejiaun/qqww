package cn.vfighter.comment.bean;

/**
 * 音乐圈点赞实体
 * 
 * @author hanghuideng
 *
 */
public class MusicCircleGreat {
	/** 主键 */
	private long id;
	/** 点赞状态 (-1:取消点赞,1:点赞,2:关心,3:踩) */
	private short state;
	/** 音乐圈id */
	private long musicCircleId;
	/** 点赞用户id */
	private long accountId;

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取点赞状态
	 * 
	 * @return 点赞状态
	 */
	public short getState() {
		return state;
	}

	/**
	 * 设置点赞状态
	 * 
	 * @param state
	 *            设置点赞状态
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * 获取音乐圈id
	 * 
	 * @return 音乐圈id
	 */
	public long getMusicCircleId() {
		return musicCircleId;
	}

	/**
	 * 设置音乐圈id
	 * 
	 * @param musicCircleId
	 *            音乐圈id
	 */
	public void setMusicCircleId(long musicCircleId) {
		this.musicCircleId = musicCircleId;
	}

	/**
	 * 获取 用户id
	 * 
	 * @return 用户id
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置 用户id
	 * 
	 * @return 用户id
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
