package example.common_base.net.executor.usercenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.UpdateUserInfoParam;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:更新用户喜欢的食物的执行器
 */
public class UpdateFoodExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<UpdateUserInfoParam>, ResponseSingle<Boolean>> {
    private static final String endpoint = RestInterfaceUrl.usercenter_updateFood;
    private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
    };

    /**
     * 创建客户端执行器
     *
     * @param param 参数
     */
    public UpdateFoodExecutor(UpdateUserInfoParam param) {
        super(endpoint, new VFighterRequest<UpdateUserInfoParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}
