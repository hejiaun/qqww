package example.common_base.net.executor.account;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.account.RestInterfaceUrl;
import cn.vfighter.account.bean.UserAccount;
import cn.vfighter.account.param.RegisterParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:注册的执行器
 */
public class RegistExecutor extends AuthJsonServiceClientExecutor<VFighterRequest<RegisterParam>, ResponseSingle<UserAccount>> {
    private final static String endPoint = RestInterfaceUrl.account_registerAccount;
    private final static TypeToken<ResponseSingle<UserAccount>> typeToken = new TypeToken<ResponseSingle<UserAccount>>() {
    };

    /**
     * 创建客户端执行器
     *
     * @param param
     */
    public RegistExecutor(RegisterParam param) {
        super(endPoint, new VFighterRequest<RegisterParam>(param));
    }

    @Override
    protected Type getResultType() {
        return typeToken.getType();
    }
}
