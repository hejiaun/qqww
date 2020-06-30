package example.common_base.net.controller.songlib;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.songlib.bean.Song;
import cn.vfighter.songlib.param.SearchByKeyWordParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.songlib.GetPubSongByIdExecutor;
import example.common_base.net.executor.songlib.SearchPubKeyWordSongByNameExecutor;
import example.common_base.net.executor.songlib.SearchPubKeyWordSongByOriginalExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:歌曲控制器
 */
public class SongController extends BaseController {

    public String getEndPointFilePaht() {
        return "songlib_endpoint.properties";
    }


    /**
     * 通过歌曲id获取公开的歌曲
     *
     * @param songId 歌曲id
     * @return
     */
    public Song getPubSongById(long songId) {
        setUp();
        GetPubSongByIdExecutor executor = new GetPubSongByIdExecutor(songId);
        try {
            ResponseSingle<Song> response = executor.execute();
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
     * 通过歌曲名获取公开歌曲
     *
     * @param keyWordParam 歌曲关键字
     * @return
     */
    public Collection<Song> getPubKeyWordSongByName(SearchByKeyWordParam keyWordParam) {
        setUp();
        SearchPubKeyWordSongByNameExecutor executor = new SearchPubKeyWordSongByNameExecutor(keyWordParam);
        try {
            ResponseCollection<Song> response = executor.execute();
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
     * 根据歌曲原唱歌手获取歌曲
     *
     * @param keyWord 原唱歌手名称
     * @return
     */
    public Collection<Song> getPubKeyWordSongByOriginal(SearchByKeyWordParam keyWord) {
        setUp();
        SearchPubKeyWordSongByOriginalExecutor executor = new SearchPubKeyWordSongByOriginalExecutor(keyWord);
        try {
            ResponseCollection<Song> response = executor.execute();
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
