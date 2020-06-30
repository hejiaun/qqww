package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.AddUserFavoritesParam;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class AddUserFavoritesExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<AddUserFavoritesParam>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_addUserUserFavorites;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	/**
	 * 增加用户收藏
	 * 
	 * @param param
	 *            用户收藏实体
	 */
	public AddUserFavoritesExecutor(AddUserFavoritesParam param) {
		super(endpoint, new VFighterRequest<AddUserFavoritesParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}