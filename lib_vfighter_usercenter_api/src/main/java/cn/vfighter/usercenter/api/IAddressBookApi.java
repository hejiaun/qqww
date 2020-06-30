package cn.vfighter.usercenter.api;

import java.util.Collection;

import cn.vfighter.usercenter.bean.AddressBook;

/**
 * 用户联系方式服务接口
 *
 * @author hanghuideng
 */

public interface IAddressBookApi {
    /**
     * 检测地址标签是否存在
     *
     * @param accountId 用户帐号ID
     * @param label     联系方式类型
     * @return
     */
    boolean existsAddressLabel(long accountId, String label);

    /**
     * 获取指定的地址
     *
     * @param addressId 地址ID
     * @return
     */
    AddressBook getAddress(long addressId);

    /**
     * 获取用户的所有地址
     *
     * @param accountId 用户帐号ID
     * @return
     */
    Collection<AddressBook> getAllAddress(long accountId);

    /**
     * 获取用户的指定标签地址
     *
     * @param accountId 用户帐号ID
     * @param label     地址标签
     * @return
     */
    Collection<AddressBook> getAddressByLabel(long accountId, String label);

    /**
     * 添加地址
     *
     * @param accountId    用户帐号ID
     * @param districtCode 地区编码
     * @param label        地址标签
     * @param detail       详细地址
     * @return
     */
    long addAddress(long accountId, String districtCode, String label, String detail);

    /**
     * 删除用户的联系方式
     *
     * @param addressId 地址ID
     * @return
     */
    boolean deleteAddress(long addressId);

}
