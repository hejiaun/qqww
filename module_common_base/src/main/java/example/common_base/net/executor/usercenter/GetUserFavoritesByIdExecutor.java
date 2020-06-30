package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.UserFavorite;

/**
 * Author: HeJiaJun
 * Date:   
 * Description:  
 *
 */
public class GetUserFavoritesByIdExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<UserFavorite>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getAllPointsType;
	private static final TypeToken<ResponseSingle<Collection<UserFavorite>>> token = new TypeToken<ResponseSingle<Collection<UserFavorite>>>() {
	};

	/**
	 * 根据ID获取所有用户收藏
	 * 
	 * @param param
	 *            用户ID
	 */
	public GetUserFavoritesByIdExecutor(Long param) {
		super(endpoint, new VFighterRequest<Long>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}