package example.common_base.net.executor.comment;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.comment.RestInterfaceUrl;
import cn.vfighter.comment.bean.MusicCircleGreat;
import cn.vfighter.comment.param.GetMusicCircleGreatViewParam;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;

/**
 * 查看当前音乐圈个人点赞情况
 */
public class GetMusicCircleGreatViewExecutor extends
        AuthJsonServiceClientExecutor<VFighterRequest<GetMusicCircleGreatViewParam>, ResponseSingle<MusicCircleGreat>> {
    private static final String endpoint = RestInterfaceUrl.comment_getMusicCircleGreatView;
    private static final TypeToken<ResponseSingle<MusicCircleGreat>> token = new TypeToken<ResponseSingle<MusicCircleGreat>>() {
    };

    public GetMusicCircleGreatViewExecutor(GetMusicCircleGreatViewParam param) {
        super(endpoint, new VFighterRequest<GetMusicCircleGreatViewParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}