package com.example.module_me.presenter;

import android.widget.Toast;

import com.example.module_me.view_interface.IEditCharacterActivityView;

import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人性格信息的Activity的Presenter层
 */
public class EditCharacterActivityPresenter extends BasePresenter<IEditCharacterActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditCharacterActivityView View层接口
     */
    public EditCharacterActivityPresenter(IEditCharacterActivityView iEditCharacterActivityView) {
        super(iEditCharacterActivityView);
    }


    /**
     * 提交用户新的性格信息
     *
     * @param accountId 用户账号id
     * @param character 用户新的性格描述
     */
    public void commnitCharacter(long accountId, String character) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserCharacter(accountId, character, new Action1<Boolean>() {
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
