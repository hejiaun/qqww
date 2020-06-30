package cn.vfighter.comment.param;

/**
 * 查询个人点赞情况实体
 * 
 * @author hanghuideng
 *
 */
public class GetMusicCircleGreatViewParam {
	/** 音乐圈id */
	private long musicCircleId;
	/** 用户id */
	private long accountId;

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
	 * 获取用户id
	 * 
	 * @return 用户id
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置用户id
	 * 
	 * @param accountId
	 *            用户id
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
}
