package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.UserPointsParam;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  
 *
 */
public class AddUserPointsExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<UserPointsParam>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_addUserPoints;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	/**
	 * 为某一类型增加1积分
	 * 
	 * @param UserPointsParam
	 *            增减分实体
	 */
	public AddUserPointsExecutor(UserPointsParam param) {
		super(endpoint, new VFighterRequest<UserPointsParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}