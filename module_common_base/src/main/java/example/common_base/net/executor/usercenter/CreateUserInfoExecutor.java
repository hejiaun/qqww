package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;

/**
 * Author: HeJiaJun
 * Date:   
 * Description:  
 *
 */
public class CreateUserInfoExecutor extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_createUserInfo;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	/**
	 * 创建用户资料(用户注册时，从帐号模块调用)
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @return
	 */
	public CreateUserInfoExecutor(Long param) {
		super(endpoint, new VFighterRequest<Long>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}