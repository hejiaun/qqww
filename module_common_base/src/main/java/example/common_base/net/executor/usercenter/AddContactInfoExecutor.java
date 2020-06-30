package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.param.AddContactInfoParam;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class AddContactInfoExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<AddContactInfoParam>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_addContactInfo;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	/**
	 * 添加用户的联系方式
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param contactType
	 *            联系方式类型
	 * @param value
	 *            联系方式内容
	 * @return
	 */
	public AddContactInfoExecutor(AddContactInfoParam param) {
		super(endpoint, new VFighterRequest<AddContactInfoParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}