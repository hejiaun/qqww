package example.common_base.net.model.usercenter;


import java.util.Date;

import cn.vfighter.usercenter.bean.UserInfo;
import cn.vfighter.usercenter.param.UpdateUserInfoParam;
import example.common_base.base.BaseModel;
import example.common_base.net.controller.usercenter.UserInfoController;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class UserInfoModel extends BaseModel {
    UserInfoController controller = null;

    public UserInfoModel() {
        controller = new UserInfoController();
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @param observer
     */
    public void updateUserInfo(final UpdateUserInfoParam userInfo, Observer<Boolean> observer) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                UpdateUserInfoParam param = new UpdateUserInfoParam();
                boolean response = controller.updateUserInfo(param);
                subscriber.onNext(response);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 异步更新用户生日
     *
     * @param accountId 用户的账号id
     * @param birthday  生日日期
     * @param observer  异步更新的状态的观察者
     */
    public void updateUserBirthday(final long accountId, final Date birthday, Observer<Boolean> observer) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateBirthday(accountId, birthday);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 异步更新用户身高和体重
     *
     * @param accountId 用户id
     * @param height    身高
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserHeightAndWeight(final long accountId, final int height, int weight, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateHeight(accountId, height);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 异步更新用户性格描述
     *
     * @param accountId 用户账号id
     * @param character 用户新的性格描述信息
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserCharacter(final long accountId, final String character, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateCharacter(accountId, character);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 异步更新用户喜爱的食物
     *
     * @param accountId 用户账号id
     * @param food      用户喜爱的食物
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserFavoriteFood(final long accountId, final String food, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateUserFood(accountId, food);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 异步更新用户喜爱的电影
     *
     * @param accountId 用户账号id
     * @param movie     用户喜爱的电影
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserFavoriteMovie(final long accountId, final String movie, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateUserMovie(accountId, movie);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 异步更新用户头像URl
     *
     * @param accountId 用户账号id
     * @param headImg   用户头像url
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserHeadImgUrl(final long accountId, final String headImg, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateUserHeadImgUrl(accountId, headImg);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    /**
     * 异步更新用户昵称
     *
     * @param accountId 用户id
     * @param nickname  用户昵称
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserNickname(final long accountId, final String nickname, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateNickname(accountId, nickname);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

    /**
     * 异步更新用户性别
     *
     * @param accountId 用户的账号id
     * @param sex       用户的性别
     * @param action1   异步更新的状态的观察者
     */
    public void updateUserSex(final long accountId, final int sex, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.updateUserSex(accountId, sex);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

}
