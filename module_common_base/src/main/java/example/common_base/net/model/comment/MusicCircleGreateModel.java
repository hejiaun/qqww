package example.common_base.net.model.comment;

import java.util.ArrayList;
import java.util.Collection;

import cn.vfighter.comment.bean.MusicCircleGreat;
import example.common_base.net.controller.comment.MusicCircleGreatController;
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
public class MusicCircleGreateModel {
    MusicCircleGreatController controller = null;

    public MusicCircleGreateModel() {
        controller = new MusicCircleGreatController();
    }

    /**
     * 点赞音乐圈
     *
     * @param musicCircleId 音乐圈id
     * @param accountId     点赞人id
     * @param state         点赞状态
     *                      <ul>
     *                      <li>-1:取消点赞</li>
     *                      <li>1:点赞</li>
     *                      <li>2：关心</li>
     *                      <li>3:踩</li>
     *                      </ul>
     * @param action1       异步回调
     */
    public void addMusicCircleGreat(final long musicCircleId, final long accountId, final short state, Action1<Long> action1) {
        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                long result = controller.addMusicCircleGreat(musicCircleId, accountId, state);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }


    /**
     * 获取用户音乐圈点赞状态
     *
     * @param musicCircleId 音乐圈id
     * @param accountId     用户账号id
     * @param action1       异步回调
     */
    public void getMusicCircleGreat(final long musicCircleId, final long accountId, Action1<MusicCircleGreat> action1) {
        Observable.create(new Observable.OnSubscribe<MusicCircleGreat>() {
            @Override
            public void call(Subscriber<? super MusicCircleGreat> subscriber) {
                MusicCircleGreat result = controller.getMusicCircleGreat(musicCircleId, accountId);
                if (result != null) {
                    subscriber.onNext(result);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 获取音乐圈点赞个数
     *
     * @param musicCircleId 音乐圈id
     * @param action1       异步回调
     */
    public void countMusicCircleGreat(final long musicCircleId, Action1<Long> action1) {
        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                long result = controller.countMusicCircleGreat(musicCircleId);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 获取音乐圈点赞的用户列表
     *
     * @param musicCircleId 音乐圈id
     * @param state         状态
     *                      <ul>
     *                      <li>-1:取消点赞</li>
     *                      <li>1:点赞</li>
     *                      <li>2：关心</li>
     *                      <li>3:踩</li>
     *                      </ul>
     * @param pageIndex     分页页数
     * @param pageLength    分页大小
     * @param action1       异步回调
     */
    public void getMusicCircleGreatList(final long musicCircleId, final short state, final int pageIndex, final int pageLength, Action1<Collection<MusicCircleGreat>> action1) {
        Observable.create(new Observable.OnSubscribe<Collection<MusicCircleGreat>>() {
            @Override
            public void call(Subscriber<? super Collection<MusicCircleGreat>> subscriber) {
                ArrayList<MusicCircleGreat> result = (ArrayList<MusicCircleGreat>) controller.getMusicCircleGreatList(musicCircleId, state, pageIndex, pageLength);
                if (result != null) {
                    subscriber.onNext(result);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
