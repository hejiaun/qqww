package cn.vfighter.usercenter.param;

public class AddUserFavoritesParam {

	/** 用户帐号ID */
	private long accountId;

	/** 收藏分类 */
	private String category;

	/** 收藏来源模块 */
	private String srcModule;

	/** 收藏内容的标题 */
	private String title;

	/** 收藏的内容目标 */
	private String value;

	/**
	 * 收藏内容的作者
	 */
	private String author;

	/**
	 * 收藏内容作者的头像
	 */
	private String authorImg;

	/**
	 * 获取 用户帐号ID
	 * 
	 * @return accountId 用户帐号ID
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * 设置 用户帐号ID
	 * 
	 * @param accountId
	 *            要设置的 用户帐号ID
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取 收藏的分类
	 * 
	 * @return category 收藏的分类
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 设置 收藏的分类
	 * 
	 * @param category
	 *            要设置的 收藏的分类
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 获取 来源模块
	 * 
	 * @return srcModule 来源模块
	 */
	public String getSrcModule() {
		return srcModule;
	}

	/**
	 * 设置 来源模块
	 * 
	 * @param srcModule
	 *            要设置的 来源模块
	 */
	public void setSrcModule(String srcModule) {
		this.srcModule = srcModule;
	}

	/**
	 * 获取 收藏内容的标题
	 * 
	 * @return title 收藏内容的标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 收藏内容的标题
	 * 
	 * @param title
	 *            要设置的 收藏内容的标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取 收藏内容的目标值
	 * 
	 * @return value 收藏内容的目标值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置 收藏内容的目标值
	 * 
	 * @param value
	 *            要设置的 收藏内容的目标值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取 收藏内容的作者
	 * 
	 * @return author 收藏内容的作者
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 设置 收藏内容的作者
	 * 
	 * @param author
	 *            要设置的 收藏内容的作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 获取 收藏内容作者的头像
	 * 
	 * @return authorImg 收藏内容作者的头像
	 */
	public String getAuthorImg() {
		return authorImg;
	}

	/**
	 * 设置 收藏内容作者的头像
	 * 
	 * @param authorImg
	 *            要设置的 收藏内容作者的头像
	 */
	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}

}
