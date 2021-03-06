package cn.vfighter.usercenter.param;

public class UserPointsParam {
	private long accountId;

	private int code;

	private int value;

	/**
	 * 获取用户ID
	 * 
	 * @return 用户ID
	 * 
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置用户ID
	 * 
	 * @param accountId
	 *            用户ID
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取 积分类型编码
	 * 
	 * @return code 积分类型编码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置 积分类型编码
	 * 
	 * @param code
	 *            要设置的 积分类型编码
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 获取积分值
	 * 
	 * @return 积分值
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 设置积分值
	 * 
	 * @param value
	 *            积分值
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
