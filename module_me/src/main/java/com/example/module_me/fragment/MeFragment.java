package com.example.module_me.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_me.R;
import com.example.module_me.activity.EditInformationActivity;
import com.example.module_me.activity.FansActivity;
import com.example.module_me.activity.FollowActivity;
import com.example.module_me.activity.HeadimgSettingActivity;
import com.example.module_me.activity.ListenerActivity;
import com.example.module_me.activity.NewListActivity;
import com.example.module_me.activity.PopularityActivity;
import com.example.module_me.activity.SettingActivity;
import com.example.module_me.activity.WalletActivity;
import com.example.module_me.activity.WorkActivity;
import com.example.module_me.presenter.MeFragmentPresenter;
import com.example.module_me.view_interface.IMeFragmentView;
import com.makeramen.roundedimageview.RoundedImageView;

import example.common_base.base.BaseFragment;
import example.common_base.util.ARouterUtil;
import example.common_base.widget.FunctionItemView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  我的Fragment
 */
@Route(path = ARouterUtil.Me_Fragment)
public class MeFragment extends BaseFragment<MeFragmentPresenter> implements IMeFragmentView, View.OnClickListener {
    RoundedImageView ivHead;
    TextView tvName;
    TextView tvLevel;
    TextView tvMusicCoin;
    //    TextView tvChat;
//    TextView tvFollow;
//    TextView tvFans;
//    TextView tvListen;
//    FunctionItemView fivPhoto;
    //    FunctionItemView fivCollect;
    FunctionItemView fivWallet;
    //    FunctionItemView fivInformation;
    ImageView ivBack;
    TextView tvTitle;
    private View viewInformation;
    //    TextView btnFollow;
//    private FunctionItemView fiEditInformation;

    @Override
    public void initView() {
        super.initView();
        viewInformation = view.findViewById(R.id.viewInformation);
//        fiEditInformation = view.findViewById(R.id.fiv_editInformation);
//        tvFollow = view.findViewById(R.id.tv_follow);
//        btnFollow = view.findViewById(R.id.btn_follow);
        tvTitle = view.findViewById(R.id.tv_title);
        ivBack = view.findViewById(R.id.iv_back);
//        fivInformation = view.findViewById(R.id.fiv_editInformation);
        fivWallet = view.findViewById(R.id.fiv_wallet);
//        fivCollect = view.findViewById(R.id.fiv_collect);
//        fivPhoto = view.findViewById(R.id.fiv_photo);

//        tvListen = view.findViewById(R.id.tv_listen);
        tvMusicCoin = view.findViewById(R.id.tvMusicCoin);
        tvLevel = view.findViewById(R.id.tvLevel);
        tvName = view.findViewById(R.id.tv_name);
        ivHead = view.findViewById(R.id.ivHead);

//        fiEditInformation.setOnClickListener(this);
        ivHead.setOnClickListener(this);
//        fivPhoto.setOnClickListener(this);
//        fivCollect.setOnClickListener(this);
        fivWallet.setOnClickListener(this);
        viewInformation.setOnClickListener(this);

        view.findViewById(R.id.fivNews).setOnClickListener(this);
        view.findViewById(R.id.tb).setBackgroundResource(R.drawable.shape_title_purple);
        view.findViewById(R.id.llPopularity).setOnClickListener(this);
        view.findViewById(R.id.llFollow).setOnClickListener(this);
        view.findViewById(R.id.llfans).setOnClickListener(this);
        view.findViewById(R.id.llListener).setOnClickListener(this);
        view.findViewById(R.id.fivSetting).setOnClickListener(this);
        view.findViewById(R.id.fivWork).setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
//        btnFollow.setVisibility(View.INVISIBLE);
//        tvChat.setVisibility(View.INVISIBLE);
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("我的");
        tvTitle.setTextColor(getResources().getColor(R.color.white));
//        fivInformation.setRightWihtTextTitleImageTitleStyle("个人资料", R.drawable.mine_icon_ziliao);
//        fivPhoto.setRightWihtTextTitleImageTitleStyle("相册", R.drawable.mine_icon_xiangce);
//        fivCollect.setRightWihtTextTitleImageTitleStyle("收藏", R.drawable.mine_icon_shoucang);
//        fivWallet.setRightWihtTextTitleImageTitleStyle("钱包", R.drawable.mine_icon_qianbao);
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public MeFragmentPresenter createPresenter() {
        return new MeFragmentPresenter(this);
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_me;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
//        if (viewId == R.id.tv_right) {
//            ARouter.getInstance().build(ARouterUtil.Setting_Activity).navigation();
//        } else
        if (viewId == R.id.viewInformation) {//点击信息
            startActivity(new Intent(getContext(), EditInformationActivity.class));
        }
//        else if (viewId == R.id.fiv_photo) {//点击相册
//            Intent photoItent = new Intent(getContext(), MyInformationActivity.class);
//            photoItent.putExtra("fromIntent", "photoIntent");
//            startActivity(photoItent);
//        }
//        else if (viewId == R.id.fiv_collect) {//点击收藏
//            startActivity(new Intent(getContext(), CollectionActivity.class));
//
//        }
        else if (viewId == R.id.fiv_wallet) {//点击钱包
            startActivity(new Intent(getContext(), WalletActivity.class));
        } else if (viewId == R.id.ivHead) {//点击头像
            startActivity(new Intent(getContext(), HeadimgSettingActivity.class));
        } else if (viewId == R.id.fivNews) {//点击消息列表
            startActivity(new Intent(getContext(), NewListActivity.class));
        } else if (viewId == R.id.llPopularity) {//点击人气
            startActivity(new Intent(getContext(), PopularityActivity.class));
        } else if (viewId == R.id.llFollow) {//点击关注
            startActivity(new Intent(getContext(), FollowActivity.class));
        } else if (viewId == R.id.llfans) {//点击粉丝
            startActivity(new Intent(getContext(), FansActivity.class));
        } else if (viewId == R.id.llListener) {//点击收听
            startActivity(new Intent(getContext(), ListenerActivity.class));
        } else if (viewId == R.id.fivSetting) {//点击系统设置
            startActivity(new Intent(getContext(), SettingActivity.class));
        } else if (viewId == R.id.fivWork) {
            startActivity(new Intent(getContext(), WorkActivity.class));
        }
    }
}
