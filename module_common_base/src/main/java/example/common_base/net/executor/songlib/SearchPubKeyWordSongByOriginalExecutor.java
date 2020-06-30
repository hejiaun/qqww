package example.common_base.net.executor.songlib;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.songlib.RestInterfaceUrl;
import cn.vfighter.songlib.bean.Song;
import cn.vfighter.songlib.param.SearchByKeyWordParam;

/**
 * 根据歌手名关键字获取公开歌曲执行器
 */
public class SearchPubKeyWordSongByOriginalExecutor
        extends AuthJsonServiceClientExecutor<VFighterRequest<SearchByKeyWordParam>, ResponseCollection<Song>> {
    private static final String endpoint = RestInterfaceUrl.songlib_searchPubKeyWordSongByOriginal;
    private static final TypeToken<ResponseCollection<Song>> token = new TypeToken<ResponseCollection<Song>>() {
    };

    /**
     * 根据歌手名关键字获取公开歌曲
     *
     * @param param 关键字参数
     * @return
     */
    public SearchPubKeyWordSongByOriginalExecutor(SearchByKeyWordParam param) {
        super(endpoint, new VFighterRequest<SearchByKeyWordParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}