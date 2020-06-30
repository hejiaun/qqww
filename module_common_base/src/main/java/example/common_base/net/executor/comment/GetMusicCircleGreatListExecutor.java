package example.common_base.net.executor.comment;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.bean.MusicCircleGreat;
import cn.vfighter.comment.param.GetMusicCircleGreatListParam;
import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 查询当前音乐圈点赞列表的执行器
 * 
 */
public class GetMusicCircleGreatListExecutor extends
		AuthJsonServiceClientExecutor<VFighterRequest<GetMusicCircleGreatListParam>, ResponseCollection<MusicCircleGreat>> {
	private static final String endpoint = RestInterfaceUrl.comment_getMusicCircleGreatList;
	private static final TypeToken<ResponseCollection<MusicCircleGreat>> token = new TypeToken<ResponseCollection<MusicCircleGreat>>() {
	};


	public GetMusicCircleGreatListExecutor(GetMusicCircleGreatListParam param) {
		super(endpoint, new VFighterRequest<GetMusicCircleGreatListParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}