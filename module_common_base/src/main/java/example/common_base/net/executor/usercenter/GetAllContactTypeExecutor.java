package example.common_base.net.executor.usercenter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.usercenter.RestInterfaceUrl;
import cn.vfighter.usercenter.bean.ContactType;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 *
 */
public class GetAllContactTypeExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest, ResponseSingle<Collection<ContactType>>> {
	private static final String endpoint = RestInterfaceUrl.usercenter_getAllContactType;
	private static final TypeToken<ResponseSingle<ContactType>> token = new TypeToken<ResponseSingle<ContactType>>() {
	};

	/**
	 * 获取所有联系方式类型
	 * 
	 * @return 联系方式类型的集合
	 */
	public GetAllContactTypeExecutor() {
		super(endpoint, new VFighterRequest());
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}