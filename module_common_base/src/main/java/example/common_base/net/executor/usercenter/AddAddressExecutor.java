package example.common_base.net.executor.usercenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.AddressBookParam;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class AddAddressExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<AddressBookParam>, ResponseSingle<Long>>{
    private static final String endpoint = RestInterfaceUrl.usercenter_addAddress;
    private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
    };

    /**
     * 创建客户端执行器
     *
     * @param param    参数
     */
    public AddAddressExecutor(AddressBookParam param) {
        super(endpoint, new VFighterRequest<AddressBookParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}
