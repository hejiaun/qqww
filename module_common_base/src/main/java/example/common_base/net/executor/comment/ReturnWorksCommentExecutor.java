package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.param.ReturnCommentParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 回复作品评论的执行器
 */
public class ReturnWorksCommentExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<ReturnCommentParam>, ResponseSingle<Long>> {
    private static final String endpoint = RestInterfaceUrl.comment_returnWorksComment;
    private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
    };

    public ReturnWorksCommentExecutor(ReturnCommentParam param) {
        super(endpoint, new VFighterRequest<ReturnCommentParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}