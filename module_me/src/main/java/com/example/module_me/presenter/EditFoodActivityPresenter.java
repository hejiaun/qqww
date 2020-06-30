package com.example.module_me.presenter;

import android.widget.Toast;

import com.example.module_me.view_interface.IEditFoodActivityView;

import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人喜好食物信息的Activity的Presenter层
 */
public class EditFoodActivityPresenter extends BasePresenter<IEditFoodActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditFoodActivityView View层接口
     */
    public EditFoodActivityPresenter(IEditFoodActivityView iEditFoodActivityView) {
        super(iEditFoodActivityView);
    }

    /**
     * 提交用户更新的喜爱的食物
     *
     * @param accountId 用户账号id
     * @param food      用户喜爱的食物
     */
    public void commitFood(long accountId, String food) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserFavoriteFood(accountId, food, new Action1<Boolean>() {
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
