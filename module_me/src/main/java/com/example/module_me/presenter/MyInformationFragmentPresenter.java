package com.example.module_me.presenter;

import android.support.design.widget.BottomSheetDialog;
import android.widget.Toast;

import com.example.module_me.R;
import com.example.module_me.view_interface.IMyInformationFragmentView;
import com.tencent.mm.opensdk.utils.Log;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

import cn.vfighter.usercenter.bean.UserInfo;
import cn.vfighter.usercenter.param.UpdateUserInfoParam;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.Observer;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MyInformationFragmentPresenter extends BasePresenter<IMyInformationFragmentView> {
    private BottomSheetDialog stateDialog;

    /**
     * 构造方法，初始化View层
     *
     * @param iMyInformationFragmentView View层接口
     */
    public MyInformationFragmentPresenter(IMyInformationFragmentView iMyInformationFragmentView) {
        super(iMyInformationFragmentView);
    }


    /**
     * 显示
     */
    public void showStateDialog() {
        if (stateDialog == null) {
            stateDialog = new BottomSheetDialog(getView().getFragment().getContext());
            stateDialog.setContentView(R.layout.dialog_information_state);
            NiceSpinner niceSpinner = stateDialog.findViewById(R.id.niceSpinner);
            ArrayList<String> strings = new ArrayList<>();
            strings.add("求撩");
            strings.add("别撩");
            niceSpinner.attachDataSource(strings);
        }
        stateDialog.show();
    }

    /**
     * 更新用户信息
     *
     * @param info
     */
    public void updateUserInfo(UpdateUserInfoParam info) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserInfo(info, new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d("ddd", "completed=====================");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("ddd", "error=================");
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Log.d("ddd", "true=========================================================");
                    Toast.makeText(getView().getFragment().getActivity(), "更新成功", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("ddd", "false=========================================================");
                    Toast.makeText(getView().getFragment().getActivity(), "更新失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
