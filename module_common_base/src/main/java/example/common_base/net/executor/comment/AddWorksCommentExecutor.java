package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.param.AddCommentParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 添加作品评论的执行器
 */
public class AddWorksCommentExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<AddCommentParam>, ResponseSingle<Long>> {
    private static final String endpoint = RestInterfaceUrl.comment_addWorksComment;
    private static final TypeToken<ResponseSingle<Long>> token = new TypeToken<ResponseSingle<Long>>() {
    };

    public AddWorksCommentExecutor(AddCommentParam param) {
        super(endpoint, new VFighterRequest<AddCommentParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}