package com.example.module_me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.allen.library.SuperTextView;
import com.example.module_me.R;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 头像编辑界面的底部对话框
 */
public class HeadImageBottomSheetDialog extends BottomSheetDialog implements View.OnClickListener {

    private final View view;
    private SuperTextView stvCancel;
    private SuperTextView stvPhotograph;
    private SuperTextView stvAlbum;
    private SuperTextView stvHeadimgHistory;
    private SuperTextView stvSaveHeadimg;

    public HeadImageBottomSheetDialog(@NonNull Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_headimg_setting, null);
        setContentView(view);
        initView();
    }

    private void initView() {
        stvCancel = view.findViewById(R.id.stvCancel);
        stvPhotograph = view.findViewById(R.id.stvPhotograph);
        stvAlbum = view.findViewById(R.id.stvAlbum);
        stvHeadimgHistory = view.findViewById(R.id.stvHeadimgHistory);
        stvSaveHeadimg = view.findViewById(R.id.stvSaveHeadimg);

        stvAlbum.setOnClickListener(this);
        stvSaveHeadimg.setOnClickListener(this);
        stvHeadimgHistory.setOnClickListener(this);
        stvPhotograph.setOnClickListener(this);
        stvCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.stvCancel) {//点击取消
            if (headImgDialogClickListener != null) {
                headImgDialogClickListener.clickCancel();
            }
            dismiss();
        } else if (id == R.id.stvPhotograph) {//点击拍照
            if (headImgDialogClickListener != null) {
                headImgDialogClickListener.clickPhotograph();
            }
            dismiss();
        } else if (id == R.id.stvAlbum) {//点击相册
            if (headImgDialogClickListener != null) {
                headImgDialogClickListener.clickAlbum();
            }
            dismiss();
        } else if (id == R.id.stvHeadimgHistory) {//点击历史头像
            if (headImgDialogClickListener != null) {
                headImgDialogClickListener.clickHeadimgHistory();
            }
            dismiss();
        } else if (id == R.id.stvSaveHeadimg) {//点击保存头像
            if (headImgDialogClickListener != null) {
                headImgDialogClickListener.clickSaveImg();
            }
            dismiss();
        }
    }

    private HeadImgDialogClickListener headImgDialogClickListener = null;

    public void setHeadImgDialogClickListener(HeadImgDialogClickListener headImgDialogClickListener) {
        this.headImgDialogClickListener = headImgDialogClickListener;
    }

    public interface HeadImgDialogClickListener {
        /**
         * 点击取消
         */
        void clickCancel();

        /**
         * 点击拍照
         */
        void clickPhotograph();

        /**
         * 点击相册
         */
        void clickAlbum();

        /**
         * 点击历史头像
         */
        void clickHeadimgHistory();

        /**
         * 点击保存头像
         */
        void clickSaveImg();
    }
}
