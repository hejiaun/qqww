package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.bean.MusicCircleComment;
import cn.vfighter.comment.param.GetCommentListParam;
import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 获取当前音乐圈评论列表的执行器
 */
public class GetMusicCircleCommentListExecutor extends
        AuthJsonServiceClientExecutor<VFighterRequest<GetCommentListParam>, ResponseCollection<MusicCircleComment>> {
    private static final String endpoint = RestInterfaceUrl.comment_getMusicCircleCommentList;
    private static final TypeToken<ResponseCollection<MusicCircleComment>> token = new TypeToken<ResponseCollection<MusicCircleComment>>() {
    };


    public GetMusicCircleCommentListExecutor(GetCommentListParam param) {
        super(endpoint, new VFighterRequest<GetCommentListParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}