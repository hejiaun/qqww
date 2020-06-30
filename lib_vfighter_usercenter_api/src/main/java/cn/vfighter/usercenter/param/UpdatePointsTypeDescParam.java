package cn.vfighter.usercenter.param;

public class UpdatePointsTypeDescParam {
	private long id;
	private String description;

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
	 * 获取 积分类型的说明
	 * 
	 * @return description 积分类型的说明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 积分类型的说明
	 * 
	 * @param description
	 *            要设置的 积分类型的说明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
