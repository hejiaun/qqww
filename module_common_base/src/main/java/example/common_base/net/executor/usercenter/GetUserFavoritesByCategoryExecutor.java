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
import cn.vfighter.usercenter.param.GetUserFavoritesByCategoryParam;

 /**
  * Author: HeJiaJun
  * Date:   
  * Description:  
  *
  */
public class GetUserFavoritesByCategoryExecutor extends
		AuthJsonServiceClientExecutor<VFighterRequest<GetUserFavoritesByCategoryParam>, ResponseCollection<UserFavorite>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getUserFavoritesByCategory;
	private static final TypeToken<ResponseCollection<UserFavorite>> token = new TypeToken<ResponseCollection<UserFavorite>>() {
	};

	/**
	 * 获取所有用户收藏
	 * 
	 * @param param
	 *            用户ID
	 */
	public GetUserFavoritesByCategoryExecutor(GetUserFavoritesByCategoryParam param) {
		super(endpoint, new VFighterRequest<GetUserFavoritesByCategoryParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}