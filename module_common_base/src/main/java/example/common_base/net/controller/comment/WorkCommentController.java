package example.common_base.net.controller.comment;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.comment.bean.WorksComment;
import cn.vfighter.comment.param.AddCommentParam;
import cn.vfighter.comment.param.GetCommentListParam;
import cn.vfighter.comment.param.ReturnCommentParam;
import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.comment.AddWorksCommentExecutor;
import example.common_base.net.executor.comment.CountWorksCommentByWorksIdExecutor;
import example.common_base.net.executor.comment.GetWorksCommentListExecutor;
import example.common_base.net.executor.comment.ReturnWorksCommentExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:作品评论控制器
 */
public class WorkCommentController extends BaseController {

    @Override
    public String getEndPointFilePaht() {
        return "comment_endpoint.properties";
    }

    /**
     * 获取作品评论列表集合
     *
     * @param worksId    作品Id
     * @param pageIndex  当前页面下标
     * @param pageLength 页面信息条数
     * @return
     */
    public Collection<WorksComment> getCommentByWorksId(long worksId, int pageIndex, int pageLength) {
        setUp();
        GetCommentListParam param = new GetCommentListParam();
        param.setPageLength(pageLength);
        param.setPageIndex(pageIndex);
        param.setId(worksId);
        GetWorksCommentListExecutor executor = new GetWorksCommentListExecutor(param);
        try {
            ResponseCollection<WorksComment> response = executor.execute();
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
     * 给作品添加评论
     *
     * @param target         作品id
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
        AddWorksCommentExecutor executor = new AddWorksCommentExecutor(param);
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
     * 回复作品评论
     *
     * @param target          作品id
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
        ReturnWorksCommentExecutor executor = new ReturnWorksCommentExecutor(param);
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
     * 获取作品评论总条数
     *
     * @param target 作品id
     * @return
     */
    public long countCommentByTargetId(long target)
    {
        setUp();
        CountWorksCommentByWorksIdExecutor executor = new CountWorksCommentByWorksIdExecutor(target);
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
