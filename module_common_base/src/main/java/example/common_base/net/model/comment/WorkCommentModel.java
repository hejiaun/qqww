package example.common_base.net.model.comment;

import java.util.Collection;

import cn.vfighter.comment.bean.WorksComment;
import example.common_base.net.controller.comment.WorkCommentController;
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
public class WorkCommentModel {
    WorkCommentController controller = null;

    public WorkCommentModel() {
        controller = new WorkCommentController();
    }

    /**
     * 获取作品评论列表集合
     *
     * @param worksId    作品Id
     * @param pageIndex  当前页面下标
     * @param pageLength 页面信息条数
     * @param action1    异步回调
     */
    public void getCommentByWorksId(final long worksId, final int pageIndex, final int pageLength, Action1<Collection<WorksComment>> action1) {
        Observable.create(new Observable.OnSubscribe<Collection<WorksComment>>() {
            @Override
            public void call(Subscriber<? super Collection<WorksComment>> subscriber) {
                Collection<WorksComment> result = controller.getCommentByWorksId(worksId, pageIndex, pageLength);
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
     * 给作品添加评论
     *
     * @param target         作品id
     * @param commentContent 评论内容
     * @param accountId      评论人账号id
     * @return
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
     * 回复作品评论
     *
     * @param target          作品id
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
     * 获取作品评论总条数
     *
     * @param target  作品id
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
