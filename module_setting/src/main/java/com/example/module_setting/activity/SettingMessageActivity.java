package com.example.module_setting.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_setting.R;
import com.example.module_setting.presenter.SettingMessageActivityPresenter;
import com.example.module_setting.view_interface.ISettingMessageActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  消息设置Activity
 */
public class SettingMessageActivity extends BaseActivity<SettingMessageActivityPresenter> implements
        ISettingMessageActivityView {
    TextView tvTitle;
    FunctionItemView fivReceiveMusicCircleMessage;
    FunctionItemView fivReceiveStranger;
    FunctionItemView fivChatFlowWindow;
    ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        fivReceiveMusicCircleMessage = findViewById(R.id.fivReceiveMusicCircleMessage);
        fivReceiveStranger = findViewById(R.id.fivReceiveStranger);
        fivChatFlowWindow = findViewById(R.id.fivChatFlowWindow);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        fivReceiveMusicCircleMessage.setOnClickListener(this);
        fivReceiveStranger.setOnClickListener(this);
        fivChatFlowWindow.setOnClickListener(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("消息设置");
        fivReceiveMusicCircleMessage.setTextTitleWithSwitchStyle("允许音乐圈消息推送");
        fivReceiveStranger.setTextTitleWithSwitchStyle("接收未关注人的消息");
        fivChatFlowWindow.setTextTitleWithSwitchStyle("聊天消息浮窗通知");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_setting_message;
    }


    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SettingMessageActivityPresenter createPresenter() {
        return new SettingMessageActivityPresenter(this);
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.fivReceiveMusicCircleMessage) {//是否接收音乐圈消息推送
            fivReceiveMusicCircleMessage.setClickItemCheck();

        } else if (viewId == R.id.fivReceiveStranger) {//是否接收陌生人消息
            fivReceiveStranger.setClickItemCheck();

        } else if (viewId == R.id.fivChatFlowWindow) {//显示浮窗消息
            fivChatFlowWindow.setClickItemCheck();

        }
    }
}
