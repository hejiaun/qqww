package cn.vfighter.usercenter.param;

public class GetContactInfoParam {
	/** 用户帐号ID */
	private long accountId;

	/** 联系方式类型 */
	private String contactType;

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
	 * 获取 联系方式类型
	 * 
	 * @return contactType 联系方式类型
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * 设置 联系方式类型
	 * 
	 * @param contactType
	 *            要设置的 联系方式类型
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

}
