package com.example.module_pk.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.entity.GameoverMultiEntity;

import java.util.List;

import example.common_base.util.CustomBaseViewHolder;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class GameoverRecyclerViewAdapter extends BaseMultiItemQuickAdapter<GameoverMultiEntity, CustomBaseViewHolder> {

    private CustomBaseViewHolder helper;
    private GameoverMultiEntity item;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GameoverRecyclerViewAdapter(List<GameoverMultiEntity> data) {
        super(data);
        //多布局绑定
        addItemType(GameoverMultiEntity.TITLE, R.layout.item_gameover_title);
        addItemType(GameoverMultiEntity.ITEM_SINGER, R.layout.item_gameover_singer);
        addItemType(GameoverMultiEntity.ITEM_RATER, R.layout.item_gameover_rater);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, GameoverMultiEntity item) {
        this.helper = helper;
        this.item = item;
        switch (item.getItemType()) {
            case GameoverMultiEntity.ITEM_RATER:
                coverRater();
                break;
            case GameoverMultiEntity.ITEM_SINGER:
                coverSinger();
                break;
            case GameoverMultiEntity.TITLE:
                coverTitle();
                break;
        }
    }

    private void coverTitle() {
        switch (helper.getPosition()) {
            case 0:
                helper.setText(R.id.tv1, "玩家信息");
                helper.setText(R.id.tv2, "段位");
                helper.setText(R.id.tv3, "人气");
                helper.setText(R.id.tv4, "胜负");
                break;
            case 3:
                helper.setText(R.id.tv1, "评委信息");
                helper.setText(R.id.tv2, "好评率");
                helper.setText(R.id.tv3, "玩家1");
                helper.setText(R.id.tv4, "玩家2");
                break;
        }
    }

    private void coverSinger() {
        helper.setText(R.id.tvSingerName, item.getSingerEntity().getName());
        helper.setImageResource(R.id.ivSingerHead, item.getSingerEntity().getHead());
        helper.setText(R.id.tvPopularity, "123");
        if (helper.getPosition() == 1) {
            helper.setVisible(R.id.tvWin, true);
            helper.setVisible(R.id.tvLose, false);
        } else if (helper.getPosition() == 2) {
            helper.setVisible(R.id.tvWin, false);
            helper.setVisible(R.id.tvLose, true);
        }
    }

    private void coverRater() {
        helper.setText(R.id.tvRaterName, item.getRaterEntity().getName());
        helper.setImageResource(R.id.ivRaterHead, item.getRaterEntity().getHead());
        helper.setText(R.id.tvPraiseRate, "78%(54)");
    }
}
