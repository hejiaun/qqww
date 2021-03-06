package com.example.module_pk.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.adapter.AudienceAdapter;
import com.example.module_pk.entity.AudienceEntiy;
import com.example.module_pk.presenter.PkRaterActivityPresenter;
import com.example.module_pk.view_interface.IPkRaterActivityView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import example.common_base.util.ActivityUtil;
import example.common_base.util.RecyclerViewItemDecorationUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  Pk评委模式Activity
 */
public class PkRaterActivity extends PkBaseActivity<PkRaterActivityPresenter> implements
        IPkRaterActivityView, BaseQuickAdapter.OnItemClickListener {
    LinearLayout llMask;
    LinearLayout llAudience;
    private RoundedImageView ivRater1;
    private RoundedImageView ivRater2;
    private RoundedImageView ivRater3;
    private RoundedImageView ivPlayer1;
    private RoundedImageView ivPlayer2;
    private RoundedImageView ivAudience4;
    private RoundedImageView ivAudience3;
    private RoundedImageView ivAudience2;
    private RoundedImageView ivAudience1;
    private AudienceAdapter adapter;
    private RecyclerView rcvAudience;
    private View outsizeView;

    @Override
    public void initView() {
        super.initView();
        rcvAudience = findViewById(R.id.rcvAudience);
        ivRater1 = findViewById(R.id.rhv1);
        ivRater2 = findViewById(R.id.rhv2);
        ivRater3 = findViewById(R.id.rhv3);
        ivPlayer1 = findViewById(R.id.ivPlayer1);
        ivPlayer2 = findViewById(R.id.ivPlayer2);
        ivAudience1 = findViewById(R.id.ivAudience1);
        ivAudience2 = findViewById(R.id.ivAudience2);
        ivAudience3 = findViewById(R.id.ivAudience3);
        ivAudience4 = findViewById(R.id.ivAudience4);
        outsizeView = findViewById(R.id.outsizeView);

        ivPlayer1.setOnClickListener(this);
        ivPlayer2.setOnClickListener(this);
        ivRater1.setOnClickListener(this);
        ivRater2.setOnClickListener(this);
        ivRater3.setOnClickListener(this);
        ivAudience4.setOnClickListener(this);
        ivAudience3.setOnClickListener(this);
        ivAudience2.setOnClickListener(this);
        outsizeView.setOnClickListener(this);
        ivAudience1.setOnClickListener(this);

        llMask = findViewById(R.id.ll_mask);
        llAudience = findViewById(R.id.ll_audience);

        findViewById(R.id.btn_exit).setOnClickListener(this);
        findViewById(R.id.iv_showAudience).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().saveAllFragment(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().restoreFragment(savedInstanceState);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        getPresenter().showFragment(1, getSupportFragmentManager().beginTransaction());
        adapter = new AudienceAdapter(R.layout.item_audience, new ArrayList<AudienceEntiy>());

        adapter.setOnItemClickListener(this);
        rcvAudience.setAdapter(adapter);
        rcvAudience.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(20, 0, 20, 0));

        //----------------------先打开倒数对话框-----------------------//
        showGameCountDownDialog();
    }

    @Override
    public void initData() {
        super.initData();
        getPresenter().getAdudienceData();
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_pk;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public PkRaterActivityPresenter createPresenter() {
        return new PkRaterActivityPresenter(this);
    }

    /**
     * 打开观众列表
     */
    public void openAudienceList() {
        outsizeView.setClickable(true);
        //播放动画
        llAudience.startAnimation(AnimationUtils.loadAnimation(this, R.anim.chat_view_show));
        llMask.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mask_show));
        //设置聊天框可见性
        llMask.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭观众列表
     */
    public void closeAudienceList() {
        outsizeView.setClickable(false);
        llMask.setVisibility(View.GONE);
        //播放动画
        llAudience.startAnimation(AnimationUtils.loadAnimation(this, R.anim.chat_view_hide));
        llMask.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mask_hide));
        //设置聊天框可见性
    }

    /**
     * 点击返回键
     */
    @Override
    public void onBackPressed() {
        if (llMask.getVisibility() == View.VISIBLE) {
            closeAudienceList();
            return;
        }
        super.onBackPressed();
    }

    /**
     * View层向Presenter层提供PkRaterActivity
     *
     * @return PkRaterActivity
     */
    @Override
    public PkRaterActivity getActivity() {
        return this;
    }

    @Override
    public AudienceAdapter getAdapter() {
        return adapter;
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_exit) {//关闭观众列表
            ActivityUtil
                    .getInstance()
                    .finishActivity(this)
                    .finishActivity(PkRaterMatchActivity.class)
                    .finishActivity(PkModeActivity.class);
        } else if (viewId == R.id.iv_showAudience) {
            openAudienceList();
        } else if (viewId == R.id.outsizeView) {
            closeAudienceList();
        } else if (viewId == R.id.ivPlayer1) {
            showPersonalMessageDialog(R.drawable.yizhen, "陈绮贞");
        } else if (viewId == R.id.ivPlayer2) {
            showPersonalMessageDialog(R.drawable.xueyou, "张学友");
        } else if (viewId == R.id.rhv1) {
            showPersonalMessageDialog(R.drawable.jielun, "周杰伦");
        } else if (viewId == R.id.rhv2) {
            showPersonalMessageDialog(R.drawable.wanfeng, "汪峰");
        } else if (viewId == R.id.rhv3) {
            showPersonalMessageDialog(R.drawable.yangkun, "杨坤");
        } else if (viewId == R.id.ivAudience1) {
            showPersonalMessageDialog(R.drawable.audience1, "Smile cry");
        } else if (viewId == R.id.ivAudience2) {
            showPersonalMessageDialog(R.drawable.audience2, "额额");
        } else if (viewId == R.id.ivAudience3) {
            showPersonalMessageDialog(R.drawable.audience3, "二v哦");
        } else if (viewId == R.id.ivAudience4) {
            showPersonalMessageDialog(R.drawable.audience4, "EMIRO");
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AudienceEntiy audienceEntiy = (AudienceEntiy) adapter.getData().get(position);
        showPersonalMessageDialog(audienceEntiy.getHeadResource(), audienceEntiy.getName());
    }

}
