package cn.vfighter.songlib.param;

/**
 * 发布歌曲实体
 * 
 * @author hanghuideng
 *
 */
public class PublishSongParam {
	/** 歌曲id */
	private long songId;

	/** 用户Id */
	private long accountId;

	/**
	 * 获取歌曲id
	 * 
	 * @return 歌曲id
	 */
	public long getSongId() {
		return songId;
	}

	/**
	 * 设置歌曲id
	 * 
	 * @param songId
	 *            歌曲id
	 */
	public void setSongId(long songId) {
		this.songId = songId;
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
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
