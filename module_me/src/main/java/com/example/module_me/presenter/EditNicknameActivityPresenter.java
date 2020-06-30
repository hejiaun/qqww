package com.example.module_me.presenter;

import android.widget.Toast;

import com.example.module_me.view_interface.IEditNicknameActivityView;

import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑手机号码的Activity的Presenter层
 */
public class EditNicknameActivityPresenter extends BasePresenter<IEditNicknameActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditNicknameActivityView View层接口
     */
    public EditNicknameActivityPresenter(IEditNicknameActivityView iEditNicknameActivityView) {
        super(iEditNicknameActivityView);
    }

    /**
     * 更新用户昵称
     *
     * @param accountId 用户账号id
     * @param nickname  用户昵称
     */
    public void commitNickname(long accountId, String nickname) {
        new UserInfoModel().updateUserNickname(accountId, nickname, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean == null || !aBoolean.booleanValue()) {
                    Toast.makeText(MyApplication.getApplication(), "修改失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getApplication(), "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
