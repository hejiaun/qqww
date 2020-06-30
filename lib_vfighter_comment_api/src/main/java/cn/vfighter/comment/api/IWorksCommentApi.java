package cn.vfighter.comment.api;

import java.util.Collection;

import cn.vfighter.comment.bean.WorksComment;

public interface IWorksCommentApi extends ICommentApi {
	/**
	 * 根据作品id获取评论
	 * 
	 * @param worksId
	 *            作品Id
	 * @param pageIndex
	 *            当前页面下标
	 * @param pageLength
	 *            页面信息条数
	 * @return
	 */
	Collection<WorksComment> getCommentByWorksId(long worksId, int pageIndex, int pageLength);

}
