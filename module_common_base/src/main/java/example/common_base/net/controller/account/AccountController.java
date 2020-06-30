package example.common_base.net.controller.account;



import org.greenrobot.eventbus.EventBus;

import cn.vfighter.account.bean.UserAccount;
import cn.vfighter.account.param.LoginParam;
import cn.vfighter.account.param.RegisterParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.account.ExistsLoginNameExecutor;
import example.common_base.net.executor.account.LoginExecutor;
import example.common_base.net.executor.account.RegistExecutor;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 账号执行器的控制器
 */
public class AccountController extends BaseController {
    @Override
    public String getEndPointFilePaht() {
        return "account_endpoint.properties";
    }

    /**
     * 判断账号的石佛存在
     *
     * @param loginName 登陆名
     * @return 账号是否存在
     */
    public boolean existsLoginName(String loginName) {
        setUp();
        ExistsLoginNameExecutor existsLoginNameExecutor = new ExistsLoginNameExecutor(loginName);
        try {
            ResponseSingle<Boolean> respone = existsLoginNameExecutor.execute();
            if (respone.hasException()) {
                fondException("vfighter_account", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
            return false;
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    /**
     * 注册帐号
     *
     * @param domain    所属域
     * @param loginName 登录名
     * @param loginType 登录名类型
     * @param endType   终端类型
     * @param password  登录密码
     * @param clientIp  客户端IP
     * @return 用户账号
     */
    public UserAccount registerAccount(String domain, String loginName, String loginType, String endType, String password, String clientIp) {
        //检测账号是否存在
        if (existsLoginName(loginName)) {
            EventBus.getDefault().post(new ExceptionEvent(154));
            return null;
        }
        RegisterParam param = new RegisterParam();
        param.setDomain(domain);
        param.setLoginName(loginName);
        param.setLoginType(loginType);
        param.setEndType(endType);
        param.setPassword(password);
        RegistExecutor executor = new RegistExecutor(param);
        try {
            ResponseSingle<UserAccount> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_account", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    /**
     * 使用账号密码登陆
     *
     * @param loginName 账号
     * @param endType   终端类型
     * @param password  账号
     * @return 用户账号
     */
    public UserAccount login(String loginName, String endType, String password) {
        LoginParam param = new LoginParam();
        param.setLoginName(loginName);
        param.setEndType(endType);
        param.setPassword(password);
        LoginExecutor executor = new LoginExecutor(param);
        try {
            ResponseSingle<UserAccount> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_account", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }

        return null;
    }


    /**
     * 注销登陆
     */
    public void logout() {

    }

    public boolean setTest(long accountId) {
        return false;
    }

    public boolean setTests(long[] accountIds) {
        return false;
    }

    public boolean unsetTest(long accountId) {
        return false;
    }

    public boolean unsetTests(long[] accountIds) {
        return false;
    }

    public boolean bindAccount(long accountId, String domain, String loginName, String loginType, String endType) {
        return false;
    }

    public void unbindAccount(long accountId, String domain, String loginName) {

    }

    public UserAccount getUserAccount(long accountId) {
        return null;
    }


}
