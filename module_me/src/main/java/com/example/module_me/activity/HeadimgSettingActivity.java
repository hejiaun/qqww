package com.example.module_me.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.module_me.R;
import com.example.module_me.dialog.HeadImageBottomSheetDialog;
import com.example.module_me.presenter.HeadimgSettingPresenter;
import com.example.module_me.view_interface.IHeadimgSettingView;
import com.jaeger.library.StatusBarUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ImageUtil;
import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 头像设置activity
 */
public class HeadimgSettingActivity extends BaseActivity<HeadimgSettingPresenter> implements IHeadimgSettingView {
    private PhotoView photoView = null;
    private HeadImageBottomSheetDialog dialog;
    private SuperTextView stvTitleBar;

    @Override
    public int initLayout() {
        return R.layout.activity_headimg_setting;
    }

    @Override
    public void initView() {
        super.initView();
        stvTitleBar = findViewById(R.id.stvTitleBar);
        photoView = findViewById(R.id.pv);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setDarkMode(this);
        photoView.enable();
        //--设置photoView为宽高相等---//
        photoView.getLayoutParams().width = WindowUtil.getInstence().getWindowWidth(this);
        photoView.getLayoutParams().height = photoView.getLayoutParams().width;

        stvTitleBar.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                showBottomSheetDialog();
            }
        });
        stvTitleBar.setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                ActivityUtil.getInstance().finishActivity(HeadimgSettingActivity.class);
            }
        });
    }

    @Override
    public HeadimgSettingPresenter createPresenter() {
        return new HeadimgSettingPresenter(this);
    }

    /**
     * 显示底部菜单列表对话框
     */
    private void showBottomSheetDialog() {
        if (dialog == null) {
            dialog = new HeadImageBottomSheetDialog(this);
            dialog.setHeadImgDialogClickListener(new HeadImageBottomSheetDialog.HeadImgDialogClickListener() {
                @Override
                public void clickCancel() {
                    Toast.makeText(HeadimgSettingActivity.this, "clickCancel", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void clickPhotograph() {
                    ImageUtil.getInstence().takePhoto(HeadimgSettingActivity.this);
                }

                @Override
                public void clickAlbum() {
                    ImageUtil.getInstence().getSingleImageFromAlbum(HeadimgSettingActivity.this);
                }

                @Override
                public void clickHeadimgHistory() {
                    Toast.makeText(HeadimgSettingActivity.this, "clickHeagdimgHistory", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void clickSaveImg() {
                    Toast.makeText(HeadimgSettingActivity.this, "clickImage", Toast.LENGTH_SHORT).show();
                }
            });
        }
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
            Glide.with(photoView).load(localMedia.get(0).getPath()).into(photoView);
        } else {
            Toast.makeText(this, "获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
