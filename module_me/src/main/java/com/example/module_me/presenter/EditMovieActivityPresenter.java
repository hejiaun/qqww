package com.example.module_me.presenter;

import android.widget.Toast;

import com.example.module_me.view_interface.IEditMovieActivityView;

import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人喜好电影信息的Activity的Presenter层
 */
public class EditMovieActivityPresenter extends BasePresenter<IEditMovieActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditMovieActivityView View层接口
     */
    public EditMovieActivityPresenter(IEditMovieActivityView iEditMovieActivityView) {
        super(iEditMovieActivityView);
    }

    /**
     * 提交用户更新的喜爱的食物
     *
     * @param accountId 用户账号id
     * @param movie     用户喜爱的电影
     */
    public void commitMovie(long accountId, String movie) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserFavoriteMovie(accountId, movie, new Action1<Boolean>() {
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
