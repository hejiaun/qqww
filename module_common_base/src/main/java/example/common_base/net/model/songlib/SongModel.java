package example.common_base.net.model.songlib;

import java.util.Collection;

import cn.vfighter.songlib.bean.Song;
import cn.vfighter.songlib.param.SearchByKeyWordParam;
import example.common_base.net.controller.songlib.SongController;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SongModel {
    SongController controller = null;

    public SongModel() {
        controller = new SongController();
    }

    /**
     * 通过歌曲id获取公开的歌曲
     *
     * @param songId  歌曲id
     * @param action1 异步回调
     * @return
     */
    public void getPubSongById(final long songId, Action1<Song> action1) {
        Observable
                .create(new Observable.OnSubscribe<Song>() {
                    @Override
                    public void call(Subscriber<? super Song> subscriber) {
                        Song result = controller.getPubSongById(songId);
                        subscriber.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 通过歌曲名获取公开歌曲
     *
     * @param keyWordParam 歌曲关键字
     * @return
     */
    public void getPubKeyWordSongByName(final SearchByKeyWordParam keyWordParam, Action1<Collection<Song>> action1) {
        Observable
                .create(new Observable.OnSubscribe<Collection<Song>>() {
                    @Override
                    public void call(Subscriber<? super Collection<Song>> subscriber) {
                        Collection<Song> result = controller.getPubKeyWordSongByName(keyWordParam);
                        subscriber.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);


    }

    /**
     * 根据歌曲原唱歌手获取歌曲
     *
     * @param keyWordParam 原唱歌手名称
     * @param action1      异步回调
     * @return
     */
    public void getPubKeyWordSongByOriginal(final SearchByKeyWordParam keyWordParam, Action1<Collection<Song>> action1) {
        Observable
                .create(new Observable.OnSubscribe<Collection<Song>>() {
                    @Override
                    public void call(Subscriber<? super Collection<Song>> subscriber) {
                        Collection<Song> result = controller.getPubKeyWordSongByOriginal(keyWordParam);
                        subscriber.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
