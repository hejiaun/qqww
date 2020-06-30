package com.example.module_me.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_me.view_interface.IMusicCoinChargeFragmentView;

import example.common_base.base.BasePresenter;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class MusicCoinChargeFragmentPresenter extends BasePresenter<IMusicCoinChargeFragmentView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iMusicCoinChargeFragmentView View层接口
     */
    public MusicCoinChargeFragmentPresenter(IMusicCoinChargeFragmentView iMusicCoinChargeFragmentView) {
        super(iMusicCoinChargeFragmentView);
    }

    /**
     * 获取列表Item间隔修饰器
     *
     * @return 列表Item间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(getView().getFragment().getActivity(), 2);
            }
        };
    }
}
