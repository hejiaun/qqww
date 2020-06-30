package com.example.module_pk.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.vfighter.songlib.bean.Accompaniment;
import example.common_base.util.CustomBaseViewHolder;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Date:2019/1/16 17:32
 * Description:
 */
public class SearchSongInPkActivityListAdapter extends BaseQuickAdapter<Accompaniment, CustomBaseViewHolder> {

    public SearchSongInPkActivityListAdapter(int layoutResId, @Nullable List<Accompaniment> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, Accompaniment item) {

    }
}
