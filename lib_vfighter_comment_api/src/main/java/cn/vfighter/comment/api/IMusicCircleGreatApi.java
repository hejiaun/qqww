package cn.vfighter.comment.api;

import java.util.Collection;

import cn.vfighter.comment.bean.MusicCircleGreat;

/**
 * 音乐圈点赞服务持久化接口
 * 
 * @author hanghuideng
 *
 */
public interface IMusicCircleGreatApi {
	/**
	 * 创建点赞
	 * 
	 * @param musicCircleId
	 *            音乐圈id
	 * @param accountId
	 *            用户id
	 * @return
	 */
	long addMusicCircleGreat(long musicCircleId, long accountId, short state);

	/**
	 * 取消点赞
	 * 
	 * @param musicCircleGreatId
	 *            点赞id
	 * 
	 * @return
	 */
	boolean deleteMusicCircleGreat(long musicCircleGreatId);

	/**
	 * 查看当前音乐圈个人点赞情况
	 * 
	 * @param musicCircleId
	 *            音乐圈id
	 * 
	 * @param accountId
	 *            用户id
	 * @return
	 */
	MusicCircleGreat getMusicCircleGreat(long musicCircleId, long accountId);

	/**
	 * 统计当前音乐圈点赞个数
	 * 
	 * @param musicCircleId
	 *            音乐圈id
	 * 
	 * @return
	 */
	long countMusicCircleGreat(long musicCircleId);

	/**
	 * 查询当前音乐圈点赞列表
	 * 
	 * @param musicCircleId
	 *            音乐圈id
	 * @param pageIndex
	 *            当前页面下标
	 * @param pageLength
	 *            页面信息条数
	 * @return
	 */
	Collection<MusicCircleGreat> getMusicCircleGreatList(long musicCircleId, short state, int pageIndex,
			int pageLength);

}
