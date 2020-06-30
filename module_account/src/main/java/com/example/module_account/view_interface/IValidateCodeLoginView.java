package com.example.module_account.view_interface;

import android.content.Context;

import example.common_base.base.IBaseView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  验证码登陆Activity的View层接口
 */
public interface IValidateCodeLoginView extends IBaseView {
    void setGetValidateCodeText(String text);

    void setClickable(Boolean clickable);

    Context getContext();

}
