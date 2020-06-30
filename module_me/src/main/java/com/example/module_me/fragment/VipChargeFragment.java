package com.example.module_me.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.billy.android.loading.Gloading;
import com.example.module_me.R;
import com.example.module_me.adapter.VipChargeListAdapter;
import com.example.module_me.entity.VipChargeEntity;
import com.example.module_me.presenter.VipChargeFragmentPresenter;
import com.example.module_me.view_interface.IVipChargeFragmentView;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.util.RecyclerViewItemDecorationUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 会员充值Fragment
 */
public class VipChargeFragment extends BaseFragment implements IVipChargeFragmentView {
    RecyclerView rcvVipCharge;
    private VipChargeListAdapter adapter;

    @Override
    public void initView() {
        super.initView();
        rcvVipCharge = view.findViewById(R.id.rcvVipCharge);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        rcvVipCharge.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VipChargeListAdapter(R.layout.item_vip_charge, new ArrayList<VipChargeEntity>());
        adapter.addData(new VipChargeEntity(1, -1, -1, 10));
        adapter.addData(new VipChargeEntity(3, 30, 9, 27));
        adapter.addData(new VipChargeEntity(6, 60, 8, 48));
        adapter.addData(new VipChargeEntity(12, 120, 5, 60));
        rcvVipCharge.setAdapter(adapter);
        rcvVipCharge.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, 10, 0));
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public BasePresenter createPresenter() {
        return new VipChargeFragmentPresenter(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_vip_chagrge;
    }

}
