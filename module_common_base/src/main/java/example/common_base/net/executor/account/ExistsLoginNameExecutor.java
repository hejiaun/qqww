package example.common_base.net.executor.account;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.account.RestInterfaceUrl;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description: 判断账号是否存在的执行器
 */
public class ExistsLoginNameExecutor extends AuthJsonServiceClientExecutor<VFighterRequest<String>, ResponseSingle<Boolean>> {

    private static final String endpoint = RestInterfaceUrl.account_existsLoginName;
    private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
    };

    /**
     * 按照用户ID获取用户信息
     *
     * @param account 账号
     * @return 用户信息
     */
    public ExistsLoginNameExecutor(String account) {
        super(endpoint, new VFighterRequest<String>(account));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}
