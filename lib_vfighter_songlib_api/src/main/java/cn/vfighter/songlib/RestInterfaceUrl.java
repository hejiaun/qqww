package cn.vfighter.songlib;

/**
 * REST路径定义
 * 
 * @author dhh
 */
public interface RestInterfaceUrl {
	/** 添加歌曲 */
	String songlib_addSong = "service/songlib/addSong";
	/** 删除歌曲 */
	String songlib_deleteSong = "service/songlib/deleteSong";
	/** 根据id获取歌曲 */
	String songlib_getSongById = "service/songlib/getSongById";
	/** 根据歌名关键字获取歌曲 */
	String songlib_searchKeyWordSongByName = "service/songlib/searchKeyWordSongByName";
	/** 根据歌手名关键字获取歌曲 */
	String songlib_searchKeyWordSongByOriginal = "service/songlib/searchKeyWordSongByOriginal";
	/** 根据id获取公开歌曲 */
	String songlib_getPubSongById = "service/songlib/getPubSongById";
	/** 根据歌名关键字获取歌曲 */
	String songlib_searchPubKeyWordSongByName = "service/songlib/searchPubKeyWordSongByName";
	/** 根据歌手名关键字获取歌曲 */
	String songlib_searchPubKeyWordSongByOriginal = "service/songlib/searchPubKeyWordSongByOriginal";
	/** 下架歌曲 */
	String songlib_downSong = "service/songlib/downSong";
	/** 发布歌曲 */
	String songlib_publishSong = "service/songlib/publishSong";
	/** 修改歌曲信息 */
	String songlib_updateSongContext = "service/songlib/updateSongContext";
	/** 修改歌曲文件信息 */
	String songlib_updateSongFileContext = "service/songlib/updateSongFileContext";

	/** 添加伴奏 */
	String songlib_addAccompaniment = "service/songlib/addAccompaniment";
	/** 删除伴奏 */
	String songlib_deleteAccompaniment = "service/songlib/deleteAccompaniment";
	/** 根据id获取伴奏 */
	String songlib_getAccompanimentById = "service/songlib/getAccompanimentById";
	/** 根据歌名关键字获取伴奏 */
	String songlib_searchKeyWordAccompanimentByName = "service/songlib/searchKeyWordAccompanimentByName";
	/** 根据歌手名关键字获取伴奏 */
	String songlib_searchKeyWordAccompanimentByOriginal = "service/songlib/searchKeyWordAccompanimentByOriginal";
	/** 根据id获取公开伴奏 */
	String songlib_getPubAccompanimentById = "service/songlib/getPubAccompanimentById";
	/** 根据歌名关键字获取公开伴奏 */
	String songlib_searchPubKeyWordAccompanimentByName = "service/songlib/searchPubKeyWordAccompanimentByName";
	/** 根据歌手名关键字获取公开伴奏 */
	String songlib_searchPubKeyWordAccompanimentByOriginal = "service/songlib/searchPubKeyWordAccompanimentByOriginal";
	/** 下架伴奏 */
	String songlib_downAccompaniment = "service/songlib/downAccompaniment";
	/** 发布伴奏 */
	String songlib_publishAccompaniment = "service/songlib/publishAccompaniment";
	/** 修改伴奏信息 */
	String songlib_updateAccompanimentContext = "service/songlib/updateAccompanimentContext";
	/** 修改伴奏文件信息 */
	String songlib_updateAccompanimentFileContext = "service/songlib/updateAccompanimentFileContext";

}
