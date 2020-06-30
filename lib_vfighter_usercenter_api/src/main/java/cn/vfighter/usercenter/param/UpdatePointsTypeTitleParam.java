package cn.vfighter.usercenter.param;

public class UpdatePointsTypeTitleParam {
	private long id;
	private String title;

	/**
	 * 获取 ID，自增编号
	 * 
	 * @return id 自增编号
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置 自增编号
	 * 
	 * @param id
	 *            要设置的 自增编号
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取 积分类型标题
	 * 
	 * @return title 积分类型标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 积分类型标题
	 * 
	 * @param title
	 *            要设置的 积分类型标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
