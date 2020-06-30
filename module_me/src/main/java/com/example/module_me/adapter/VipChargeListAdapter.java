package com.example.module_me.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.R;
import com.example.module_me.entity.VipChargeEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 会员充值列表
 */
public class VipChargeListAdapter extends BaseQuickAdapter<VipChargeEntity, CustomBaseViewHolder> {

    public VipChargeListAdapter(int layoutResId, @Nullable List<VipChargeEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, VipChargeEntity item) {
        int discount = item.getDiscount();
        int originalPrice = item.getOriginalPrice();
        int price = item.getPrice();
        int tenancy = item.getTenancy();

        if (discount > 0) {
            helper.setVisible(R.id.tvDisCount, true);
            helper.setText(R.id.tvDisCount, "限时" + discount + "折");
        } else {
            helper.setVisible(R.id.tvDisCount, false);
        }

        TextView tvOriginalPrice = helper.getView(R.id.tvOriginalPrice);
        if (originalPrice > 0) {
            tvOriginalPrice.setVisibility(View.VISIBLE);
            tvOriginalPrice.setText("原价" + originalPrice);
            tvOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            tvOriginalPrice.setVisibility(View.INVISIBLE);
        }

        if (price > 0) {
            helper.setVisible(R.id.btnPrice, true);
            helper.setText(R.id.btnPrice, price + "");
        } else {
            helper.setVisible(R.id.btnPrice, false);
        }

        if (tenancy > 0) {
            helper.setVisible(R.id.tvTenancy, true);
            helper.setText(R.id.tvTenancy, tenancy + "个月");
        } else {
            helper.setVisible(R.id.tvTenancy, false);
        }
    }
}
