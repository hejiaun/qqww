package example.common_base.net.executor.usercenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.UpdateUserInfoParam;

/**
 * 更新用户体重的执行器
 */
public class UpdateWeightExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<UpdateUserInfoParam>, ResponseSingle<Boolean>> {
    private static final String endpoint = RestInterfaceUrl.usercenter_updateWeight;
    private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
    };

    public UpdateWeightExecutor(UpdateUserInfoParam param) {
        super(endpoint, new VFighterRequest<UpdateUserInfoParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}