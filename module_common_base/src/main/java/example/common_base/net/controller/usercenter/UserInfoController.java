package example.common_base.net.controller.usercenter;


import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.usercenter.bean.UserInfo;
import cn.vfighter.usercenter.param.UpdateUserInfoParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.usercenter.CreateUserInfoExecutor;
import example.common_base.net.executor.usercenter.GetUserInfoExecutor;
import example.common_base.net.executor.usercenter.UpdateAgeExecutor;
import example.common_base.net.executor.usercenter.UpdateBirthdayExecutor;
import example.common_base.net.executor.usercenter.UpdateConstellationExecutor;
import example.common_base.net.executor.usercenter.UpdateDescExecutor;
import example.common_base.net.executor.usercenter.UpdateFoodExecutor;
import example.common_base.net.executor.usercenter.UpdateHeadImgUrlExecutor;
import example.common_base.net.executor.usercenter.UpdateHeightExecutor;
import example.common_base.net.executor.usercenter.UpdateMovieExecutor;
import example.common_base.net.executor.usercenter.UpdateNatureExecutor;
import example.common_base.net.executor.usercenter.UpdateNicknameExecutor;
import example.common_base.net.executor.usercenter.UpdateSexExecutor;
import example.common_base.net.executor.usercenter.UpdateStateExecutor;
import example.common_base.net.executor.usercenter.UpdateUserInfoExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:用户资料执行器的控制器
 */
public class UserInfoController extends BaseController {

    /**
     * 查询用户的信息
     *
     * @param accountId 目标用户的账号Id
     * @return 用户信息实体
     */
    public UserInfo getUserInfo(long accountId) {
        setUp();
        GetUserInfoExecutor executor = new GetUserInfoExecutor(accountId);
        try {
            ResponseSingle<UserInfo> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    public long createUserInfo(long accountId) {
        setUp();
        CreateUserInfoExecutor executor = new CreateUserInfoExecutor(accountId);
        try {
            ResponseSingle<Long> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return 0;
    }

    /**
     * 更新用户的昵称
     *
     * @param accountId 目标用户的账号Id
     * @param nickname  新的昵称
     * @return 更新是否成功
     */
    public boolean updateNickname(long accountId,
                                  String nickname) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setNickName(nickname);
        UpdateNicknameExecutor executor = new UpdateNicknameExecutor(param);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }

        return false;
    }

    /**
     * 更新用户的年龄
     *
     * @param accountId 目标用户的账号id
     * @param age       新的年龄
     * @return 更新是否成功
     */
    public boolean updateAge(long accountId,
                             int age) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setAge(age);
        UpdateAgeExecutor executor = new UpdateAgeExecutor(param);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的生日日期
     *
     * @param accountId 目标用户的账号Id
     * @param birthday  新的生日日期
     * @return 更新是否成功
     */
    public boolean updateBirthday(long accountId, Date birthday) {
        setUp();
        UpdateUserInfoParam updateUserInfoParam = new UpdateUserInfoParam();
        updateUserInfoParam.setAccountId(accountId);
        updateUserInfoParam.setBirthday(birthday);
        UpdateBirthdayExecutor executor = new UpdateBirthdayExecutor(updateUserInfoParam);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的身高
     *
     * @param accountId 目标用户的账号Id
     * @param height    新的身高
     * @return 更新是否成功
     */
    public boolean updateHeight(long accountId,
                                int height) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setHeight(height);
        UpdateHeightExecutor executor = new UpdateHeightExecutor(param);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的性格描述
     *
     * @param accountId 目标用户的账号Id
     * @param nature    新的性格描述
     * @return 更新是否成功
     */
    public boolean updateCharacter(long accountId,
                                   String nature) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setNature(nature);
        UpdateNatureExecutor executor = new UpdateNatureExecutor(param);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的自我描述
     *
     * @param accountId 目标用户的账号Id
     * @param desc      新的自我描述
     * @return 更新是否成功
     */
    public boolean updateDesc(long accountId,
                              String desc) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setDesc(desc);
        UpdateDescExecutor executor = new UpdateDescExecutor(param);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }


    /**
     * 更新用户的所有信息
     *
     * @param accountId     目标用户的账号Id
     * @param nickName      新的昵称
     * @param age           新的年龄
     * @param birthday      新的生日日期
     * @param height        新的身高
     * @param nature        新的性格描述
     * @param desc          新的自我描述
     * @param constellation 新的星座
     * @param state         新的状态
     * @param photoUrl      新的头像Url
     * @param sex           新的性别
     * @param movie         新的喜爱电影
     * @param food          新的喜爱食物
     * @return 更新是否成功
     */
    public boolean updateUserInfo(long accountId,
                                  String nickName,
                                  int age, Date birthday,
                                  int height,
                                  int weight,
                                  String nature,
                                  String desc,
                                  int constellation,
                                  int state,
                                  String photoUrl,
                                  int sex,
                                  String others,
                                  String movie,
                                  String food) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setNature(nature);
        param.setNickName(nickName);
        param.setWeight(weight);
        param.setBirthday(birthday);
        param.setHeight(height);
        param.setAccountId(accountId);
        param.setAge(age);
        param.setDesc(desc);
        param.setFood(food);
        param.setSex(sex);
        param.setPhotoUrl(photoUrl);
        param.setState(state);
        param.setConstellation(constellation);
        param.setMovie(movie);
        param.setOthers(others);
        UpdateUserInfoExecutor executor = new UpdateUserInfoExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }


