package cn.vfighter.comment.param;

/**
 * 查询当前音乐圈点赞列表
 * 
 * @author hanghuideng
 *
 */
public class GetMusicCircleGreatListParam {
	/** 音乐圈id */
	private long musicCircleId;
	/** 点赞状态 (-1:取消点赞,1:点赞,2:关心,3:踩) */
	private short state;
	/** 当前页下标 */
	private int pageIndex;
	/** 单页实体个数 */
	private int pageLength;

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
	 * 获取当前下标
	 * 
	 * @return 当前下标
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 设置当前下标
	 * 
	 * @param pageIndex
	 *            当前下标
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 获取单页实体数量
	 * 
	 * @return 单页实体数量
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * 设置单页实体数量
	 * 
	 * @param pageLength
	 *            单页实体数量
	 */
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
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

}
