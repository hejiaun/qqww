package com.example.module_me.view_interface;

import android.widget.TextView;

import org.w3c.dom.Text;

import example.common_base.base.IBaseView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public interface IChangePasswordActivityView extends IBaseView {
    /**
     * view层向Presenter提供获取验证码TextView
     * @return
     */
    TextView getValidateTextView();
}
