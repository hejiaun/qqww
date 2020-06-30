package cn.vfighter.songlib.param;

/**
 * 更新伴奏信息参数实体
 * 
 * @author hanghuideng
 *
 */
public class UpdateAccompanimentContextParam {
	/** 主键 */
	private long id;

	/** 伴奏名称 */
	private String accompanimentName;

	/** 原唱 */
	private String original;

	/** 修改者 */
	private long accountId;

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

	/**
	 * 获取 伴奏名称
	 * 
	 * @return 歌曲名称
	 */
	public String getAccompanimentName() {
		return accompanimentName;
	}

	/**
	 * 设置 伴奏名称
	 * 
	 * @param name
	 *            歌曲名称
	 */
	public void setAccompanimentName(String accompanimentName) {
		this.accompanimentName = accompanimentName;
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
	 * 获取修改者id
	 * 
	 * @return 修改者id
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置修改者id
	 * 
	 * @param accountId
	 *            修改者id
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
