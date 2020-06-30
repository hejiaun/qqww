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

package cn.vfighter.usercenter.bean;

/**
 * 联系方式实体
 * 
 * @author hanghuideng
 *
 */
public class AddressBook {

	/** ID，自增编号 */
	private long id;

	/** 用户帐号ID */
	private long accountId;

	/** 地区编码 */
	private String districtCode;

	/** 地址标签 */
	private String label;

	/** 详细地址 */
	private String detail;

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置
	 * 
	 * @param id
	 *            要设置的 id
	 */
	public void setId(long id) {
		this.id = id;
	}

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
	 * 获取 地区编码
	 * 
	 * @return districtCode 地区编码
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * 设置 地区编码
	 * 
	 * @param districtCode
	 *            要设置的 地区编码
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
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

	/**
	 * 获取 详细地址
	 * 
	 * @return detail 详细地址
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置 详细地址
	 * 
	 * @param detail
	 *            要设置的 详细地址
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
