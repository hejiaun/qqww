package com.example.module_pk.fragment;

import com.example.module_pk.R;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class RaterIncomeFragment extends BaseFragment {
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_income_rater;
    }
}
