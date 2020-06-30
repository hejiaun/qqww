package com.example.module_me.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_me.R;
import com.example.module_me.presenter.MyInformationFragmentPresenter;
import com.example.module_me.view_interface.IMyInformationFragmentView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import cn.vfighter.usercenter.param.UpdateUserInfoParam;
import example.common_base.base.BaseFragment;
import example.common_base.util.PermissionUtil;
import example.common_base.widget.FunctionItemView;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的个人信息Fragment
 */
public class MyInformationFragment extends BaseFragment<MyInformationFragmentPresenter> implements
        View.OnClickListener,
        IMyInformationFragmentView {
    RoundedImageView ivHead;
    TextView tvName;
    TextView tvAge;
    TextView tvHeight;
    TextView tvCharacter;
    TextView btnFollow;
    TextView tvChat;
    FunctionItemView fivTel;
    FunctionItemView fivAddress;
    FunctionItemView fivFood;
    FunctionItemView fivMoive;
    FunctionItemView fivOther;
    TextView tvState;
    private ImageView ivEdit;

    @Override
    public void initView() {
        super.initView();
        tvState = view.findViewById(R.id.tvState);
        fivOther = view.findViewById(R.id.fivOther);
        fivMoive = view.findViewById(R.id.fivMoive);
        fivFood = view.findViewById(R.id.fivFood);
        fivAddress = view.findViewById(R.id.fivAddress);
        fivTel = view.findViewById(R.id.fivTel);
        tvChat = view.findViewById(R.id.tv_chat);
        btnFollow = view.findViewById(R.id.btn_follow);
        tvCharacter = view.findViewById(R.id.tv3);
        tvHeight = view.findViewById(R.id.tv2);
        tvAge = view.findViewById(R.id.tv1);
        tvName = view.findViewById(R.id.tv_name);
        ivHead = view.findViewById(R.id.ivHead);
        ivEdit = view.findViewById(R.id.ivEdit);
        tvState.setOnClickListener(this);
        ivEdit.setOnClickListener(this);
        ivHead.setOnClickListener(this);
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MyInformationFragmentPresenter createPresenter() {
        return new MyInformationFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_information_me;
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        fivTel.setTextTitleImageTitleRightNullSttle("电话", R.drawable.ziliao_icon_shouji);
        fivAddress.setTextTitleImageTitleRightNullSttle("地址", R.drawable.ziliao_icon_dizhi);
        fivFood.setTextTitleImageTitleRightNullSttle("食物", R.drawable.ziliao_icon_food);
        fivMoive.setTextTitleImageTitleRightNullSttle("电影", R.drawable.ziliao_icon_moive);
        fivOther.setTextTitleImageTitleRightNullSttle("其他", R.drawable.ziliao_icon_qita);
    }

    /**
     * 创建Fragment视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 显示编辑资料对话框
     */
    private void showEditInformationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = View.inflate(getActivity(), R.layout.dialog_personal_information, null);
        final EditText etAge = dialogView.findViewById(R.id.etAge);
        final EditText etHeight = dialogView.findViewById(R.id.etHeight);
        EditText etConstellation = dialogView.findViewById(R.id.etConstellation);
        final EditText etCharacter = dialogView.findViewById(R.id.etCharacter);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if (viewId == R.id.btnSure) {
                    UpdateUserInfoParam userInfo = new UpdateUserInfoParam();
                    userInfo.setAccountId(100000001L);
                    userInfo.setAge(19);
                    userInfo.setDesc("自我描述");
                    userInfo.setHeight(168);
                    userInfo.setNature("描述");
                    userInfo.setNickName("昵称");
                    getPresenter().updateUserInfo(userInfo);
                } else if (viewId == R.id.btnCancel) {

                }
                dialog.dismiss();
            }
        };
        dialogView.findViewById(R.id.btnCancel).setOnClickListener(onClickListener);
        dialogView.findViewById(R.id.btnSure).setOnClickListener(onClickListener);
        dialog.show();
    }

    /**
     * View层向Presenter层提供MyInformationFragment
     *
     * @return MyInformationFragment
     */
    @Override
    public MyInformationFragment getFragment() {
        return this;
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tvState) {
            getPresenter().showStateDialog();

        } else if (viewId == R.id.ivEdit) {//编辑
            showEditInformationDialog();

        } else if (viewId == R.id.ivHead) {
            // TODO: 2019/1/28 上传图片
            startAlbum();
        }
    }

    /**
     * 开启图片选择器并进行剪裁
     */
    public void startAlbum() {
        PictureSelector.create(getActivity())
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(false)
                .imageSpanCount(3)
                .enableCrop(true)
                .isGif(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.getInstance().onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
    }
}
