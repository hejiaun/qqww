package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 根据评论目标统计作品评论个数的执行器
 */
public class CountWorksCommentByWorksIdExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Long>> {
    private static final String endpoint = RestInterfaceUrl.comment_countWorksCommentByWorksId;
    private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
    };


    public CountWorksCommentByWorksIdExecutor(Long param) {
        super(endpoint, new VFighterRequest<Long>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}