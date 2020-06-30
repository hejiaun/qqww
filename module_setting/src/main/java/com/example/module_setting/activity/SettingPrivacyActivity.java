package com.example.module_setting.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_setting.R;
import com.example.module_setting.presenter.SettingPrivacyActivityPresenter;
import com.example.module_setting.view_interface.ISettingPrivacyActivityView;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  隐私设置Activity
 */
public class SettingPrivacyActivity extends BaseActivity<SettingPrivacyActivityPresenter> implements
        ISettingPrivacyActivityView {

    TextView tvTitle;
    FunctionItemView fivReceiveStrangerMessage;
    FunctionItemView fivAllowMyFollowerVisit;
    FunctionItemView fivAllowFollowMeVisit;
    FunctionItemView fivBlacklist;
    ImageView ivBack;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        fivReceiveStrangerMessage = findViewById(R.id.fivReceiveStrangerMessage);
        fivAllowMyFollowerVisit = findViewById(R.id.fivAllowMyFollowerVisit);
        fivAllowFollowMeVisit = findViewById(R.id.fivAllowFollowMeVisit);
        fivBlacklist = findViewById(R.id.fivBlacklist);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        fivAllowFollowMeVisit.setOnClickListener(this);
        fivAllowMyFollowerVisit.setOnClickListener(this);
        fivBlacklist.setOnClickListener(this);
        fivReceiveStrangerMessage.setOnClickListener(this);
    }

    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        tvTitle.setText("隐私设置");
        fivReceiveStrangerMessage.setTextTitleWithSwitchStyle("允许陌生人看我的音乐圈");
        fivAllowMyFollowerVisit.setTextTitleWithSwitchStyle("允许我关注的人看我的音乐圈");
        fivAllowFollowMeVisit.setTextTitleWithSwitchStyle("接收陌生人消息");
        fivBlacklist.setRightWithTextTitleStyle("黑名单管理");
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_setting_privacy;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public SettingPrivacyActivityPresenter createPresenter() {
        return new SettingPrivacyActivityPresenter(this);
    }

    /**
     * 点击事监听
     *
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.fivReceiveStrangerMessage) {//接收允许陌生人消息
            fivReceiveStrangerMessage.setClickItemCheck();
        } else if (viewId == R.id.fivBlacklist) {//黑名单管理
            startActivity(new Intent(this, BlackListActivity.class));
        } else if (viewId == R.id.fivAllowMyFollowerVisit) {//是否允许我关注的人查看我的音乐圈
            fivAllowMyFollowerVisit.setClickItemCheck();
        } else if (viewId == R.id.fivAllowFollowMeVisit) {//是否允许关注我的人查看我的音乐圈
            fivAllowFollowMeVisit.setClickItemCheck();
        }
    }
}
