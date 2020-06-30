package com.example.module_me.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.R;
import com.example.module_me.entity.NewListEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 消息列表适配器
 */
public class NewListAdapter extends BaseQuickAdapter<NewListEntity, CustomBaseViewHolder> {


    private CustomBaseViewHolder helper;
    private NewListEntity item;

    public NewListAdapter(int layoutResId, @Nullable List<NewListEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, NewListEntity item) {
        this.helper = helper;
        this.item = item;

        int itemType = item.getItemType();
        if (itemType == NewListEntity.TYPE_FONT) {
            covert1();
        } else if (itemType == NewListEntity.TYPE_PRESENT) {
            convert2();
        } else if (itemType == NewListEntity.TYEP_EMOJI) {
            convert3();
        } else if (itemType == NewListEntity.TYEP_WORK) {
            convert4();
        }
    }

    private void convert4() {
        helper.setVisible(R.id.tvPresentCount, false);
        helper.setVisible(R.id.tvWork, true);
        helper.setVisible(R.id.ivPresent, false);

        helper.setText(R.id.tvComment, "我朋友唱这首歌超好听");
    }

    private void convert3() {
        helper.setVisible(R.id.tvPresentCount, false);
        helper.setVisible(R.id.tvWork, false);
        helper.setVisible(R.id.ivPresent, false);


        TextView tvComment = helper.getView(R.id.tvComment);
        tvComment.setText("踩了你");
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.yinyuequan_btn_cai_s);
        tvComment.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    private void convert2() {
        helper.setVisible(R.id.tvWork, true);
        helper.setText(R.id.tvComment, "送了");
        helper.setVisible(R.id.ivPresent, true);
        helper.setVisible(R.id.tvPresentCount, true);
    }

    private void covert1() {
        helper.setVisible(R.id.tvPresentCount, false);
        helper.setVisible(R.id.tvWork, false);
        helper.setVisible(R.id.ivPresent, false);
        helper.setText(R.id.tvComment, "好好听");
    }

}
