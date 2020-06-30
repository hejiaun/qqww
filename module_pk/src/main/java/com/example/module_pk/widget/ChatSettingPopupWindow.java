package com.example.module_pk.widget;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.module_pk.R;

import example.common_base.util.DensityUtils;
import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class ChatSettingPopupWindow extends PopupWindow {

    private ClickListener clickListener;
    private View view;
    private int[] ints;
    private final View anchorView;
    private boolean isSelectBarrage = true;
    private boolean isSelectChat = true;
    private ImageView ivChat;
    private ImageView ivBarrage;


    public ChatSettingPopupWindow(Activity activity, View anchorView) {
        super(activity);
        view = View.inflate(activity, R.layout.popup_pk_font_setting, null);
        this.anchorView = anchorView;
        ints = WindowUtil.getInstence().calculatePopWindowPos(anchorView, view);
        setContentView(view);
        initView();
    }

    private void initView() {
        view.findViewById(R.id.llBarrageSetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clickBarrage();
                isSelectBarrage = !isSelectBarrage;
                if (isSelectBarrage) {
                    ivBarrage.setImageResource(R.drawable.chat_icon_chenggong);
                } else {
                    ivBarrage.setImageResource(R.drawable.sousuo_btn_xiayishou);
                }
            }
        });
        view.findViewById(R.id.llChatSetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clickChat();
                isSelectChat = !isSelectChat;
                if (isSelectChat) {
                    ivChat.setImageResource(R.drawable.chat_icon_chenggong);
                } else {
                    ivChat.setImageResource(R.drawable.sousuo_btn_xiayishou);
                }
            }
        });

        ivBarrage = view.findViewById(R.id.ivBarrage);
        ivChat = view.findViewById(R.id.ivChat);

        setBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.shape_round6dp_black));
        setOutsideTouchable(true);
        setFocusable(true);
    }

    public void show() {
        showAtLocation(anchorView, Gravity.LEFT | Gravity.TOP, ints[0], ints[1] - DensityUtils.dp2px(view.getContext(), 20));
    }

    /**
     * 设置点击事件
     *
     * @param clickListener
     */
    public void setSettingClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 弹窗的点击监听器
     */
    public interface ClickListener {

        /**
         * 点击弹幕
         */
        void clickBarrage();

        /**
         * 点击聊天
         */
        void clickChat();
    }


}
