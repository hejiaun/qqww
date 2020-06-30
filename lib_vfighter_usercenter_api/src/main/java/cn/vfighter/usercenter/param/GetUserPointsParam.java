package cn.vfighter.usercenter.param;

public class GetUserPointsParam {
	private long accountId;

	private int code;

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
}
