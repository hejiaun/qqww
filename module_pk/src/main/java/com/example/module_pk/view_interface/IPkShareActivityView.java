package com.example.module_pk.view_interface;

import com.example.module_pk.adapter.PkShareAdapter;

import example.common_base.base.IBaseView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 分享activity的View层接口
 */
public interface IPkShareActivityView extends IBaseView {

    PkShareAdapter getAdapter();
}
