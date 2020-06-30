package com.example.module_me.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.module_me.R;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.eventbusevent.DefaultBusEvent;
import example.common_base.widget.FunctionItemView;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的个人信息Fragment
 */
public class OtherInformationFragment extends BaseFragment {
    RoundedImageView ivHead;
    TextView tvName;
    TextView tvAge;
    TextView tvHeight;
    TextView btnFollow;
    TextView tvChat;
    FunctionItemView fivTel;
    FunctionItemView fivAddress;
    FunctionItemView fivFood;
    FunctionItemView fivMoive;
    FunctionItemView fivOther;
    LinearLayout llPower;
    ImageView ivEdit;

    @Override
    public void initView() {
        super.initView();
        ivEdit = view.findViewById(R.id.ivEdit);
        llPower = view.findViewById(R.id.llPower);
        fivOther = view.findViewById(R.id.fivOther);
        fivMoive = view.findViewById(R.id.fivMoive);
        fivFood = view.findViewById(R.id.fivFood);
        fivAddress = view.findViewById(R.id.fivAddress);
        fivTel = view.findViewById(R.id.fivTel);
        tvChat = view.findViewById(R.id.tv_chat);
        btnFollow = view.findViewById(R.id.btn_follow);
        tvHeight = view.findViewById(R.id.tv2);
        tvAge = view.findViewById(R.id.tv1);
        tvName = view.findViewById(R.id.tv_name);
        ivHead = view.findViewById(R.id.ivHead);

    }

    /**
     * 加载个人信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void showInitInformation(DefaultBusEvent event) {
        if (event.getType() == DefaultBusEvent.USER_INFORMATION) {
            Glide.with(this).load(event.getMsgString()).into(ivHead);
        }
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_information_others;
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        fivTel.setTextTitleImageTitleRightNullSttle("电话", R.drawable.ziliao_icon_shouji);
        fivAddress.setTextTitleImageTitleRightNullSttle("地址", R.drawable.ziliao_icon_dizhi);
        fivFood.setTextTitleImageTitleRightNullSttle("食物", R.drawable.ziliao_icon_food);
        fivMoive.setTextTitleImageTitleRightNullSttle("电影", R.drawable.ziliao_icon_moive);
        fivOther.setTextTitleImageTitleRightNullSttle("其他", R.drawable.ziliao_icon_qita);

        ivEdit.setVisibility(View.GONE);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
//        unbinder.unbind();
    }


}
