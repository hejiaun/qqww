package cn.vfighter.usercenter.param;

/**
 * 检测地址标签是否存在参数实体
 * 
 * @author hanghuideng
 *
 */
public class ExistsAddressLabelParam {
	/** 用户帐号ID */
	private long accountId;
	/** 地址标签 */
	private String label;

	/**
	 * 获取账号id
	 * 
	 * @return 账号id
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置 账号id
	 * 
	 * @param label
	 *            账号id
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
