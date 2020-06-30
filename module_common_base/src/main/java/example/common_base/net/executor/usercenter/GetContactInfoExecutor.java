package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.ContactInfo;
import cn.vfighter.usercenter.param.GetContactInfoParam;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class GetContactInfoExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<GetContactInfoParam>, ResponseSingle<ContactInfo>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getContactInfo;
	private static final TypeToken<ResponseSingle<ContactInfo>> token = new TypeToken<ResponseSingle<ContactInfo>>() {
	};

	/**
	 * 获取指定类型的联系方式
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param contactType
	 *            联系方式类型
	 * @return
	 */
	public GetContactInfoExecutor(GetContactInfoParam param) {
		super(endpoint, new VFighterRequest<GetContactInfoParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}