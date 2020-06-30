package com.example.module_home.view_interface;

import com.example.module_home.adapter.HotFightAdapter;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:热门对战Fragment的View层接口
 */
public interface IHotFightListFragmentView extends IBaseView {
    HotFightAdapter getAdapter();

}
