package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.UserFavorite;
import cn.vfighter.usercenter.bean.UserInfo;

/**
 * Author: HeJiaJun
 * Date:   
 * Description:  
 *
 */
public class GetUserInfoExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<UserInfo>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getUserInfo;
	private static final TypeToken<ResponseSingle<Collection<UserFavorite>>> token = new TypeToken<ResponseSingle<Collection<UserFavorite>>>() {
	};

	/**
	 * 获取用户资料
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @return
	 */
	public GetUserInfoExecutor(Long param) {
		super(endpoint, new VFighterRequest<Long>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}