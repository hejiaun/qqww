package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.AddressBook;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class GetAllAddressExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Collection<AddressBook>>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getAllAddress;
	private static final TypeToken<ResponseSingle<AddressBook>> token = new TypeToken<ResponseSingle<AddressBook>>() {
	};

	/**
	 * 获取用户的所有地址
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @return
	 */
	public GetAllAddressExecutor(Long param) {
		super(endpoint, new VFighterRequest<Long>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}