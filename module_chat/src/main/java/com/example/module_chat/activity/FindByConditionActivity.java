package com.example.module_chat.activity;


import com.example.module_chat.R;
import com.example.module_chat.presenter.FindByConditionActivityPresenter;
import com.example.module_chat.view_interface.IFindByConditionActivityView;

import example.common_base.base.BaseActivity;

/**
 * Author: HeJiaJun
 * Date:
 * <p>
 * Description: 添加好友Activity
 */
public class FindByConditionActivity extends BaseActivity<FindByConditionActivityPresenter> implements
        IFindByConditionActivityView {

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_find_by_condiction;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public FindByConditionActivityPresenter createPresenter() {
        return new FindByConditionActivityPresenter(this);
    }
}
