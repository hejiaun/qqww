package cn.vfighter.usercenter.param;

/**
 * 检测地址标签是否存在参数实体
 * 
 * @author hanghuideng
 *
 */
public class GetAddressByLabelParam {
	/** 用户帐号ID */
	private long accountId;
	/** 地址标签 */
	private String label;

	/**
	 * 获取 帐号ID
	 * 
	 * @return accountId 帐号ID
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置 帐号ID
	 * 
	 * @param accountId
	 *            要设置的 帐号ID
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取 地址标签
	 * 
	 * @return label 地址标签
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 设置 地址标签
	 * 
	 * @param label
	 *            要设置的 地址标签
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
