package example.common_base.net.executor.songlib;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.songlib.RestInterfaceUrl;
import cn.vfighter.songlib.bean.Song;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:根据歌曲Id获取公开歌曲信息的执行器
 */
public class GetPubSongByIdExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Song>> {
    private static final String endpoint = RestInterfaceUrl.songlib_getPubSongById;
    private static final TypeToken<ResponseSingle<Song>> token = new TypeToken<ResponseSingle<Song>>() {
    };

    /**
     * 创建客户端执行器
     *
     * @param songId 歌曲id
     */
    public GetPubSongByIdExecutor(Long songId) {
        super(endpoint, new VFighterRequest<Long>(songId));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}
