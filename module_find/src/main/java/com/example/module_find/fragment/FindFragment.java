package com.example.module_find.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_find.R;
import com.example.module_find.activity.MusicCircleActivity;
import com.example.module_find.view_interface.IFindView;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.util.ARouterUtil;
import example.common_base.widget.FunctionItemView;
import example.common_base.widget.ZxingUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description：发现Fragment
 */
@Route(path = ARouterUtil.Find_Fragment)
public class FindFragment extends BaseFragment implements IFindView, View.OnClickListener {
    FunctionItemView fivMusicCircle;
    private ImageView ivScan;

    @Override
    public void initView() {
        super.initView();
        fivMusicCircle = view.findViewById(R.id.fiv_musicCircle);
        ivScan = view.findViewById(R.id.ivScan);
        fivMusicCircle.setOnClickListener(this);
        ivScan.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        setItemStyle("音乐圈", fivMusicCircle, R.drawable.find_icon_yinyuequan);
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 设置条目样式
     *
     * @param text
     * @param fiv
     */
    public void setItemStyle(String text, FunctionItemView fiv, int image) {
        fiv.setTextTitle(text);
        fiv.setRightImage();
        fiv.setImageTitle(image);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_find;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.fiv_musicCircle) {
            startActivity(new Intent(getContext(), MusicCircleActivity.class));
        } else if (viewId == R.id.ivScan) {
            ZxingUtil.getInstence().startScanActivity(getActivity());
        }
    }
}
