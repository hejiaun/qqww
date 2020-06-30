package example.common_base.net.controller.songlib;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.songlib.bean.Accompaniment;
import cn.vfighter.songlib.param.SearchByKeyWordParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.songlib.GetPubAccompanimentByIdExecutor;
import example.common_base.net.executor.songlib.SearchPubKeyWordAccompanimentByNameExecutor;
import example.common_base.net.executor.songlib.SearchPubKeyWordAccompanimentByOriginalExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:伴奏控制器
 */
public class AccompanimentController extends BaseController {

    public String getEndPointFilePaht() {
        return "songlib_endpoint.properties";
    }

    /**
     * 根据伴奏id获取公开伴奏
     *
     * @param accompanimentId 伴奏id
     * @return
     */
    public Accompaniment getPubAccompanimentById(long accompanimentId) {
        setUp();
        GetPubAccompanimentByIdExecutor executor = new GetPubAccompanimentByIdExecutor(accompanimentId);
        try {
            ResponseSingle<Accompaniment> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_songlib", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }

        return null;
    }

    /**
     * 通过伴奏名称获取公开的伴奏
     *
     * @param keyWordParam 伴奏名称
     * @return
     */
    public Collection<Accompaniment> getPubKeyWordAccompanimentByName(SearchByKeyWordParam keyWordParam) {
        setUp();
        SearchPubKeyWordAccompanimentByNameExecutor executor = new SearchPubKeyWordAccompanimentByNameExecutor(keyWordParam);
        try {
            ResponseCollection<Accompaniment> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_songlib", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }

        return null;
    }

    /**
     * 通过原唱歌手获取公开的伴奏
     *
     * @param keyWord 原唱歌手名称
     * @return
     */
    public Collection<Accompaniment> getPubKeyWordAccompanimentByOriginal(SearchByKeyWordParam keyWord) {
        setUp();
        SearchPubKeyWordAccompanimentByOriginalExecutor executor = new SearchPubKeyWordAccompanimentByOriginalExecutor(keyWord);
        try {
            ResponseCollection<Accompaniment> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_songlib", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }


}
