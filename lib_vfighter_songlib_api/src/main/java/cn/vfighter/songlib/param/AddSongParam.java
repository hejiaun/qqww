package cn.vfighter.songlib.param;

/**
 * 添加歌曲实体
 * 
 * @author hanghuideng
 *
 */
public class AddSongParam {

	/** 文件名 */
	private String fileName;

	/** 创建者 */
	private long accountId;

	/** 说明 */
	private String describe;

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
	 * 获取 账户id
	 * 
	 * @return 账户id
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置 账户id
	 * 
	 * @param accountId
	 *            账户id
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
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

}
