package example.common_base.net.controller.usercenter;


import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.usercenter.bean.UserPoints;
import cn.vfighter.usercenter.param.GetUserPointsParam;
import cn.vfighter.usercenter.param.UserPointsParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.usercenter.AddUserPointsExecutor;
import example.common_base.net.executor.usercenter.SubtractUserPointsExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class UserPointController extends BaseController {
    public long addUserPoints(long accountId, int code) {
        setUp();
        UserPointsParam param = new UserPointsParam();
        param.setAccountId(accountId);
        param.setCode(code);
        AddUserPointsExecutor executor = new AddUserPointsExecutor(param);
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

    public long addUserPoints(long accountId, int code, int value) {
        setUp();
        UserPointsParam param = new UserPointsParam();
        param.setAccountId(accountId);
        param.setCode(code);
        param.setValue(value);
        AddUserPointsExecutor executor = new AddUserPointsExecutor(param);
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

    public long subtractUserPoints(long accountId, int code) {
        setUp();
        UserPointsParam param = new UserPointsParam();
        param.setAccountId(accountId);
        param.setCode(code);
        SubtractUserPointsExecutor executor = new SubtractUserPointsExecutor(param);
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

    public long subtractUserPoints(long accountId, int code, int value) {
        setUp();

        UserPointsParam param = new UserPointsParam();
        param.setAccountId(accountId);
        param.setValue(value);
        param.setCode(code);
        SubtractUserPointsExecutor executor = new SubtractUserPointsExecutor(param);
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

    public long getUserPoints(long accountId, int code) {
        setUp();

        GetUserPointsParam param = new GetUserPointsParam();
        param.setAccountId(accountId);
        param.setCode(code);


        return 0;
    }

    public Collection<UserPoints> getAllUserPoints(long accountId) {
        setUp();
        return null;
    }

    @Override
    public String getEndPointFilePaht() {
        return "usercenter_endpoint.properties";
    }
}
