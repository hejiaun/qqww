package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.UserFavorite;
import cn.vfighter.usercenter.param.GetUserFavoritesParam;
/**
 * Author: HeJiaJun
 * Date:   
 * Description:  
 *
 */
public class GetUserFavoritesExecutor extends
		AuthJsonServiceClientExecutor<VFighterRequest<GetUserFavoritesParam>, ResponseCollection<UserFavorite>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getUserFavorites;
	private static final TypeToken<ResponseCollection<UserFavorite>> token = new TypeToken<ResponseCollection<UserFavorite>>() {
	};

	/**
	 * 获取所有用户收藏
	 * 
	 * @param param
	 *            用户ID
	 */
	public GetUserFavoritesExecutor(GetUserFavoritesParam param) {
		super(endpoint, new VFighterRequest<GetUserFavoritesParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}