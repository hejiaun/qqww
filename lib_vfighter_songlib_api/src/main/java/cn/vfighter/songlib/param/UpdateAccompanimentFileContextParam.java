package cn.vfighter.songlib.param;

/**
 * 更新伴奏文件信息参数实体
 * 
 * @author hanghuideng
 *
 */
public class UpdateAccompanimentFileContextParam {
	/** 主键 */
	private long id;

	/** 状态 -1：已下架，1：可用 */
	private short state;

	/** 文件名 */
	private String fileName;

	/** 文件大小 单位M */
	private int size;

	/** 修改者 */
	private long updateAccountid;

	/** 编码类型 1:MP3,2:CD,3:WAV */
	private short codeTtype;

	/** 声道类型 1:2.0声道 ,2:2.1声道 ,3:5.1声道 ,4:7.1声道 */
	private short trackTtype;

	/**
	 * 采样率 1:32000Hz ,2:44100Hz ,3:47250Hz ,4:48000Hz ,5:50000Hz ,6:50400Hz
	 * ,7:96000,8:192000Hz ,9:28224MHz
	 */
	private short samplingType;

	/**
	 * 比特率 1:32kbps 2:96kbps ,3:128 - 160kbps,4:192 kbps,5:224 - 320
	 * kbps,6:320+kbps
	 */
	private short bpsType;

	/** 时长 秒为单位 */
	private int duration;

	/** 说明 */
	private String describe;

	/** http地址 */
	private String FilePath;

	/**
	 * 获取 状态 -1：已下架，1：可用
	 * 
	 * @return 状态 -1：已下架，1：可用
	 */
	public short getState() {
		return state;
	}

	/**
	 * 设置状态 -1：已下架，1：可用
	 * 
	 * @param state
	 *            状态 -1：已下架，1：可用
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * 获取 文件名
	 * 
	 * @return 文件名
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置 文件名
	 * 
	 * @param name
	 *            文件名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取 文件大小 单位M
	 * 
	 * @return 文件大小 单位M
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 设置 文件大小 单位M
	 * 
	 * @param size
	 *            文件大小 单位M
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 获取 修改者
	 * 
	 * @return 修改者
	 */
	public long getUpdateAccountid() {
		return updateAccountid;
	}

	/**
	 * 设置 修改者
	 * 
	 * @param updateAccountid
	 *            修改者
	 */
	public void setUpdateAccountid(long updateAccountid) {
		this.updateAccountid = updateAccountid;
	}

	/**
	 * 获取 编码类型 1:MP3,2:CD,3:WAV
	 * 
	 * @return 编码类型 1:MP3,2:CD,3:WAV
	 */
	public short getCodeTtype() {
		return codeTtype;
	}

	/**
	 * 设置 编码类型 1:MP3,2:CD,3:WAV
	 * 
	 * @param codeTtype
	 *            编码类型 1:MP3,2:CD,3:WAV
	 */
	public void setCodeTtype(short codeTtype) {
		this.codeTtype = codeTtype;
	}

	/**
	 * 获取 声道类型 1:2.0声道 ,2:2.1声道 ,3:5.1声道 ,4:7.1声道
	 * 
	 * @return 声道类型 1:2.0声道 ,2:2.1声道 ,3:5.1声道 ,4:7.1声道
	 */
	public short getTrackTtype() {
		return trackTtype;
	}

	/**
	 * 设置 声道类型 1:2.0声道 ,2:2.1声道 ,3:5.1声道 ,4:7.1声道
	 * 
	 * @param trackTtype
	 *            声道类型 1:2.0声道 ,2:2.1声道 ,3:5.1声道 ,4:7.1声道
	 */
	public void setTrackTtype(short trackTtype) {
		this.trackTtype = trackTtype;
	}

	/**
	 * 获取采样率1:32000Hz ,2:44100Hz ,3:47250Hz ,4:48000Hz ,5:50000Hz ,6:50400Hz
	 * ,7:96000,8:192000Hz ,9:28224MHz
	 * 
	 * @return 采样率1:32000Hz ,2:44100Hz ,3:47250Hz ,4:48000Hz ,5:50000Hz
	 *         ,6:50400Hz ,7:96000,8:192000Hz ,9:28224MHz
	 */
	public short getSamplingType() {
		return samplingType;
	}

	/**
	 * 设置 采样率1:32000Hz ,2:44100Hz ,3:47250Hz ,4:48000Hz ,5:50000Hz ,6:50400Hz
	 * ,7:96000,8:192000Hz ,9:28224MHz
	 * 
	 * @param samplingType
	 *            采样率1:32000Hz ,2:44100Hz ,3:47250Hz ,4:48000Hz ,5:50000Hz
	 *            ,6:50400Hz ,7:96000,8:192000Hz ,9:28224MHz
	 */
	public void setSamplingType(short samplingType) {
		this.samplingType = samplingType;
	}

	/**
	 * 获取 比特率1:32kbps 2:96kbps ,3:128 - 160kbps,4:192 kbps,5:224 - 320
	 * kbps,6:320+kbps
	 * 
	 * @return 比特率1:32kbps 2:96kbps ,3:128 - 160kbps,4:192 kbps,5:224 - 320
	 *         kbps,6:320+kbps
	 */
	public short getBpsType() {
		return bpsType;
	}

	/**
	 * 设置 比特率 1:32kbps 2:96kbps ,3:128 - 160kbps,4:192 kbps,5:224 - 320
	 * kbps,6:320+kbps
	 * 
	 * @param bpsType
	 *            比特率 1:32kbps 2:96kbps ,3:128 - 160kbps,4:192 kbps,5:224 - 320
	 *            kbps,6:320+kbps
	 */
	public void setBpsType(short bpsType) {
		this.bpsType = bpsType;
	}

	/**
	 * 获取 时长 秒为单位
	 * 
	 * @return 时长
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * 设置 时长 秒为单位
	 * 
	 * @param duration
	 *            时长 秒为单位
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * 获取说明
	 * 
	 * @return 说明
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 设置说明
	 * 
	 * @param describe
	 *            说明
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * 获取http地址
	 * 
	 * @return http地址
	 */
	public String getFilePath() {
		return FilePath;
	}

	/**
	 * 设置http地址
	 * 
	 * @param filePath
	 *            http地址
	 */
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(long id) {
		this.id = id;
	}
}
