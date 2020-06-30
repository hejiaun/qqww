package com.example.module_me.presenter;

import android.widget.Toast;

import com.example.module_me.view_interface.IEditHeightAndWeightActivityView;

import cn.vfighter.usercenter.param.UpdateUserInfoParam;
import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.Observer;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人身高体重的Activity的Presenter层
 */
public class EditHeightAndWeightActivityPresenter extends BasePresenter<IEditHeightAndWeightActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditHeightAndWeightActivityView View层接口
     */
    public EditHeightAndWeightActivityPresenter(IEditHeightAndWeightActivityView iEditHeightAndWeightActivityView) {
        super(iEditHeightAndWeightActivityView);
    }


    /**
     * 提交身高和体重到后台进行更新
     */
    public void commitHeightAndWeight(UpdateUserInfoParam param, int height) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserInfo(param, new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean == null || !aBoolean.booleanValue()) {
                    Toast.makeText(MyApplication.getApplication(), "修改失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getApplication(), "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
