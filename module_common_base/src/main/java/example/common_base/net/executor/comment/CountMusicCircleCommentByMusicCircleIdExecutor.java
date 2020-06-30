package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 根据评论目标统计音乐圈评论个数的执行器
 */
public class CountMusicCircleCommentByMusicCircleIdExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Long>> {
    private static final String endpoint = RestInterfaceUrl.comment_countMusicCircleCommentByMusicCircleId;
    private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
    };

    public CountMusicCircleCommentByMusicCircleIdExecutor(Long param) {
        super(endpoint, new VFighterRequest<Long>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}