package cn.vfighter.songlib.bean;

/**
 * 歌曲实体
 * 
 * @author hanghuideng
 *
 */
public class Song extends AbstractAudio {

	/** 自增长主键 */
	private long id;

	/** 歌曲名称 */
	private String songName;

	/** 所属专辑 */
	private String album;

	/** 年份 */
	private String year;

	/** 艺术家 */
	private String artist;

	/** 原唱 */
	private String original;

	/** 作词 */
	private String lyricist;

	/** 作曲 */
	private String composer;

	/** 编曲 */
	private String arranger;

	/**
	 * 获取自增长主键
	 * 
	 * @return 自增长主键
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置自增长主键
	 * 
	 * @param id
	 *            自增长主键
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取 歌曲名称
	 * 
	 * @return 歌曲名称
	 */
	public String getSongName() {
		return songName;
	}

	/**
	 * 设置 歌曲名称
	 * 
	 * @param name
	 *            歌曲名称
	 */
	public void setSongName(String songName) {
		this.songName = songName;
	}

	/**
	 * 获取 艺术家
	 * 
	 * @return 艺术家
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * 设置 艺术家
	 * 
	 * @param artist
	 *            艺术家
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * 获取 所属专辑
	 * 
	 * @return 所属专辑
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * 设置 所属专辑
	 * 
	 * @param album
	 *            所属专辑
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * 获取 作词
	 * 
	 * @return 作词
	 */
	public String getLyricist() {
		return lyricist;
	}

	/**
	 * 设置作词
	 * 
	 * @param lyricist
	 *            作词
	 */
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}

	/**
	 * 获取 作曲
	 * 
	 * @return 作曲
	 */
	public String getComposer() {
		return composer;
	}

	/**
	 * 设置 作曲
	 * 
	 * @param composer
	 *            作曲
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}

	/**
	 * 获取 编曲
	 * 
	 * @return 编曲
	 */
	public String getArranger() {
		return arranger;
	}

	/**
	 * 设置 编曲
	 * 
	 * @param arranger
	 *            编曲
	 */
	public void setArranger(String arranger) {
		this.arranger = arranger;
	}

	/**
	 * 获取 原唱
	 * 
	 * @return 原唱
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * 设置 原唱
	 * 
	 * @param original
	 *            原唱
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * 获取 年份
	 * 
	 * @return 年份
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 设置 年份
	 * 
	 * @param year
	 *            年份
	 */
	public void setYear(String year) {
		this.year = year;
	}

}
