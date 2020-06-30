package example.common_base.net.executor.account;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.account.RestInterfaceUrl;
import cn.vfighter.account.bean.UserAccount;
import cn.vfighter.account.param.LoginParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:登陆的执行器
 */
public class LoginExecutor extends AuthJsonServiceClientExecutor<VFighterRequest<LoginParam>, ResponseSingle<UserAccount>> {

    private static final String endpoint = RestInterfaceUrl.account_login;
    private static final TypeToken<ResponseSingle<UserAccount>> token = new TypeToken<ResponseSingle<UserAccount>>() {
    };


    public LoginExecutor(LoginParam param) {
        super(endpoint, new VFighterRequest<LoginParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}
