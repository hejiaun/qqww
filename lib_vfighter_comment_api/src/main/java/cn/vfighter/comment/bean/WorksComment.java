package cn.vfighter.comment.bean;

/**
 * 作品评论实体
 * 
 * @author hanghuideng
 *
 */
public class WorksComment extends AbstractComment {
	/** 作品id */
	private long worksId;

	/**
	 * 获取作品id
	 * 
	 * @return
	 */
	public long getWorksId() {
		return worksId;
	}

	/**
	 * 设置作品id
	 * 
	 * @param worksId
	 */
	public void setWorksId(long worksId) {
		this.worksId = worksId;
	}

}
