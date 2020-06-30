package com.example.module_me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;

public class SoundPracticeFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int initLayout() {
        return 0;
    }

}
