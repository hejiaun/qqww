package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 取消音乐圈点赞的执行器
 */
public class DeleteMusicCircleGreatExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Boolean>> {
    private static final String endpoint = RestInterfaceUrl.comment_deleteMusicCircleGreat;
    private static final TypeToken<ResponseSingle<Boolean>> token = new TypeToken<ResponseSingle<Boolean>>() {
    };

    public DeleteMusicCircleGreatExecutor(Long param) {
        super(endpoint, new VFighterRequest<Long>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}