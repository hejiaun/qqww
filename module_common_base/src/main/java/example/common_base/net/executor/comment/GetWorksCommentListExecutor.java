package example.common_base.net.executor.comment;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.bean.WorksComment;
import cn.vfighter.comment.param.GetCommentListParam;
import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 查询当前作品点赞列表的执行器
 * 
 */
public class GetWorksCommentListExecutor extends
		AuthJsonServiceClientExecutor<VFighterRequest<GetCommentListParam>, ResponseCollection<WorksComment>> {
	private static final String endpoint = RestInterfaceUrl.comment_getWorksCommentList;
	private static final TypeToken<ResponseCollection<WorksComment>> token = new TypeToken<ResponseCollection<WorksComment>>() {
	};


	public GetWorksCommentListExecutor(GetCommentListParam param) {
		super(endpoint, new VFighterRequest<GetCommentListParam>(param));
	}

	@Override
	protected Type getResultType() {
		return token.getType();
	}
}