    /**
     * 更新用户的星座
     *
     * @param accountId     目标用户的账号
     * @param constellation 新的星座
     * @return 更新是否成功
     */
    public boolean updateUserConstellation(long accountId,
                                           int constellation) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setConstellation(constellation);
        UpdateConstellationExecutor executor = new UpdateConstellationExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的体重
     *
     * @param accountId 目标用户的账号
     * @param weight    体重
     * @return 更新是否成功
     */
    public boolean updateUserWeight(long accountId,
                                    int weight) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setWeight(weight);
        UpdateConstellationExecutor executor = new UpdateConstellationExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的其他信息
     *
     * @param accountId 目标用户的账号
     * @param others    其他
     * @return 更新是否成功
     */
    public boolean updateUserOthers(long accountId,
                                    String others) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setOthers(others);
        UpdateConstellationExecutor executor = new UpdateConstellationExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的喜爱食物
     *
     * @param accountId 目标用户的账号Id
     * @param food      新的喜爱食物
     * @return 更新是否成功
     */
    public boolean updateUserFood(long accountId, String food) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setFood(food);
        UpdateFoodExecutor executor = new UpdateFoodExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的喜爱电影
     *
     * @param accountId 目标用户的账号Id
     * @param movie     新的喜爱电影
     * @return 更新是否成功
     */
    public boolean updateUserMovie(long accountId,
                                   String movie) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setMovie(movie);
        UpdateMovieExecutor executor = new UpdateMovieExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的性别
     *
     * @param accountId 目标用户的账号Id
     * @param sex       新的性别
     * @return 更新是否成功
     */
    public boolean updateUserSex(long accountId,
                                 int sex) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setSex(sex);
        UpdateSexExecutor executor = new UpdateSexExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的头像Url
     *
     * @param accountId 目标用户的账号Id
     * @param photoUrl  新的头像Url
     * @return 更新是否成功
     */
    public boolean updateUserHeadImgUrl(long accountId,
                                        String photoUrl) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setPhotoUrl(photoUrl);
        UpdateHeadImgUrlExecutor executor = new UpdateHeadImgUrlExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 更新用户的状态
     *
     * @param accountId 目标用户的账号Id
     * @param state     新的状态
     * @return 更新是否成功
     */
    public boolean updateUserState(long accountId,
                                   int state) {
        setUp();
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setAccountId(accountId);
        param.setState(state);
        UpdateStateExecutor executor = new UpdateStateExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }


    /**
     * 更新用户信息
     *
     * @param param 用户信息参数
     * @return
     */
    public boolean updateUserInfo(UpdateUserInfoParam param) {
        setUp();
        UpdateStateExecutor executor = new UpdateStateExecutor(param);
        try {
            ResponseSingle<Boolean> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_usercenter", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    @Override
    public String getEndPointFilePaht() {
        return "usercenter_endpoint.properties";
    }

}
