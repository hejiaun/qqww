package cn.vfighter.comment;

/**
 * 评论服务REST路径定义
 * 
 * @author hanghuideng
 *
 */
public interface RestInterfaceUrl {
	/** 添加作品评论 */
	String comment_addWorksComment = "service/comment/addWorksComment";
	/** 回复作品评论 */
	String comment_returnWorksComment = "service/comment/returnWorksComment";
	/** 删除作品评论 */
	String comment_deleteWorksComment = "service/comment/deleteWorksComment";
	/** 统计作品评论个数 */
	String comment_countWorksCommentByWorksId = "service/comment/countWorksCommentByWorksId";
	/** 查询作品评论列表 */
	String comment_getWorksCommentList = "service/comment/getWorkscommentList";

	/** 添加音乐圈评论 */
	String comment_addMusicCircleComment = "service/comment/addMusicCircleComment";
	/** 回复音乐圈评论 */
	String comment_returnMusicCircleComment = "service/comment/returnMusicCircleComment";
	/** 删除音乐圈评论 */
	String comment_deleteMusicCircleComment = "service/comment/deleteMusicCircleComment";
	/** 统计音乐圈评论个数 */
	String comment_countMusicCircleCommentByMusicCircleId = "service/comment/countMusicCircleCommentByMusicCircleId";
	/** 查询音乐圈评论列表 */
	String comment_getMusicCircleCommentList = "service/comment/getMusicCircleCommentList";

	/** 创建点赞 */
	String comment_addMusicCircleGreat = "service/comment/addMusicCircleGreat";
	/** 取消点赞 */
	String comment_deleteMusicCircleGreat = "service/comment/deleteMusicCircleGreat";
	/** 查看当前音乐圈个人点赞情况 */
	String comment_getMusicCircleGreatView = "service/comment/getMusicCircleGreatView";
	/** 查询当前音乐圈点赞个数 */
	String comment_countMusicCircleGreat = "service/comment/countMusicCircleGreat";
	/** 查询当前音乐圈点赞列表 */
	String comment_getMusicCircleGreatList = "service/comment/getMusicCircleGreatList";

}
