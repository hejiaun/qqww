package example.common_base.net.controller.usercenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.usercenter.bean.UserFavorite;
import cn.vfighter.usercenter.param.AddUserFavoritesParam;
import cn.vfighter.usercenter.param.GetUserFavoritesByCategoryParam;
import cn.vfighter.usercenter.param.GetUserFavoritesByModuleParam;
import cn.vfighter.usercenter.param.GetUserFavoritesParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.usercenter.AddUserFavoritesExecutor;
import example.common_base.net.executor.usercenter.GetUserFavoritesByCategoryExecutor;
import example.common_base.net.executor.usercenter.GetUserFavoritesByIdExecutor;
import example.common_base.net.executor.usercenter.GetUserFavoritesByModuleExecutor;
import example.common_base.net.executor.usercenter.GetUserFavoritesExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class UserFavoriteController extends BaseController {

    public long addFavorite(long accountId, String srcModule, String value, String title, String author, String authorImg, String category) {
        setUp();
        AddUserFavoritesParam param = new AddUserFavoritesParam();
        param.setAccountId(accountId);
        param.setSrcModule(srcModule);
        param.setValue(value);
        param.setTitle(title);
        param.setAuthor(author);
        param.setAuthorImg(authorImg);
        param.setCategory(category);
        AddUserFavoritesExecutor executor = new AddUserFavoritesExecutor(param);
        try {
            ResponseSingle<Long> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }

        return 0;
    }

    public UserFavorite getUserFavorite(long favoriteId) {
        setUp();
        GetUserFavoritesByIdExecutor executor = new GetUserFavoritesByIdExecutor(favoriteId);
        try {
            ResponseSingle<UserFavorite> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    public Collection<UserFavorite> getUserFavorites(long accountId, int pageIndex, int pageLength) {
        setUp();
        GetUserFavoritesParam param = new GetUserFavoritesParam();
        param.setAccountId(accountId);
        param.setPageIndex(pageIndex);
        param.setPageLength(pageLength);
        GetUserFavoritesExecutor executor = new GetUserFavoritesExecutor(param);
        try {
            ResponseCollection<UserFavorite> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    public Collection<UserFavorite> getUserFavoritesByCategory(long accountId, String category, int pageIndex, int pageLength) {
        setUp();

        GetUserFavoritesByCategoryParam param = new GetUserFavoritesByCategoryParam();
        param.setAccountId(accountId);
        param.setCategory(category);
        param.setPageIndex(pageIndex);
        param.setPageLength(pageLength);
        GetUserFavoritesByCategoryExecutor executor = new GetUserFavoritesByCategoryExecutor(param);
        try {
            ResponseCollection<UserFavorite> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    public Collection<UserFavorite> getUserFavoritesByModule(long accountId, String srcModule, int pageIndex, int pageLength) {
        setUp();
        GetUserFavoritesByModuleParam param = new GetUserFavoritesByModuleParam();
        param.setAccountId(accountId);
        param.setSrcModule(srcModule);
        param.setPageIndex(pageIndex);
        param.setPageLength(pageLength);
        GetUserFavoritesByModuleExecutor executor = new GetUserFavoritesByModuleExecutor(param);
        try {
            ResponseCollection<UserFavorite> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    @Override
    public String getEndPointFilePaht() {
        return "usercenter_endpoint.properties";
    }
}
