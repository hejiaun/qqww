package com.example.module_me.fragment;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.billy.android.loading.Gloading;
import com.example.module_me.R;
import com.example.module_me.adapter.CoinMenuAdapter;
import com.example.module_me.presenter.MusicCoinChargeFragmentPresenter;
import com.example.module_me.view_interface.IMusicCoinChargeFragmentView;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.entity.CoinEntity;
import example.common_base.util.DensityUtils;


/**
 * Author: HeJiaJun
 * Date:
 * Description:音乐币充值Fragment
 */
public class MusicCoinChargeFragment extends BaseFragment<MusicCoinChargeFragmentPresenter> implements IMusicCoinChargeFragmentView {

    private ArrayList<CoinEntity> coinEntities = new ArrayList<>();
    private CoinMenuAdapter coinMenuAdapter;
    private RecyclerView.ItemDecoration itemDecoration;
    private RecyclerView rcv;


    @Override
    public void initView() {
        super.initView();
        rcv = view.findViewById(R.id.rcv);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        coinEntities.add(new CoinEntity(60, 6));
        coinEntities.add(new CoinEntity(300, 30));
        coinEntities.add(new CoinEntity(680, 68));
        coinMenuAdapter = new CoinMenuAdapter(R.layout.item_coin_menu, coinEntities);
        itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = DensityUtils.dp2px(MusicCoinChargeFragment.this.getContext(), 2);
            }
        };

        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(coinMenuAdapter);
        rcv.addItemDecoration(itemDecoration);

    }


    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MusicCoinChargeFragmentPresenter createPresenter() {
        return new MusicCoinChargeFragmentPresenter(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_music_coin;
    }


    /**
     * View层向Presenter层提供Fragment 上下文
     *
     * @return Fragment上下文
     */
    @Override
    public MusicCoinChargeFragment getFragment() {
        return this;
    }
}
