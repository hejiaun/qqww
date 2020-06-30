package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 删除音乐圈评论的执行器
 */
public class DeleteWorksCommentExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Boolean>> {
    private static final String endpoint = RestInterfaceUrl.comment_deleteWorksComment;
    private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
    };

    public DeleteWorksCommentExecutor(Long param) {
        super(endpoint, new VFighterRequest<Long>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}