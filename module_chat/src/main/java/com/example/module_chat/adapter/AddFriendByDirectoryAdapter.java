package com.example.module_chat.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;
import com.example.module_chat.entity.DirectoryFriendEntity;

import java.util.List;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class AddFriendByDirectoryAdapter extends BaseQuickAdapter<DirectoryFriendEntity, CustomBaseViewHolder> {

    private CustomBaseViewHolder helper;
    private DirectoryFriendEntity item;

    public AddFriendByDirectoryAdapter(int layoutResId, @Nullable List<DirectoryFriendEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, DirectoryFriendEntity item) {
        this.helper = helper;
        this.item = item;
        initFollow();

    }

    private void initFollow() {
        TextView tv = helper.getView(R.id.tvFollow);
        CardView cardView = helper.getView(R.id.cvFollow);
        helper.addOnClickListener(R.id.tvFollow);

        int color = mContext.getResources().getColor(R.color.red_select);
        String tvText = "关注";
        int textSize = 14;

        if (item.getFollowState() == DirectoryFriendEntity.STATE_FOLLOW) {
            tvText = "已关注";
            textSize = 14;
            color = mContext.getResources().getColor(R.color.followGray);
            helper.setImageResource(R.id.ivHeadImg, R.drawable.audience1);
        } else if (item.getFollowState() == DirectoryFriendEntity.STATE_FOLLOW_EACHOTHER) {
            tvText = "相互关注";
            textSize = 12;
            color = mContext.getResources().getColor(R.color.followGray);
            helper.setImageResource(R.id.ivHeadImg, R.drawable.audience2);

        } else if (item.getFollowState() == DirectoryFriendEntity.STATE_UNFOLLOW) {
            tvText = "关注";
            textSize = 14;
            color = mContext.getResources().getColor(R.color.red_select);
            helper.setImageResource(R.id.ivHeadImg, R.drawable.audience3);
        }

        cardView.setCardBackgroundColor(color);
        tv.setText(tvText);
        tv.setTextSize(textSize);
    }
}
