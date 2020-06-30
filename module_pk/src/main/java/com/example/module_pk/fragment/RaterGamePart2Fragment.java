package com.example.module_pk.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.activity.IncomeActivity;
import com.example.module_pk.activity.PkRaterActivity;
import com.example.module_pk.adapter.BarrageAdapter;
import com.example.module_pk.entity.BarrageEntity;
import com.example.module_pk.widget.BarrageDialog;
import com.example.module_pk.widget.ChatSettingPopupWindow;
import com.example.module_pk.widget.PlayerIncomeView;
import com.example.module_pk.widget.RatingHeadView;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.util.RecyclerViewItemDecorationUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:评委模式的点评环节Fragment
 */
public class RaterGamePart2Fragment extends BaseFragment implements View.OnClickListener, RatingHeadView.MyViewOnClickListener {
    private ImageView ivVoice;
    private RatingHeadView rhv2;
    private RatingHeadView rhv1;
    private RatingHeadView rhv3;
    private PlayerIncomeView piv1;
    private PlayerIncomeView piv2;
    private BarrageDialog barrageDialog;
    private RecyclerView rcvBarrage;
    private BarrageAdapter barrageAdapter;
    private TextView tvFontSetting;
    private ChatSettingPopupWindow chatSettingPopupWindow;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_ratergame_part2;
    }

    @Override
    public void initView() {
        super.initView();
        tvFontSetting = view.findViewById(R.id.tvFontSetting);
        ivVoice = view.findViewById(R.id.ivVoice);
        rhv1 = view.findViewById(R.id.rhv1);
        rhv2 = view.findViewById(R.id.rhv2);
        rhv3 = view.findViewById(R.id.rhv3);
        piv1 = view.findViewById(R.id.pivPlayer1);
        piv2 = view.findViewById(R.id.pivPlayer2);
        rcvBarrage = view.findViewById(R.id.rcvBarrage);

        ivVoice.setOnClickListener(this);
        rhv1.setMyViewOnClickListener(this);
        rhv2.setMyViewOnClickListener(this);
        rhv3.setMyViewOnClickListener(this);
        tvFontSetting.setOnClickListener(this);

        view.findViewById(R.id.tvChat).setOnClickListener(this);
        view.findViewById(R.id.tvStopRating).setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        barrageAdapter = new BarrageAdapter(R.layout.item_pk_barrage, new ArrayList<BarrageEntity>());
        //----------------------点击事件-----------------------//
        piv1.setHeadViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getThisActivity().showPersonalMessageDialog(R.drawable.yizhen, "陈绮贞");
            }
        });
        piv2.setHeadViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getThisActivity().showPersonalMessageDialog(R.drawable.xueyou, "张学友");
            }
        });
        rhv1.setMyViewOnClickListener(this);
        rhv2.setMyViewOnClickListener(this);
        rhv3.setMyViewOnClickListener(this);

        //----------------------设置不可点击投票-----------------------//
        rhv1.setDisVotable();
        rhv2.setDisVotable();
        rhv3.setDisVotable();
        //----------------------配置关联-----------------------//

        rcvBarrage.setAdapter(barrageAdapter);
        rcvBarrage.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, 16, 0));

        rhv1.startSpeaking();
        rhv1.showVoteIcon();
        rhv2.startSpeaking();
        rhv2.showVoteIcon();
        rhv3.startSpeaking();
        rhv3.showVoteIcon();

        piv1.showVoteBotton();

        piv2.hideVoteBotton();


    }

    public PkRaterActivity getThisActivity() {
        return (PkRaterActivity) getActivity();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvChat) {
            showBarrageDialog();
        } else if (id == R.id.ivVoice) {
            rhv1.release();
            rhv2.release();
            rhv3.release();
            getThisActivity().startActivity(new Intent(getThisActivity(), IncomeActivity.class));
        } else if (id == R.id.tvStopRating) {
            // TODO: 2019/3/20 结束点评
        } else if (id == R.id.tvFontSetting) {
            showChatSetting();
        } else if (id == R.id.llBarrageSetting) {//点击弹幕设置

        } else if (id == R.id.llChatSetting) {//点击聊天设置

        }
    }

    /**
     * 显示发送弹幕对话框
     */
    public void showBarrageDialog() {
        if (barrageDialog == null) {
            barrageDialog = new BarrageDialog(getThisActivity());
            barrageDialog.setBarrageDialogClickListener(new BarrageDialog.BarrageDialogClickListener() {
                @Override
                public void clickSend(String word) {
                    // TODO: 2019/3/22 发送弹幕
                    barrageAdapter.addData(new BarrageEntity(word));
                    rcvBarrage.scrollToPosition(barrageAdapter.getData().size()-1);
                }
            });
        }
        barrageDialog.show();
    }

    private void showChatSetting() {
        if (chatSettingPopupWindow == null) {
            chatSettingPopupWindow = new ChatSettingPopupWindow(getThisActivity(), tvFontSetting);
            chatSettingPopupWindow.setSettingClickListener(new ChatSettingPopupWindow.ClickListener() {
                @Override
                public void clickBarrage() {

                }

                @Override
                public void clickChat() {

                }
            });
        }
        chatSettingPopupWindow.show();
    }

    @Override
    public void clickLike(View view) {
        int id = view.getId();
        if (id == R.id.rhv1) {

        } else if (id == R.id.rhv2) {

        } else if (id == R.id.rhv2) {

        }
    }

    @Override
    public void clickDisLike(View view) {
        int id = view.getId();
        if (id == R.id.rhv1) {

        } else if (id == R.id.rhv2) {

        } else if (id == R.id.rhv2) {

        }
    }

    @Override
    public void clickHead(View view) {
        int id = view.getId();
        if (id == R.id.rhv1) {
            getThisActivity().showPersonalMessageDialog(R.drawable.jielun, "周杰伦");
        } else if (id == R.id.rhv2) {
            getThisActivity().showPersonalMessageDialog(R.drawable.wanfeng, "汪峰");
        } else if (id == R.id.rhv3) {
            getThisActivity().showPersonalMessageDialog(R.drawable.yangkun, "杨坤");
        }
    }

}
