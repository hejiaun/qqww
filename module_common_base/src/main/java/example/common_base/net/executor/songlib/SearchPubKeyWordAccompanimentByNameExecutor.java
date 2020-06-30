package example.common_base.net.executor.songlib;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.songlib.RestInterfaceUrl;
import cn.vfighter.songlib.bean.Accompaniment;
import cn.vfighter.songlib.param.SearchByKeyWordParam;

/**
 * 根据歌名关键字获取公开伴奏 执行器
 */
public class SearchPubKeyWordAccompanimentByNameExecutor extends
        AuthJsonServiceClientExecutor<VFighterRequest<SearchByKeyWordParam>, ResponseCollection<Accompaniment>> {
    private static final String endpoint = RestInterfaceUrl.songlib_searchPubKeyWordAccompanimentByName;
    private static final TypeToken<ResponseCollection<Accompaniment>> token = new TypeToken<ResponseCollection<Accompaniment>>() {
    };

    /**
     * 根据歌名关键字获取公开伴奏
     *
     * @param param 关键字参数
     * @return
     */
    public SearchPubKeyWordAccompanimentByNameExecutor(SearchByKeyWordParam param) {
        super(endpoint, new VFighterRequest<SearchByKeyWordParam>(param));
    }

    @Override
    protected Type getResultType() {
        return token.getType();
    }
}