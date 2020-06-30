package com.example.module_me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_me.presenter.SelectCourseFragmentPresenter;
import com.example.module_me.view_interface.ISelectCourseFragmentView;

import example.common_base.base.BaseFragment;

/**
 * Author: HeJiaJun
 * Date:
 * Description:
 */
public class SelectCourseFragment extends BaseFragment<SelectCourseFragmentPresenter> implements ISelectCourseFragmentView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public SelectCourseFragmentPresenter createPresenter() {
        return new SelectCourseFragmentPresenter(this);
    }

    @Override
    public int initLayout() {
        return 0;
    }

}
