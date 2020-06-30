package com.example.module_home.view_interface;

import com.example.module_home.adapter.RankRecyclerViewAdapter;

import example.common_base.base.IBaseView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  主页Fragment的View层接口
 */
public interface IHomeView extends IBaseView {
    RankRecyclerViewAdapter getAdapter();
}
