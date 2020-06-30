package example.common_base.net.model.account;


import cn.vfighter.account.bean.UserAccount;
import example.common_base.net.controller.account.AccountController;
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
public class AccountModel {
    private AccountController controller;

    public AccountModel() {
        controller = new AccountController();
    }

    /**
     * 检测登录名是否存在
     *
     * @param domain    域
     * @param loginName 登录名
     * @return
     */
    public void existsLoginName(final String domain, final String loginName, Action1<Boolean> action1) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean result = controller.existsLoginName(loginName);
                subscriber.onNext(result);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(action1);
    }

    /**
     * 注册帐号
     *
     * @param domain    所属域
     * @param loginName 登录名
     * @param loginType 登录名类型
     * @param endType   终端类型
     * @param password  登录密码
     * @param action1   回调操作
     * @return
     */
    public void registerAccount(final String domain, final String loginName, final String loginType, final String endType, final String password, Action1<UserAccount> action1) {
        Observable.create(new Observable.OnSubscribe<UserAccount>() {
            @Override
            public void call(Subscriber<? super UserAccount> subscriber) {
                UserAccount userAccount = controller.registerAccount(domain, loginName, loginType, endType, password, null);
                subscriber.onNext(userAccount);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

    /**
     * 登陆
     *
     * @param loginName 登陆名
     * @param endType   登陆终端
     * @param password  登陆密码
     * @param action1   回调操作
     */
    public void login(final String loginName, final String endType, final String password, Action1<UserAccount> action1) {
        Observable.create(new Observable.OnSubscribe<UserAccount>() {
            @Override
            public void call(Subscriber<? super UserAccount> subscriber) {
                UserAccount userAccount = controller.login(loginName, endType, password);
                subscriber.onNext(userAccount);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

}
