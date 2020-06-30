package cn.vfighter.comment.param;

/**
 * 回复评论参数实体
 * 
 * @author hanghuideng
 *
 */
public class ReturnCommentParam {
	/** 评论目标id */
	private long id;
	/** 评论 */
	private String commentContent;
	/** 回复用户id */
	private long accountId;
	/** 被回复用户id */
	private long returnAccountId;

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
	 * 获取评论内容
	 * 
	 * @return
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * 设置评论内容
	 * 
	 * @param commentContent
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	/**
	 * 获取回复用户
	 * 
	 * @return
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * . 设置回复用户
	 * 
	 * @param accountId
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获得被回复用户id
	 * 
	 * @return
	 */
	public long getReturnAccountId() {
		return returnAccountId;
	}

	/**
	 * 设置被回复用户id
	 * 
	 * @return
	 */
	public void setReturnAccountId(long returnAccountId) {
		this.returnAccountId = returnAccountId;
	}

}
