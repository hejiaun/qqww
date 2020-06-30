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

package cn.vfighter.usercenter.api;

import java.util.Collection;

import cn.vfighter.usercenter.bean.PointsType;

/**
 * 积分类型管理接口
 * 
 * @author konlg
 */
public interface IPointsTypeApi {
	/**
	 * 按照ID检测积分类型是否存在
	 * 
	 * @param id
	 *            积分类型ID
	 * @return 是否存在
	 */
	boolean existsPointsType(long id);

	/**
	 * 检测积分类型编码是否已经存在
	 * 
	 * @param code
	 *            积分类型编码
	 * @return
	 */
	boolean existsPointsTypeByCode(int code);

	/**
	 * 检测积分类型标题是否已经存在
	 * 
	 * @param title
	 *            积分类型标题
	 * @return
	 */
	boolean existsPointsTypeByTitle(String title);

	/**
	 * 添加一个积分类型
	 * 
	 * @param code
	 *            积分类型编码
	 * @param weight
	 *            积分比例权重
	 * @param title
	 *            积分类型标题
	 * @param module
	 *            处理该积分类型的模块名称
	 * @param description
	 *            积分类型的说明
	 * @return 积分类型的编号
	 */
	long addPointsType(int code, int weight, String title, String description);

	/**
	 * 更新积分类型标题
	 * 
	 * @param id
	 *            积分类型ID
	 * @param title
	 *            积分类型标题
	 * @return 是否成功
	 */
	boolean updatePointsTypeTitle(long id, String title);

	/**
	 * 更新积分类型的说明
	 * 
	 * @param id
	 *            积分类型ID
	 * @param description
	 *            积分类型说明
	 * @return 是否成功
	 */
	boolean updatePointsTypeDesc(long id, String description);

	/**
	 * 按照ID删除积分类型
	 * 
	 * @param id
	 *            积分类型ID
	 * @return 是否成功
	 */
	boolean deletePointsTypeById(long id);

	/**
	 * 按照编码删除积分类型
	 * 
	 * @param code
	 *            积分类型编码
	 * @return 是否成功
	 */
	boolean deletePointsTypeByCode(int code);

	/**
	 * 按照ID获取积分类型
	 * 
	 * @param id
	 *            积分类型ID
	 * @return 积分类型持久化实体
	 */
	PointsType getPointsTypeById(long id);

	/**
	 * 按照编码获取积分类型
	 * 
	 * @param code
	 *            积分类型编码
	 * @return 积分类型持久化实体
	 */
	PointsType getPointsTypeByCode(int code);

	/**
	 * 获取所有积分类型
	 * 
	 * @return 积分类型集合
	 */
	Collection<PointsType> getAllPointsType();
}
