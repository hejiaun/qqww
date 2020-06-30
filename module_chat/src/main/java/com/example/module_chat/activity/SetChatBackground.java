package com.example.module_chat.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_chat.R;
import com.example.module_chat.presenter.SetChatBackgroundPresenter;
import com.example.module_chat.view_interface.ISetChatBackgroundView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ImageUtil;
import example.common_base.widget.FunctionItemView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:设置聊天背景Activity
 */
public class SetChatBackground extends BaseActivity<SetChatBackgroundPresenter> implements ISetChatBackgroundView {

    FunctionItemView fivSelectBackgroundIamge;
    FunctionItemView fivSelectFormAlbum;
    FunctionItemView fivTakePhoto;
    TextView tv_title;
    private ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        fivSelectBackgroundIamge = findViewById(R.id.fivSelectBackgroundIamge);
        fivSelectFormAlbum = findViewById(R.id.fivSelectFormAlbum);
        fivTakePhoto = findViewById(R.id.fivTakePhoto);
        tv_title = findViewById(R.id.tv_title);
        fivSelectBackgroundIamge = findViewById(R.id.fivSelectBackgroundIamge);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        fivSelectBackgroundIamge.setOnClickListener(this);
        fivSelectFormAlbum.setOnClickListener(this);
        fivTakePhoto.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tv_title.setText("设置聊天背景");
        fivSelectBackgroundIamge.setRightWithTextTitleStyle("选择背景图");
        fivSelectFormAlbum.setRightWithTextTitleStyle("从手机相册选择");
        fivTakePhoto.setRightWithTextTitleStyle("拍一张");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_chatbackground;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SetChatBackgroundPresenter createPresenter() {
        return new SetChatBackgroundPresenter(this);
    }


    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.fivSelectBackgroundIamge) {
            startActivity(new Intent(this, SelectBackgroundActivity.class));
        } else if (viewId == R.id.fivSelectFormAlbum) {
            ImageUtil.getInstence().getSingleImageFromAlbum(this);
        } else if (viewId == R.id.fivTakePhoto) {
            ImageUtil.getInstence().takePhoto(this);
        }
    }

    /**
     * 获取图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
        } else {
            Toast.makeText(this, "获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
