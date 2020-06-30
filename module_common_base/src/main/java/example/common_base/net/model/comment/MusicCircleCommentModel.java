package example.common_base.net.model.comment;

import java.util.ArrayList;
import java.util.Collection;

import cn.vfighter.comment.bean.MusicCircleComment;
import example.common_base.net.controller.comment.MusicCircleCommentController;
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
public class MusicCircleCommentModel {
    MusicCircleCommentController controller = null;

    public MusicCircleCommentModel() {
        controller = new MusicCircleCommentController();
    }

    /**
     * 获取音乐圈评论列表
     *
     * @param musicCircleId 音乐圈id
     * @param pageIndex     分页页数
     * @param pageLength    分页大小
     * @param action1       异步回调
     */
    public void getCommentByMusicCircleId(final long musicCircleId, final int pageIndex, final int pageLength, Action1<Collection<MusicCircleComment>> action1) {
        Observable.create(new Observable.OnSubscribe<Collection<MusicCircleComment>>() {
            @Override
            public void call(Subscriber<? super Collection<MusicCircleComment>> subscriber) {
                ArrayList<MusicCircleComment> result = (ArrayList<MusicCircleComment>) controller.getCommentByMusicCircleId(musicCircleId, pageIndex, pageLength);
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
     * 给音乐圈添加评论
     *
     * @param target         音乐圈id
     * @param commentContent 评论内容
     * @param accountId      评论人账号id
     * @param action1        异步回调
     */
    public void addComment(final long target, final String commentContent, final long accountId, Action1<Long> action1) {
        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                long result = controller.addComment(target, commentContent, accountId);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 回复音乐圈评论
     *
     * @param target          音乐圈id
     * @param commentContent  回复内容
     * @param accountId       评论人id
     * @param returnAccountId 回复人id
     * @param action1         异步回调
     */
    public void returnComment(final long target, final String commentContent, final long accountId, final long returnAccountId, Action1<Long> action1) {
        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                long result = controller.returnComment(target, commentContent, accountId, returnAccountId);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }


    /**
     * 获取音乐圈评论总条数
     *
     * @param target  音乐圈id
     * @param action1 异步回调
     */
    public void countCommentByTargetId(final long target, Action1<Long> action1) {
        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                long result = controller.countCommentByTargetId(target);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

}
