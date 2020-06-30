package cn.vfighter.songlib.param;

/**
 * 发布伴奏实体
 * 
 * @author hanghuideng
 *
 */
public class PublishAccompanimentParam {
	/** 伴奏id */
	private long accompanimentId;

	/** 用户Id */
	private long accountId;

	/**
	 * 获取伴奏id
	 * 
	 * @return 歌曲id
	 */
	public long getAccompanimentId() {
		return accompanimentId;
	}

	/**
	 * 设置伴奏id
	 * 
	 * @param accompanimentId
	 *            歌曲id
	 */
	public void setAccompanimentId(long accompanimentId) {
		this.accompanimentId = accompanimentId;
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
