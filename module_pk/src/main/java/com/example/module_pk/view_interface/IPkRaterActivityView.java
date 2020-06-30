package com.example.module_pk.view_interface;

import com.example.module_pk.activity.PkRaterActivity;
import com.example.module_pk.adapter.AudienceAdapter;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public interface IPkRaterActivityView extends IBaseView {
    PkRaterActivity getActivity();
    AudienceAdapter getAdapter();
}
