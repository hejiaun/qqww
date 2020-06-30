package cn.vfighter.comment.api;

import java.util.Collection;

import cn.vfighter.comment.bean.MusicCircleComment;

/**
 * 音乐圈评论服务接口
 * 
 * @author hanghuideng
 *
 */
public interface IMusicCricleCommentApi extends ICommentApi {
	/**
	 * 根据作品id获取评论
	 * 
	 * @param musicCircleId
	 *            音乐圈Id
	 * @param pageIndex
	 *            当前页面下标
	 * @param pageLength
	 *            页面信息条数
	 * @return
	 */
	Collection<MusicCircleComment> getCommentByMusicCircleId(long musicCircleId, int pageIndex, int pageLength);

}
