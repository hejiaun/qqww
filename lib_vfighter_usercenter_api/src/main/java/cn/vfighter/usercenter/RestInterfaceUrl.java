package cn.vfighter.usercenter;

/**
 * REST路径定义
 *
 * @author dhh
 */
public interface RestInterfaceUrl {
    /** 按照ID检测积分类型是否存在 */
    String usercenter_existsPointsTypeById = "service/usercenter/existsPointsTypeById";

    /** 检测积分类型编码是否已经存在 */
    String usercenter_existsPointsTypeByCode = "service/usercenter/existsPointsTypeByCode";

    /** 检测积分类型标题是否已经存在 */
    String usercenter_existsPointsTypeByTittle = "service/usercenter/existsPointsTypeByTittle";

    /** 添加一个积分类型 */
    String usercenter_addPointsType = "service/usercenter/addPointsType";

    /** 更新积分类型标题 */
    String usercenter_updatePointsTypeTitle = "service/usercenter/updatePointsTypeTitle";

    /** 更新积分类型的说明 */
    String usercenter_updatePointsTypeDesc = "service/usercenter/updatePointsTypeDesc";

    /** 按照ID删除积分类型 */
    String usercenter_deletePointsTypeById = "service/usercenter/deletePointsTypeById";

    /** 按照编码删除积分类型 */
    String usercenter_deletePointsTypeByCode = "service/usercenter/deletePointsTypeByCode";

    /** 按照ID获取积分类型 */
    String usercenter_getPointsTypeById = "service/usercenter/getPointsTypeById";

    /** 按照编码获取积分类型 */
    String usercenter_getPointsTypeByCode = "service/usercenter/getPointsTypeByCode";

    /** 获取所有积分类型 */
    String usercenter_getAllPointsType = "service/usercenter/getAllPointsType";

    /** 为某一类型增加1积分 */
    String usercenter_addUserPoints = "service/usercenter/addUserPoints";

    /** 为某一类型增加指定积分值 */
    String usercenter_addUserPointsSome = "service/usercenter/addUserPointsSome";

    /** 为某一类型减少1积分 */
    String usercenter_subtractUserPoints = "service/usercenter/subtractUserPoints";

    /** 为某一类型减少制定积分值 */
    String usercenter_subtractUserPointsSome = "service/usercenter/subtractUserPointsSome";

    /** 获取某一类型的用户积分值 */
    String usercenter_getUserPoints = "service/usercenter/getUserPoints";

    /** 获取用户的所有类型的积分值列表 */
    String usercenter_getAllUserPoints = "service/usercenter/getAllUserPoints";

    /** 增加用户收藏 */
    String usercenter_addUserUserFavorites = "service/usercenter/addUserUserFavorites";

    /** 根据ID获取用户收藏 */
    String usercenter_getUserFavorite = "service/usercenter/getUserFavorite";

    /** 获取用户收藏列表 */
    String usercenter_getUserFavorites = "service/usercenter/getUserFavorites";

    /** 按照分类获取收藏列表 */
    String usercenter_getUserFavoritesByCategory = "service/usercenter/getUserFavoritesByCategory";

    /** 按照模块获取收藏列表 */
    String usercenter_getUserFavoritesByModule = "service/usercenter/getUserFavoritesByModule";

    /** 获取用户资料 */
    String usercenter_getUserInfo = "service/usercenter/getUserInfo";

    /** 创建用户资料(用户注册时，从帐号模块调用) */
    String usercenter_createUserInfo = "service/usercenter/createUserInfo";

    /** 更新昵称 */
    String usercenter_updateNickname = "service/usercenter/updateNickname";

    /** 更新岁数 */
    String usercenter_updateAge = "service/usercenter/updateAge";

    /** 更新生日 */
    String usercenter_updateBirthday = "service/usercenter/updateBirthday";

    /** 更新身高 */
    String usercenter_updateHeight = "service/usercenter/updateHeight";

    /** 更新体重 */
    String usercenter_updateWeight = "service/usercenter/updateWeight";

    /** 更新其他 */
    String usercenter_updateOther = "service/usercenter/updateOther";

    /** 更新性格描述 */
    String usercenter_updateNature = "service/usercenter/updateNature";

    /** 更新自我说明 */
    String usercenter_updateDesc = "service/usercenter/updateDesc";

    /** 更新星座 */
    String usercenter_updateConstellation = "service/usercenter/updateConstellation";

    /** 更新状态 */
    String usercenter_updateState = "service/usercenter/updateState";

    /** 更新头像路径 */
    String usercenter_updatePhotoUrl = "service/usercenter/updatePhotoUrl";

    /** 更新性别 */
    String usercenter_updateSex = "service/usercenter/updateSex";

    /** 更新电影 */
    String usercenter_updateMovie = "service/usercenter/updateMovie";

    /** 更新食物 */
    String usercenter_updateFood = "service/usercenter/updateFood";

    /** 更新用户资料 */
    String usercenter_updateUserInfo = "service/usercenter/updateUserInfo";

    /** 获取所有联系方式类型 */
    String usercenter_getAllContactType = "service/usercenter/getAllContactType";

    /** 检测联系方式类型是否存在 */
    String usercenter_existsContactInfo = "service/usercenter/existsContactInfo";

    /** 获取指定类型的联系方式 */
    String usercenter_getContactInfo = "service/usercenter/getContactInfo";

    /** 获取用户的所有联系方式 */
    String usercenter_getAllContactInfo = "service/usercenter/getAllContactInfo";

    /** 添加用户的联系方式 */
    String usercenter_addContactInfo = "service/usercenter/addContactInfo";

    /** 删除用户的联系方式 */
    String usercenter_deleteContactInfo = "service/usercenter/deleteContactInfo";

    /** 检测地址标签是否存在 */
    String usercenter_existsAddressLabel = "service/usercenter/existsAddressLabel";

    /** 获取指定的地址 */
    String usercenter_getAddress = "service/usercenter/getAddress";

    /** 获取用户的所有地址 */
    String usercenter_getAllAddress = "service/usercenter/getAllAddress";

    /** 获取用户的指定标签地址 */
    String usercenter_getAddressByLabel = "service/usercenter/getAddressByLabel";

    /** 添加地址 */
    String usercenter_addAddress = "service/usercenter/addAddress";

    /** 删除用户的联系方式 */
    String usercenter_deleteAddress = "service/usercenter/deleteAddress";
}
