package com.example.module_me.presenter;


import android.widget.Toast;

import com.example.module_me.view_interface.IEditInformationActivityView;

import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class EditInformationActivityPresenter extends BasePresenter<IEditInformationActivityView> {

    /**
     * 构造方法，初始化View层
     *
     * @param iEditInformationActivityView View层接口
     */
    public EditInformationActivityPresenter(IEditInformationActivityView iEditInformationActivityView) {
        super(iEditInformationActivityView);
    }

    /**
     * 提交新的性别
     *
     * @param accountId 用户的账号id
     * @param sex       性别
     */
    public void commitSex(long accountId, int sex) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserSex(accountId, sex, new Action1<Boolean>() {
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
