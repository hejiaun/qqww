package example.common_base.net.executor.songlib;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.VFighterRequest;
import cn.vfighter.communication.auth.AuthJsonServiceClientExecutor;
import cn.vfighter.songlib.RestInterfaceUrl;
import cn.vfighter.songlib.bean.Accompaniment;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class GetPubAccompanimentByIdExecutor extends AuthJsonServiceClientExecutor<VFighterRequest<Long>, ResponseSingle<Accompaniment>> {
    private static final String endpoint = RestInterfaceUrl.songlib_getAccompanimentById;
    private static final TypeToken<ResponseSingle<Accompaniment>> token = new TypeToken<ResponseSingle<Accompaniment>>() {
    };

    /**
     * 创建客户端执行器
     *
     * @param param 伴奏id
     */
    public GetPubAccompanimentByIdExecutor(Long param) {
        super(endpoint, new VFighterRequest<Long>(param));
    }


    @Override
    protected Type getResultType() {
        return token.getType();
    }
}
