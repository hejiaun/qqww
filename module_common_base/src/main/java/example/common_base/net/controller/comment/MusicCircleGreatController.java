package example.common_base.net.controller.comment;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.comment.bean.MusicCircleGreat;
import cn.vfighter.comment.param.AddMusicCircleGreatParam;
import cn.vfighter.comment.param.GetMusicCircleGreatListParam;
import cn.vfighter.comment.param.GetMusicCircleGreatViewParam;
import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.comment.AddMusicCircleGreatExecutor;
import example.common_base.net.executor.comment.CountMusicCircleGreatExecutor;
import example.common_base.net.executor.comment.GetMusicCircleGreatListExecutor;
import example.common_base.net.executor.comment.GetMusicCircleGreatViewExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:音乐圈点赞控制器
 */
public class MusicCircleGreatController extends BaseController {
    @Override
    public String getEndPointFilePaht() {
        return "comment_endpoint.properties";
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
     * @return
     */
    public long addMusicCircleGreat(long musicCircleId, long accountId, short state) {
        setUp();
        AddMusicCircleGreatParam param = new AddMusicCircleGreatParam();
        param.setAccountId(accountId);
        param.setMusicCircleId(musicCircleId);
        param.setState(state);
        AddMusicCircleGreatExecutor executor = new AddMusicCircleGreatExecutor(param);
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
     * 获取用户音乐圈点赞状态
     *
     * @param musicCircleId 音乐圈id
     * @param accountId     用户账号id
     * @return
     */
    public MusicCircleGreat getMusicCircleGreat(long musicCircleId, long accountId) {
        setUp();
        GetMusicCircleGreatViewParam param = new GetMusicCircleGreatViewParam();
        param.setAccountId(accountId);
        param.setMusicCircleId(musicCircleId);
        GetMusicCircleGreatViewExecutor executor = new GetMusicCircleGreatViewExecutor(param);
        try {
            ResponseSingle<MusicCircleGreat> response = executor.execute();
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
     * 获取音乐圈点赞个数
     *
     * @param musicCircleId 音乐圈id
     * @return
     */
    public long countMusicCircleGreat(long musicCircleId) {
        setUp();
        CountMusicCircleGreatExecutor executor = new CountMusicCircleGreatExecutor(musicCircleId);
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
     * @return
     */
    public Collection<MusicCircleGreat> getMusicCircleGreatList(long musicCircleId, short state, int pageIndex, int pageLength) {
        setUp();
        GetMusicCircleGreatListParam param = new GetMusicCircleGreatListParam();
        param.setMusicCircleId(musicCircleId);
        param.setState(state);
        param.setPageIndex(pageIndex);
        param.setPageLength(pageLength);
        GetMusicCircleGreatListExecutor executor = new GetMusicCircleGreatListExecutor(param);
        try {
            ResponseCollection<MusicCircleGreat> response = executor.execute();
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

//    /**
//     * 点赞音乐圈
//     *
//     * @param musicCircleId 音乐圈id
//     * @param accountId     用户账号id
//     * @return
//     */
//    public long likeMusicCircle(long musicCircleId, long accountId) {
//        setUp();
//        AddMusicCircleGreatParam param = new AddMusicCircleGreatParam();
//        param.setAccountId(accountId);
//        param.setMusicCircleId(musicCircleId);
//        AddMusicCircleGreatExecutor executor = new AddMusicCircleGreatExecutor(param);
//        try {
//            ResponseSingle<Long> response = executor.execute();
//            if (response.hasException()) {
//                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
//            } else {
//                return response.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return 0;
//    }
//
//    /**
//     * @param musicCircleGreatId
//     * @return
//     */
//    public boolean disLikeMusicCircle(long musicCircleGreatId) {
//        setUp();
//        DeleteMusicCircleGreatExecutor executor = new DeleteMusicCircleGreatExecutor(musicCircleGreatId);
//        try {
//            ResponseSingle<Boolean> response = executor.execute();
//            if (response.hasException()) {
//                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
//            } else {
//                return response.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public MusicCircleGreat getMusicCircleGreat(long musicCircleId, long accountId) {
//
//        return null;
//    }
//
//    public long countMusicCircleGreat(long musicCircleId) {
//        return 0;
//    }
//
//    public Collection<MusicCircleGreat> getMusicCircleGreatList(long musicCircleId, int pageIndex, int pageLength) {
//        setUp();
//        GetMusicCircleGreatListParam param = new GetMusicCircleGreatListParam();
//        param.setMusicCircleId(musicCircleId);
//        param.setPageIndex(pageIndex);
//        param.setPageLength(pageLength);
//        GetMusicCircleGreatListExecutor executor = new GetMusicCircleGreatListExecutor(param);
//        try {
//            ResponseCollection<MusicCircleGreat> response = executor.execute();
//            if (response.hasException()) {
//                fondException("vfighter_comment", response.getException().getMessage(), response.getException().getCode());
//            } else {
//                return response.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//
//        return null;
//    }

}
