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
public class SubtractUserPointsExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<UserPointsParam>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_subtractUserPoints;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	/**
	 * 为某一类型减少1积分
	 * 
	 * @param UserPointsParam
	 *            增减分实体
	 */
	public SubtractUserPointsExecutor(UserPointsParam param) {
		super(endpoint, new VFighterRequest<UserPointsParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}