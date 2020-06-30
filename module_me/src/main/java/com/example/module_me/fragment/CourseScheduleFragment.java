package com.example.module_me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_me.presenter.CourseScheduleFragmentPresenter;
import com.example.module_me.view_interface.ICourseScheduleFragmentView;

import example.common_base.base.BaseFragment;

public class CourseScheduleFragment extends BaseFragment<CourseScheduleFragmentPresenter> implements ICourseScheduleFragmentView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public CourseScheduleFragmentPresenter createPresenter() {
        return new CourseScheduleFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return 0;
    }

}
