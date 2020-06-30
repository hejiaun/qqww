package cn.vfighter.comment.param;

/**
 * 获取评论列表
 * 
 * @author hanghuideng
 *
 */
public class GetCommentListParam {
	/** 目标id */
	private long id;
	/** 当前页下标 */
	private int pageIndex;
	/** 单页实体数量 */
	private int pageLength;

	/**
	 * 获取评论目标id
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置评论目标id
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取当前下标
	 * 
	 * @return
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 设置当前下标
	 * 
	 * @param pageIndex
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 获取单页实体数量
	 * 
	 * @return
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * 设置单页实体数量
	 * 
	 * @param pageLength
	 */
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

}
