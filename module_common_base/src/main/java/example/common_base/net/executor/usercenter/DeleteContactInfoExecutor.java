package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.DeleteContactInfoParam;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class DeleteContactInfoExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<DeleteContactInfoParam>, ResponseSingle<Boolean>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_deleteContactInfo;
	private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
	};

	/**
	 * 删除用户的联系方式
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param contactType
	 *            联系方式类型
	 * @return
	 */
	public DeleteContactInfoExecutor(DeleteContactInfoParam param) {
		super(endpoint, new VFighterRequest<DeleteContactInfoParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}