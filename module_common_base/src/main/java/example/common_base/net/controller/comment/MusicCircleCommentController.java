package example.common_base.net.controller.comment;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.comment.bean.MusicCircleComment;
import cn.vfighter.comment.param.AddCommentParam;
import cn.vfighter.comment.param.GetCommentListParam;
import cn.vfighter.comment.param.ReturnCommentParam;
import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.comment.AddMusicCircleCommentExecutor;
import example.common_base.net.executor.comment.CountMusicCircleCommentByMusicCircleIdExecutor;
import example.common_base.net.executor.comment.GetMusicCircleCommentListExecutor;
import example.common_base.net.executor.comment.ReturnMusicCircleCommentExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:音乐圈评论控制器
 */
public class MusicCircleCommentController extends BaseController {
    @Override
    public String getEndPointFilePaht() {
        return "comment_endpoint.properties";
    }

    /**
     * 获取音乐圈评论列表
     *
     * @param musicCircleId 音乐圈id
     * @param pageIndex     分页页数
     * @param pageLength    分页大小
     * @return
     */
    public Collection<MusicCircleComment> getCommentByMusicCircleId(long musicCircleId, int pageIndex, int pageLength) {
        setUp();
        GetCommentListParam param = new GetCommentListParam();
        param.setId(musicCircleId);
        param.setPageIndex(pageIndex);
        param.setPageLength(pageLength);
        GetMusicCircleCommentListExecutor executor = new GetMusicCircleCommentListExecutor(param);
        try {
            ResponseCollection<MusicCircleComment> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
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
     * 给音乐圈添加评论
     *
     * @param target         音乐圈id
     * @param commentContent 评论内容
     * @param accountId      评论人账号id
     * @return
     */
    public long addComment(long target, String commentContent, long accountId) {
        setUp();
        AddCommentParam param = new AddCommentParam();
        param.setAccountId(accountId);
        param.setCommentContent(commentContent);
        param.setId(target);
        AddMusicCircleCommentExecutor executor = new AddMusicCircleCommentExecutor(param);
        try {
            ResponseSingle<Long> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return 0;
    }

    /**
     * 回复音乐圈评论
     *
     * @param target          音乐圈id
     * @param commentContent  回复内容
     * @param accountId       评论人id
     * @param returnAccountId 回复人id
     * @return
     */
    public long returnComment(long target, String commentContent, long accountId, long returnAccountId) {
        setUp();
        ReturnCommentParam param = new ReturnCommentParam();
        param.setAccountId(accountId);
        param.setCommentContent(commentContent);
        param.setId(target);
        param.setReturnAccountId(returnAccountId);
        ReturnMusicCircleCommentExecutor executor = new ReturnMusicCircleCommentExecutor(param);
        try {
            ResponseSingle<Long> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return 0;
    }

    /**
     * 获取音乐圈评论总条数
     *
     * @param target 音乐圈id
     * @return
     */
    public long countCommentByTargetId(long target) {
        setUp();
        CountMusicCircleCommentByMusicCircleIdExecutor executor = new CountMusicCircleCommentByMusicCircleIdExecutor(target);
        try {
            ResponseSingle<Long> response = executor.execute();
            if (response.hasException()) {
                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
            } else {
                return response.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return 0;
    }
}
