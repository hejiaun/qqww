package cn.vfighter.songlib.api;

import java.util.Collection;

import cn.vfighter.songlib.bean.Song;

/**
 * 歌曲服务接口
 * 
 * @author hanghuideng
 *
 */
public interface ISongApi {
	/**
	 * 添加歌曲
	 * 
	 * @param fileName
	 *            文件名
	 * @param accountId
	 *            上传用户id
	 * @param describe
	 *            说明
	 * @return
	 */
	long addSong(String fileName, long accountId, String describe);

	/**
	 * 删除歌曲
	 * 
	 * @param songId
	 *            歌曲id
	 * @return
	 */
	boolean deleteSong(long songId);

	/**
	 * 根据id获取歌曲
	 * 
	 * @param songId
	 *            歌曲id
	 * @return
	 */
	Song getSongById(long songId);

	/**
	 * 根据歌名关键字获取歌曲
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Song> searchKeyWordSongByName(String keyWord, int pageIndex, int pageLength);

	/**
	 * 根据歌手名关键字获取歌曲
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Song> searchKeyWordSongByOriginal(String keyWord, int pageIndex, int pageLength);

	/**
	 * 根据id获取公开歌曲
	 * 
	 * @param songId
	 *            歌曲id
	 * @return
	 */
	Song getPubSongById(long songId);

	/**
	 * 根据歌名关键字获取公开歌曲
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Song> searchPubKeyWordSongByName(String keyWord, int pageIndex, int pageLength);

	/**
	 * 根据歌手名关键字获取公开歌曲
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Song> searchPubKeyWordSongByOriginal(String keyWord, int pageIndex, int pageLength);

	/**
	 * 下架歌曲
	 * 
	 * @param songId
	 *            歌曲id
	 * @param accountId
	 *            用户id
	 * @return
	 */
	boolean downSong(long songId, long accountId);

	/**
	 * 发布歌曲
	 * 
	 * @param songId
	 *            歌曲id
	 * @param accountId
	 *            用户id
	 * @return
	 */
	boolean publishSong(long songId, long accountId);

	/**
	 * 修改歌曲信息
	 * 
	 * @param songId
	 *            歌曲id
	 * @param songName
	 *            歌曲名
	 * @param album
	 *            专辑名
	 * @param year
	 *            年份
	 * @param artist
	 *            艺术家
	 * @param original
	 *            原唱
	 * @param lyricist
	 *            作词
	 * @param composer
	 *            作曲
	 * @param arranger
	 *            编曲
	 * @param accountId
	 *            用户id
	 * @return
	 */
	boolean updateSongContext(long songId, String songName, String album, String year, String artist, String original,
                              String lyricist, String composer, String arranger, long accountId);

	/**
	 * 修改歌曲文件信息
	 * 
	 * @param songId
	 *            歌曲id
	 * @param state
	 *            状态 -1：已下架，1：可用
	 * @param name
	 *            文件名
	 * @param size
	 *            文件大小 单位M
	 * @param accountId
	 *            修改者
	 * @param codeTtype
	 *            编码类型 1:MP3,2:CD,3:WAV
	 * @param trackTtype
	 *            声道类型 1:2.0声道 ,2:2.1声道 ,3:5.1声道 ,4:7.1声道
	 * @param samplingType
	 *            采样率 1:32000Hz ,2:44100Hz ,3:47250Hz ,4:48000Hz ,5:50000Hz
	 *            ,6:50400Hz ,7:96000,8:192000Hz ,9:28224MHz
	 * @param bpsType
	 *            比特率 1:32kbps 2:96kbps ,3:128 - 160kbps,4:192 kbps,5:224 - 320
	 *            kbps,6:320+kbps
	 * @param duration
	 *            时长 秒为单位
	 * @param describe
	 *            说明
	 * @param FilePath
	 *            http地址
	 * @return
	 */
	boolean updateSongFileContext(long songId, short state, String name, int size, long accountId, short codeTtype,
                                  short trackTtype, short samplingType, short bpsType, int duration, String describe, String filePath);

}
