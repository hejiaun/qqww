package com.example.module_account.presenter;


import android.widget.Toast;


import com.example.module_account.view_interface.ILoginView;

import cn.vfighter.account.bean.UserAccount;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.account.AccountModel;
import example.common_base.util.FormatValidationUtil;
import rx.functions.Action1;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  登陆界面presenter
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private final AccountModel accountModel;

    /**
     * 构造方法，初始化View层
     *
     * @param iLoginView View层接口
     */
    public LoginPresenter(ILoginView iLoginView) {
        super(iLoginView);
        accountModel = new AccountModel();
    }


    /**
     * 注册
     *
     * @param userName 账号
     * @param password 密码
     */
    public void Regist(final String userName, String password) {
        int userNameValidate = FormatValidationUtil.getInstence().validateUserName(userName);
        int passwordValidate = FormatValidationUtil.getInstence().validatePassword(password);
        if (userNameValidate != 0 || passwordValidate != 0) {
            Toast.makeText(getView().getContext(), "账号和密码都不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        accountModel.registerAccount("null", userName, "1", "1", password, new Action1<UserAccount>() {
            @Override
            public void call(UserAccount userAccount) {
                if (userAccount == null) {
                    Toast.makeText(getView().getContext(), "注册失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getView().getContext(), "注册成功，userName:" + userName, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * 登陆
     *
     * @param loginName 登录名
     * @param password  密码
     * @param endType   登陆终端
     */
    public void login(final String loginName, String password, String endType) {
        accountModel.login(loginName, endType, password, new Action1<UserAccount>() {
            @Override
            public void call(UserAccount userAccount) {
                if (userAccount == null) {
                    Toast.makeText(getView().getContext(), "登陆失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getView().getContext(), "登陆成功，userName:" + loginName, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
