package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.ExistsContactInfoParam;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class ExistsContactInfoExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<ExistsContactInfoParam>, ResponseSingle<Boolean>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_existsContactInfo;
	private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
	};

	/**
	 * 检测联系方式类型是否存在
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param contactType
	 *            联系方式类型
	 * @return
	 */
	public ExistsContactInfoExecutor(ExistsContactInfoParam param) {
		super(endpoint, new VFighterRequest<ExistsContactInfoParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}