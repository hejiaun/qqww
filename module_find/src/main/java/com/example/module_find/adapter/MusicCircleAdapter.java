package com.example.module_find.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_find.R;
import com.example.module_find.entity.MusicCircleEntity;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

import example.common_base.entity.UserEntity;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.CustomBaseViewHolder;
import example.common_base.util.DensityUtils;
import example.common_base.util.RecyclerViewItemDecorationUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  音乐圈列表适配器
 */
public class MusicCircleAdapter extends BaseQuickAdapter<MusicCircleEntity, CustomBaseViewHolder> {
    private CustomBaseViewHolder mHelper;
    private MusicCircleEntity mItem;
    private FrameLayout frameLayout;

    public MusicCircleAdapter(int layoutResId, @Nullable List<MusicCircleEntity> data) {
        super(layoutResId, data);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, MusicCircleEntity item) {
        mHelper = helper;
        mItem = item;
        helper.addOnClickListener(R.id.tvComment);
        helper.addOnClickListener(R.id.tvMore);
        helper.addOnClickListener(R.id.tvLike);
        helper.addOnLongClickListener(R.id.tvLike);
        frameLayout = mHelper.getView(R.id.fl);
        frameLayout.removeAllViews();
        initImageGridView();
        initCommentList();
    }


    /**
     * 加载九宫格图片
     */
    private void initImageGridView() {
        NineGridView nineGridView = new NineGridView(mContext);
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        for (int i = 0; i < mItem.getComment().size(); i++) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setBigImageUrl(ConstantValuesUtil.IMAGE_URL_EXAMPLE1);
            imageInfo.setThumbnailUrl(ConstantValuesUtil.IMAGE_URL_EXAMPLE1);
            imageInfos.add(imageInfo);
        }
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfos));
        nineGridView.setGridSpacing(DensityUtils.dp2px(mContext, 2));
        nineGridView.setMaxSize(9);
        frameLayout.addView(nineGridView);
    }


    /**
     * 加载评论列表
     */
    private void initCommentList() {
        // TODO: 2019/1/24 请求评论列表数据
        RecyclerView rcv = mHelper.getView(R.id.rcv);
        MyCommetAdapter adapter = new MyCommetAdapter(R.layout.item_comment_musiccircle, new ArrayList<UserEntity>());
        rcv.setLayoutManager(new LinearLayoutManager(mContext));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance()
                .getItemDecoration(0, 0, DensityUtils.dp2px(mContext, 2), 0));
        int size;
        size = Math.min(3, mItem.getComment().size());
        if (mItem.getComment().size() > 3) {
            mHelper.setVisible(R.id.tvMore, true);
        } else {
            mHelper.setViewGone(R.id.tvMore);
        }
        for (int i = 0; i < size; i++) {
            adapter.addData(mItem.getComment().get(i));
        }
    }

    /**
     * 评论列表适配器
     */
    private class MyCommetAdapter extends BaseQuickAdapter<UserEntity, CustomBaseViewHolder> {

        public MyCommetAdapter(int layoutResId, @Nullable List<UserEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(CustomBaseViewHolder helper, UserEntity item) {

        }
    }


}
