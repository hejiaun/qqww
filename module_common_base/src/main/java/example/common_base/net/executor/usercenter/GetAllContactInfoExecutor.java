package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.ContactInfo;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class GetAllContactInfoExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Collection<ContactInfo>>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getAllContactInfo;
	private static final TypeToken<ResponseSingle<ContactInfo>> token = new TypeToken<ResponseSingle<ContactInfo>>() {
	};

	/**
	 * 获取用户的所有联系方式
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @return
	 */
	public GetAllContactInfoExecutor(Long param) {
		super(endpoint, new VFighterRequest<Long>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}