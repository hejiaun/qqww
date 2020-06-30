package com.example.module_task.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_task.R;

import java.util.List;

import example.common_base.entity.TaskEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:挑战任务列表适配器
 */
public class TaskAdapter extends BaseQuickAdapter<TaskEntity, BaseViewHolder> {

    private BaseViewHolder helper;

    /**
     * 构造方法
     *
     * @param layoutResId 列表布局id
     * @param data        列表布局数据集合
     */
    public TaskAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(BaseViewHolder helper, TaskEntity item) {
        this.helper = helper;
        helper.setText(R.id.tvTitle, item.getTitle());
        if (item.isFinish()) {//如果任务已经完成
            if (item.isReceivce()) {//如果任务已经领取
                setBtnDo(R.color.white, "已完成", R.color.fontGray);
            } else {
                setBtnDo(R.drawable.shape_border_red_round_4dp, "领取", R.drawable.selector_font_shallow);
            }
        } else {
            switch (item.getType()) {
                case TaskEntity.EVALUATE_TASK:
                    setBtnDo(R.drawable.shape_border_white_round_4dp, "前往", R.drawable.selector_font_deep_black);
                    break;
                case TaskEntity.FIGHT_TASK:
                    setBtnDo(R.drawable.shape_border_white_round_4dp, "前往", R.drawable.selector_font_deep_black);
                    break;
                case TaskEntity.SHARE_TASK:
                    setBtnDo(R.color.white, "未完成", R.color.fontGray);
                    break;
                case TaskEntity.SIGN_TASK:
                    setBtnDo(R.drawable.shape_border_red_round_4dp, "签到", R.drawable.selector_font_shallow);
                    break;
            }
        }
    }

    public void setBtnDo(int drawable, String text, int color) {
        helper.setBackgroundRes(R.id.btnDo, drawable);
        helper.setText(R.id.btnDo, text);
        TextView tvDo = (TextView) helper.getView(R.id.btnDo);
        tvDo.setTextColor(tvDo.getResources().getColorStateList(color));
    }

}
