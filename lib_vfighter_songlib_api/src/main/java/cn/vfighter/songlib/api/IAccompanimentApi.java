package cn.vfighter.songlib.api;

import java.util.Collection;

import cn.vfighter.songlib.bean.Accompaniment;

/**
 * 伴奏服务接口
 * 
 * @author hanghuideng
 *
 */
public interface IAccompanimentApi {
	/**
	 * 添加伴奏
	 * 
	 * @param fileName
	 *            文件名
	 * @param accountId
	 *            上传用户id
	 * @param describe
	 *            说明
	 * @return
	 */
	long addAccompaniment(String fileName, long accountId, String describe);

	/**
	 * 删除伴奏
	 * 
	 * @param accompanimentId
	 *            伴奏id
	 * @return
	 */
	boolean deleteAccompaniment(long accompanimentId);

	/**
	 * 根据id获取伴奏
	 * 
	 * @param accompanimentId
	 *            伴奏id
	 * @return
	 */
	Accompaniment getAccompanimentById(long accompanimentId);

	/**
	 * 根据歌名关键字获取伴奏
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Accompaniment> searchKeyWordAccompanimentByName(String keyWord, int pageIndex, int pageLength);

	/**
	 * 根据歌手名关键字获取伴奏
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Accompaniment> searchKeyWordAccompanimentByOriginal(String keyWord, int pageIndex, int pageLength);

	/**
	 * 根据id获取公开伴奏
	 * 
	 * @param accompanimentId
	 *            伴奏id
	 * @return
	 */
	Accompaniment getPubAccompanimentById(long accompanimentId);

	/**
	 * 根据歌名关键字获取公开伴奏
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Accompaniment> searchPubKeyWordAccompanimentByName(String keyWord, int pageIndex, int pageLength);

	/**
	 * 根据歌手名关键字获取公开伴奏
	 * 
	 * @param keyWord
	 *            关键字
	 * @param pageIndex
	 *            分页页码
	 * @param pageLength
	 *            每页大小
	 * @return
	 */
	Collection<Accompaniment> searchPubKeyWordAccompanimentByOriginal(String keyWord, int pageIndex, int pageLength);

	/**
	 * 下架伴奏
	 * 
	 * @param accompanimentId
	 *            伴奏id
	 * @param accountId
	 *            用户id
	 * @return
	 */
	boolean downAccompaniment(long accompanimentId, long accountId);

	/**
	 * 发布伴奏
	 * 
	 * @param accompanimentId
	 *            伴奏id
	 * @param accountId
	 *            用户id
	 * @return
	 */
	boolean publishAccompaniment(long accompanimentId, long accountId);

	/**
	 * 修改伴奏信息
	 * 
	 * @param accompanimentId
	 *            伴奏id
	 * @param accompanimentName
	 *            伴奏名
	 * @param original
	 *            原唱
	 * @param accountId
	 *            用户id
	 * @return
	 */
	boolean updateAccompanimentContext(long accompanimentId, String accompanimentName, String original, long accountId);

	/**
	 * 修改伴奏文件信息
	 * 
	 * @param accompanimentId
	 *            伴奏id
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
	boolean updateAccompanimentFileContext(long accompanimentId, short state, String name, int size, long accountId,
                                           short codeTtype, short trackTtype, short samplingType, short bpsType, int duration, String describe,
                                           String filePath);

}
