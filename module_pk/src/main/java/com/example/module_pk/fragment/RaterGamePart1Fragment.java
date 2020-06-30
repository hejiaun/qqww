package com.example.module_pk.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.module_pk.R;
import com.example.module_pk.activity.PkRaterActivity;
import com.example.module_pk.adapter.BarrageAdapter;
import com.example.module_pk.entity.BarrageEntity;
import com.example.module_pk.presenter.RaterGamePart1FragmentPresenter;
import com.example.module_pk.view_interface.IRaterGamePart1FragmentView;
import com.example.module_pk.widget.BarrageDialog;
import com.example.module_pk.widget.ChatSettingPopupWindow;
import com.example.module_pk.widget.PresentDialog;
import com.example.module_pk.widget.RaterVoteButton;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.util.LrcUtil;
import example.common_base.util.RecyclerViewItemDecorationUtil;
import me.wcy.lrcview.LrcView;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:评委模式的唱歌环节Fragment
 */
public class RaterGamePart1Fragment extends BaseFragment<RaterGamePart1FragmentPresenter> implements
        IRaterGamePart1FragmentView,
        View.OnClickListener,
        RaterVoteButton.VoteButtonClickListener {
    private RecyclerView rcvBarrage;
    private RoundedImageView ivCurrentSingerHead;
    private LrcView lrcView;
    private TextView tvFontSetting;
    private PresentDialog presentDialog;
    private BarrageAdapter barrageAdapter;
    private BarrageDialog barrageDialog;
    private RaterVoteButton rb3;
    private RaterVoteButton rb2;
    private RaterVoteButton rb1;
    private ProgressBar pb;
    private ChatSettingPopupWindow chatSettingPopupWindow;
    private View maskView;

    @Override
    public RaterGamePart1FragmentPresenter createPresenter() {
        return new RaterGamePart1FragmentPresenter(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        rcvBarrage.setLayoutManager(new LinearLayoutManager(getActivity()));
        barrageAdapter = new BarrageAdapter(R.layout.item_pk_barrage, new ArrayList<BarrageEntity>());
        rcvBarrage.setAdapter(barrageAdapter);
        rcvBarrage.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, 16, 0));

        rb1.setMyClickListener(this);
        rb2.setMyClickListener(this);
        rb3.setMyClickListener(this);

        rb1.setVisibility(View.GONE);
    }

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        pb = view.findViewById(R.id.pb);
        tvFontSetting = view.findViewById(R.id.tvFontSetting);
        lrcView = view.findViewById(R.id.lrcView);
        ivCurrentSingerHead = view.findViewById(R.id.ivCurrentSingerHead);
        rcvBarrage = view.findViewById(R.id.rcv);
        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        rb3 = view.findViewById(R.id.rb3);
        maskView = view.findViewById(R.id.maskView);


        view.findViewById(R.id.tvChat).setOnClickListener(this);
        view.findViewById(R.id.tvGift).setOnClickListener(this);

        tvFontSetting.setOnClickListener(this);
        ivCurrentSingerHead.setOnClickListener(this);
    }

    /**
     * 配置布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_ratergame_part1;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvChat) {
            getThisActivity().getPresenter().showFragment(2, getThisActivity().getSupportFragmentManager().beginTransaction());
//            showBarrageDialog();
        } else if (id == R.id.ivCurrentSingerHead) {
            getThisActivity().showPersonalMessageDialog(R.drawable.yizhen, "陈绮贞");
        } else if (id == R.id.rb1) {
            getThisActivity().getPresenter().showFragment(2, getThisActivity().getSupportFragmentManager().beginTransaction());
        } else if (id == R.id.tvFontSetting) {
            showChatSetting();
        } else if (id == R.id.tvGift) {
            showPresentDialog();
        }
    }

    /**
     * 获取对应的Activity
     *
     * @return
     */
    public PkRaterActivity getThisActivity() {
        return (PkRaterActivity) getActivity();
    }

    /**
     * 显示礼物对话框
     */
    private void showPresentDialog() {
        if (presentDialog == null) {
            presentDialog = new PresentDialog(getActivity());
        }
        presentDialog.show();
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
                    rcvBarrage.scrollToPosition(barrageAdapter.getData().size() - 1);
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
    public void clickVoteButton(View view) {
        int id = view.getId();
        if (id == R.id.rb1) {
            vote();
        } else if (id == R.id.rb2) {
            vote();
        } else if (id == R.id.rb3) {
            vote();
        }
    }

    /**
     * 投票
     */
    public void vote() {
        maskView.setVisibility(View.GONE);
        rb1.setVisibility(View.GONE);
        rb2.setVisibility(View.GONE);
        rb3.setVisibility(View.GONE);
        lrcView.loadLrc(LrcUtil.getInstence().getLrcText("chengdu.lrc", getThisActivity()));
        lrcView.setVisibility(View.VISIBLE);
    }

    @Override
    public void clickHead(View view) {
        int id = view.getId();
        if (id == R.id.rb1) {
            getThisActivity().showPersonalMessageDialog(R.drawable.yizhen, "陈绮贞");
        } else if (id == R.id.rb2) {
            getThisActivity().showPersonalMessageDialog(R.drawable.yizhen, "陈绮贞");
        } else if (id == R.id.rb3) {
            getThisActivity().showPersonalMessageDialog(R.drawable.xueyou, "张学友");
        }
    }
}
