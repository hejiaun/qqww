package example.common_base.net.executor.comment;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 查询当前音乐圈点赞个数的执行器
 * @return
 */
public class CountMusicCircleGreatExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.comment_countMusicCircleGreat;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	public CountMusicCircleGreatExecutor(Long param) {
		super(endpoint, new VFighterRequest<Long>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}