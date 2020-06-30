package com.example.module_pk.view_interface;

import com.example.module_pk.adapter.SearchSongInPkActivityListAdapter;

import example.common_base.base.IBaseView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public interface ISearchSongInPkActivityView extends IBaseView {
    SearchSongInPkActivityListAdapter getAdapter();
}
