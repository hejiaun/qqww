package com.example.module_chat.activity;

import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_chat.R;
import com.example.module_chat.adapter.SelectBackgroundAdapter;
import com.example.module_chat.entity.SelectBackgroundEntity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;
import example.common_base.util.DensityUtils;
import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 选择聊天背景Activity
 */
public class SelectBackgroundActivity extends BaseActivity {
    RecyclerView rcv;
    private SelectBackgroundAdapter adapter;
    private SuperTextView stvTitleBar;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        rcv = findViewById(R.id.rcv);
        stvTitleBar = findViewById(R.id.stvTitleBar);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new SelectBackgroundAdapter(R.layout.item_background_selelct, new ArrayList<SelectBackgroundEntity>());
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(new MyItemDecoration());
        adapter.setOnItemClickListener(new MyItemClickListener());
        closeRecyclerViewReflashAnimate();

        //--设置titleBar点击事件---//
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                ActivityUtil.getInstance().finishActivity(SelectBackgroundActivity.class);
            }
        });
        stvTitleBar.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
                ActivityUtil.getInstance().finishActivity(SelectBackgroundActivity.class);
            }
        });
    }

    /**
     * 关闭RecyclerView刷新动画
     */
    private void closeRecyclerViewReflashAnimate() {
        ((DefaultItemAnimator) rcv.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        adapter.addData(new SelectBackgroundEntity());
        adapter.addData(new SelectBackgroundEntity());
        adapter.addData(new SelectBackgroundEntity());
        adapter.addData(new SelectBackgroundEntity());
        adapter.addData(new SelectBackgroundEntity());
        adapter.addData(new SelectBackgroundEntity());
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_select_background;
    }

    /**
     * 创建presenter
     * @return
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int padding = DensityUtils.dp2px(SelectBackgroundActivity.this, 4);
            outRect.right = padding;
            outRect.bottom = padding;
            outRect.left = padding;
            outRect.top = padding;

            RoundedImageView roundedImageView = view.findViewById(R.id.ivBackground);
            int i = WindowUtil.getInstence().getWindowWidth(SelectBackgroundActivity.this) - DensityUtils.dp2px(SelectBackgroundActivity.this, 4) * 6;
            roundedImageView.getLayoutParams().width = i / 3;
            roundedImageView.getLayoutParams().height = roundedImageView.getLayoutParams().width;
        }
    }

    private class MyItemClickListener implements BaseQuickAdapter.OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            ArrayList<SelectBackgroundEntity> data = (ArrayList<SelectBackgroundEntity>) adapter.getData();
            for (int i = 0; i < data.size(); i++) {
                SelectBackgroundEntity e = data.get(i);
                if (e.isSelect()) {
                    e.setSelect(false);
                    adapter.notifyItemChanged(i);
                }
            }
            SelectBackgroundEntity entity = (SelectBackgroundEntity) data.get(position);
            entity.setSelect(true);
            adapter.notifyItemChanged(position);
        }
    }
}
