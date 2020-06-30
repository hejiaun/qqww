package com.example.module_pk.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.entity.AudienceEntiy;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 观众列表适配器
 */
public class AudienceAdapter extends BaseQuickAdapter<AudienceEntiy, CustomBaseViewHolder> {

    public AudienceAdapter(int layoutResId, @Nullable List<AudienceEntiy> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, AudienceEntiy item) {
        ImageView imageView = helper.getView(R.id.ivAudience);
        Glide.with(imageView).load(item.getHeadResource()).into(imageView);
        helper.setText(R.id.tvAudienceName, item.getName());
    }
}
