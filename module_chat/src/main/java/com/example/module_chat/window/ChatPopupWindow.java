package com.example.module_chat.window;

import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.module_chat.R;

import example.common_base.util.WindowUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class ChatPopupWindow extends PopupWindow implements View.OnClickListener {

    private final View view;
    private final View anchorView;
    private final int[] ints;
    private TextView tvGroup;
    private TextView tvScan;
    private TextView tvAddFriend;


    public ChatPopupWindow(Activity activity, View achorView) {
        view = View.inflate(activity, R.layout.popup_chat, null);
        this.anchorView = achorView;
        ints = WindowUtil.getInstence().calculatePopWindowPos1(achorView, view);
        setContentView(view);
        setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.shape_card_round_white));
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view);
        setOutsideTouchable(true);
        setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(2);
        }
        initView();
    }

    private void initView() {
        tvGroup = view.findViewById(R.id.tvGroup);
        tvScan = view.findViewById(R.id.tvScan);
        tvAddFriend = view.findViewById(R.id.tvAddFriend);

        tvAddFriend.setOnClickListener(this);
        tvScan.setOnClickListener(this);
        tvGroup.setOnClickListener(this);
    }

    public void show() {
        showAtLocation(anchorView, Gravity.LEFT | Gravity.TOP, ints[0], ints[1]);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (clickListener == null) {
            return;
        }
        if (id == R.id.tvAddFriend) {
            clickListener.clickAddFriend();
        } else if (id == R.id.tvScan) {
            clickListener.clickScan();
        } else if (id == R.id.tvGroup) {
            clickListener.clickGroup();
        }
        dismiss();
    }

    private MyClickListener clickListener;

    public void setClickListener(MyClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface MyClickListener {
        void clickAddFriend();

        void clickScan();

        void clickGroup();
    }

}
