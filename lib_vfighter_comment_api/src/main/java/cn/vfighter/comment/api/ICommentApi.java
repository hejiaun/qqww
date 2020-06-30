package cn.vfighter.comment.api;

/**
 * 评论通用业务实体接口
 * 
 * @author hanghuideng
 *
 */
public interface ICommentApi {
	/**
	 * 添加评论
	 * 
	 * @param target
	 *            评论目标id
	 * @param CommentContent
	 *            评论内容
	 * @param accountId
	 *            用户id
	 * @return
	 */
	long addComment(long target, String commentContent, long accountId);

	/**
	 * 回复评论
	 * 
	 * @param target
	 *            评论目标id
	 * @param CommentContent
	 *            评论内容
	 * @param accountId
	 *            用户id
	 * @param beReturnAccountId
	 *            被回复用户id
	 * @return
	 */
	long returnComment(long target, String commentContent, long accountId, long returnAccountId);

	/**
	 * 删除评论
	 * 
	 * @param commentId
	 *            评论id
	 * @return
	 */
	boolean deleteCircleComment(long commentId);

	/**
	 * 根据评论目标统计评论个数
	 * 
	 * @param target
	 *            音乐圈Id
	 * @return
	 */
	long countCommentByTargetId(long target);

}
