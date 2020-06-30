package com.example.module_pk.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.entity.PresentEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;
import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 礼物对话框列表适配器
 */
public class PresenterDialogAdapter extends BaseQuickAdapter<PresentEntity, CustomBaseViewHolder> {

    private PresentEntity item;
    private CustomBaseViewHolder helper;

    public PresenterDialogAdapter(int layoutResId, @Nullable List<PresentEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, PresentEntity item) {
        this.helper = helper;
        this.item = item;
        initImageViewSize();
        initSelectItem();
    }

    /**
     * 设置选中项
     */
    private void initSelectItem() {
        if (item.isSelect()) {
            helper.getView(R.id.llContent).setBackground(mContext.getResources().getDrawable(R.drawable.shape_white_border));
        } else {
            helper.getView(R.id.llContent).setBackground(null);
        }
    }

    /**
     * 设置图片宽高相等
     */
    private void initImageViewSize() {
        ImageView ivPresenter = helper.getView(R.id.ivPresent);
        ViewGroup.LayoutParams layoutParams = ivPresenter.getLayoutParams();
        layoutParams.width = WindowUtil.getInstence().getWindowWidth(mContext) / 4;
        layoutParams.height = WindowUtil.getInstence().getWindowWidth(mContext) / 4;
    }
}
