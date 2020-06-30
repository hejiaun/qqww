package com.example.module_pk.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.activity.IncomeActivity;
import com.example.module_pk.activity.PkSingerActivity;
import com.example.module_pk.widget.ChatSettingPopupWindow;
import com.example.module_pk.widget.PlayerIncomeView;
import com.example.module_pk.widget.RatingHeadView;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 歌手模式的点评环节Fragment
 */
public class SingerGamePart2Fragment extends BaseFragment implements View.OnClickListener {
    private RatingHeadView rhv2;
    private RatingHeadView rvh1;
    private RatingHeadView rhv3;
    private PlayerIncomeView piv1;
    private PlayerIncomeView piv2;
    private View ivVoice;
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
        rvh1 = view.findViewById(R.id.rhv1);
        rhv2 = view.findViewById(R.id.rhv2);
        rhv3 = view.findViewById(R.id.rhv3);
        piv1 = view.findViewById(R.id.pivPlayer1);
        piv2 = view.findViewById(R.id.pivPlayer2);
        ivVoice = view.findViewById(R.id.ivVoice);
        tvFontSetting = view.findViewById(R.id.tvFontSetting);

        view.findViewById(R.id.tvStopRating).setVisibility(View.GONE);
        ivVoice.setVisibility(View.INVISIBLE);
        ivVoice.setClickable(false);
        tvFontSetting.setOnClickListener(this);
        view.findViewById(R.id.tvChat).setOnClickListener(this);
        view.findViewById(R.id.tvFontSetting).setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
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

        rhv2.showVoteIcon();
        rhv3.showVoteIcon();
        rvh1.showVoteIcon();


        rvh1.setVotable();
        rhv2.setVotable();
        rhv3.setVotable();
        rhv2.showCountdown();
        rhv2.startCountdown();

        rhv2.startSpeaking();
        rhv3.startSpeaking();
        rvh1.startSpeaking();


        piv1.showVoteBotton();

        piv2.hideVoteBotton();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvChat) {
//            getThisActivity().getPresenter().showBarrageDialog();
            getThisActivity().startActivity(new Intent(getThisActivity(), IncomeActivity.class));
        } else if (id == R.id.tvFontSetting) {
            showChatSetting();
        }
    }


    /**
     * 获取对应的Activity
     *
     * @return
     */
    public PkSingerActivity getThisActivity() {
        return (PkSingerActivity) getActivity();
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

}
