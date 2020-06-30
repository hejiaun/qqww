package cn.vfighter.comment.param;

/**
 * 添加评论参数实体
 * 
 * @author hanghuideng
 *
 */
public class AddCommentParam {
	/** 评论目标id */
	private long id;
	/** 评论 */
	private String commentContent;
	/** 评论用户id */
	private long accountId;

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
	 * @param tagId
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
	 * 获取评论用户
	 * 
	 * @return
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * . 设置评论用户
	 * 
	 * @param accountId
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
