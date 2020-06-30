package example.common_base.net.executor.comment;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.param.AddMusicCircleGreatParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 点赞音乐圈的执行器
 * 
 */
public class AddMusicCircleGreatExecutor
		extends AuthJsonServiceClientExecutor<VFighterRequest<AddMusicCircleGreatParam>, ResponseSingle<Long>> {
	private static final String endpoint = RestInterfaceUrl.comment_addMusicCircleGreat;
	private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
	};

	public AddMusicCircleGreatExecutor(AddMusicCircleGreatParam param) {
		super(endpoint, new VFighterRequest<AddMusicCircleGreatParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}