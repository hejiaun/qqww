package com.example.module_me.view_interface;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.module_me.fragment.ApprenticeFragment;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public interface IApprenticeFragmentView extends IBaseView {
    TextView getCurrentPriceTextView();

    RecyclerView getRecyclerView();

    ApprenticeFragment getFragment();
}
