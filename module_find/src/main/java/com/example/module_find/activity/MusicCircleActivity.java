package com.example.module_find.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_find.R;
import com.example.module_find.adapter.MusicCircleAdapter;
import com.example.module_find.entity.MusicCircleEntity;
import com.example.module_find.presenter.MusicCirclePresenter;
import com.example.module_find.view_interface.IMusicCircleView;
import com.jaeger.library.StatusBarUtil;
import com.mabeijianxi.jianxiexpression.ExpressionGridFragment;
import com.mabeijianxi.jianxiexpression.ExpressionShowFragment;
import com.mabeijianxi.jianxiexpression.widget.ExpressionEditText;

import java.util.ArrayList;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import example.common_base.base.BaseActivity;
import example.common_base.entity.UserEntity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.DensityUtils;
import example.common_base.util.RecyclerViewItemDecorationUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  音乐圈Activity
 */
public class MusicCircleActivity extends BaseActivity<MusicCirclePresenter> implements
        BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemChildLongClickListener,
        IMusicCircleView,
        ExpressionGridFragment.ExpressionClickListener,
        ExpressionGridFragment.ExpressionDeleteClickListener {
    RecyclerView rcv;
    MusicCircleAdapter adapter = null;
    private LinearLayout llCommentPanel;
    private ImageView ivBack;
    private KPSwitchPanelLinearLayout rootPanel;
    private ExpressionEditText etInput;
    private ImageView ivEmoji;
    private TextView tvSend;
    private int currentCommentPosition = -1;
    private PopupWindow popupWindow;
    private boolean isPoupuShowing = false;
    private ImageView ivPublishMusicCircle;
    private ImageView ivNews;

    @Override
    public void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        StatusBarUtil.setLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void initView() {
        super.initView();
        rcv = findViewById(R.id.rcv);
        llCommentPanel = findViewById(R.id.llCommentPanel);
        ivBack = findViewById(R.id.iv_back);
        ivEmoji = findViewById(R.id.ivEmoji);
        rootPanel = findViewById(R.id.rootPanel);
        etInput = findViewById(R.id.etInput);
        tvSend = findViewById(R.id.tvSend);
        ivPublishMusicCircle = findViewById(R.id.ivPublishMusicCircle);
        ivNews = findViewById(R.id.ivNews);

        ivBack.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        ivNews.setOnClickListener(this);
        ivPublishMusicCircle.setOnClickListener(this);

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.activity_musiccircle;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public MusicCirclePresenter createPresenter() {
        return new MusicCirclePresenter(this);
    }

    @Override
    public void initData() {
        super.initData();
        getPresenter().requestFirstEntryData();
    }

    /**
     * 关闭RecyclerView刷新动画
     */
    private void closeRecyclerViewReflashAnimate() {
        ((DefaultItemAnimator) rcv.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        adapter = new MusicCircleAdapter(R.layout.view_musiccircle, new ArrayList<MusicCircleEntity>());
        adapter.addHeaderView(View.inflate(getActivity(), R.layout.headview_photo, null));
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemChildLongClickListener(this);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(RecyclerViewItemDecorationUtil.getInstance().getItemDecoration(0, 0, DensityUtils.dp2px(this, 10), 0));
        rcv.scrollToPosition(0);
        closeRecyclerViewReflashAnimate();

        rcv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideCommentPanel();
                return false;
            }
        });

        KeyboardUtil.attach(this, rootPanel);
        KPSwitchConflictUtil.attach(rootPanel, ivEmoji, etInput,
                new KPSwitchConflictUtil.SwitchClickListener() {
                    @Override
                    public void onClickSwitch(boolean switchToPanel) {
                        if (switchToPanel) {
                            etInput.clearFocus();
                        } else {
                            etInput.requestFocus();
                        }
                    }
                });
        setEmojiconFragment();
    }

    /**
     * View层向Presenter提供MusicNearbyActivity
     *
     * @return MusicNearbyActivity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * View层向Presenter提供MusicCircleAdapter
     *
     * @return MusicCircleAdapter
     */
    @Override
    public MusicCircleAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tvSend) {
            comment();
        } else if (viewId == R.id.ivPublishMusicCircle) {//发布音乐圈

        } else if (viewId == R.id.ivNews) {//点击消息
            startActivity(new Intent(this, MusicCircleNewsActivity.class));
        }
    }

    /**
     * 清空输入框
     */
    private void clearInput() {
        etInput.setText("");
    }

    /**
     * 评论
     */
    private void comment() {
        String inputText = etInput.getText().toString().trim();
        if (!inputText.isEmpty()) {
            Toast.makeText(this, currentCommentPosition + "===", Toast.LENGTH_SHORT).show();
            hideCommentPanel();
            clearInput();
            adapter.getData().get(currentCommentPosition).getComment().add(new UserEntity());
            adapter.refreshNotifyItemChanged(currentCommentPosition);
        } else {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 隐藏评论面板
     */
    private void hideCommentPanel() {
        KPSwitchConflictUtil.hidePanelAndKeyboard(rootPanel);
        llCommentPanel.setVisibility(View.GONE);
    }

    /**
     * 显示评论面板
     */
    private void showCommentPanel() {
        llCommentPanel.setVisibility(View.VISIBLE);
        KPSwitchConflictUtil.showKeyboard(rootPanel, etInput);
    }

    /**
     * 点赞窗口
     */
    public void showPopupWindow(View view) {
        if (popupWindow == null) {
            View popupView = View.inflate(this,
                    R.layout.popupwindow_emtion, null);
            popupWindow = new PopupWindow(popupView,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.getContentView().findViewById(R.id.tvLikePop).setOnClickListener(this);
            popupWindow.getContentView().findViewById(R.id.tvCarePop).setOnClickListener(this);
            popupWindow.getContentView().findViewById(R.id.tvDislikePop).setOnClickListener(this);
        }
        if (!isPoupuShowing) {
            isPoupuShowing = true;
            popupWindow.showAsDropDown(view);
        } else {
            isPoupuShowing = false;
            popupWindow.dismiss();
        }
    }

    /**
     * 返回键事件
     */
    @Override
    public void onBackPressed() {
        if (llCommentPanel.getVisibility() == View.VISIBLE) {
            hideCommentPanel();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 设置表情面板
     *
     * @param
     */
    private void setEmojiconFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flEmojicons, ExpressionShowFragment.newInstance())
                .commit();
    }

    /**
     * 列表item控件点击事件监听
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int viewId = view.getId();
        if (viewId == R.id.tvComment) {//点击评论
            showCommentPanel();
            currentCommentPosition = position;
        } else if (viewId == R.id.tvMore) {
            // TODO: 2019/1/25 查看更多评论
            Toast.makeText(this, "'dd", Toast.LENGTH_SHORT).show();
        } else if (viewId == R.id.tvLike) {
            // TODO: 2019/1/25 点击赞
            Toast.makeText(this, "ddddd", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 列表item控件长按事件监听
     *
     * @param adapter
     * @param view
     * @param position
     * @return
     */
    @Override
    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        int viewId = view.getId();
        if (viewId == R.id.tvLike) {
            showPopupWindow(view);
        }
        return false;
    }

    /**
     * 表情点击事件
     *
     * @param str
     */
    @Override
    public void expressionClick(String str) {
        ExpressionShowFragment.input(etInput, str);
    }

    /**
     * 删除表情事件
     *
     * @param
     */
    @Override
    public void expressionDeleteClick(View v) {
        ExpressionShowFragment.delete(etInput);
    }
}
