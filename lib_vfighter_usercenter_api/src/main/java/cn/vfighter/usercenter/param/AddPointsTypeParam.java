/* Copyright (c) vfighter.cn, All Rights Reserved
 *             ____     __    __
 *   _  ______/ __/____/ / __/ /_________
 *  | |/ /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | / / / / / /__  / // // /_/ ___/ /
 *  |__/_/ /_/  __/ /_//_/ \__/\___/_/
 *             \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.usercenter.param;

/**
 * 添加积分类型参数
 * 
 * @author konlg
 */
public class AddPointsTypeParam {
	private int code;
	private int weight;
	private String title;
	private String description;

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
	 * 获取 积分比例权重
	 * 
	 * @return weight 积分比例权重
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * 设置 积分比例权重
	 * 
	 * @param weight
	 *            要设置的 积分比例权重
	 */
	public void setWeight(int weight) {
		this.weight = weight;
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
