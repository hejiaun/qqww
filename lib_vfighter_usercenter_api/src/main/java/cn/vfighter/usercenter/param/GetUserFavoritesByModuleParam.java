package cn.vfighter.usercenter.param;

public class GetUserFavoritesByModuleParam {
	/** 用户ID */
	private long accountId;

	/** 收藏分类 */
	private String srcModule;
	/** 页面下标 */
	private int pageIndex;
	/** 每页大小 */
	private int pageLength;

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
	 * 获取来源模块
	 * 
	 * @return 来源模块
	 */
	public String getSrcModule() {
		return srcModule;
	}

	/**
	 * 设置来源模块
	 * 
	 * @param srcModule
	 *            来源模块
	 */
	public void setSrcModule(String srcModule) {
		this.srcModule = srcModule;
	}

	/**
	 * 获取页面下标
	 * 
	 * @return 页面下标
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 设置页面下标
	 * 
	 * @param pageIndex
	 *            页面下标
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 获取每页大小
	 * 
	 * @return 每页大小
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * 设置每页大小
	 * 
	 * @param pageLength
	 *            每页大小
	 */
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

}
