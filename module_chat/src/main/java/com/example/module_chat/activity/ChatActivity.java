package com.example.module_chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lib_media.voice.VoiceMessagePlayer;
import com.example.module_chat.R;
import com.example.module_chat.adapter.ChatMessageAdapter;
import com.example.module_chat.presenter.ChatActivityPresenter;
import com.example.module_chat.view_interface.IChatActivityView;
import com.jaeger.library.StatusBarUtil;
import com.mabeijianxi.jianxiexpression.ExpressionGridFragment;
import com.mabeijianxi.jianxiexpression.ExpressionShowFragment;
import com.mabeijianxi.jianxiexpression.widget.ExpressionEditText;

import java.util.ArrayList;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import example.common_base.base.BaseActivity;
import example.common_base.entity.ChatMessageEntity;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.DensityUtils;
import example.common_base.util.ImageUtil;
import example.common_base.util.PermissionUtil;
import example.common_base.util.RecyclerViewItemDecorationUtil;
import example.common_base.widget.FullScreenImageDialog;
import example.common_base.widget.FullScreenTextDialog;
import example.common_base.widget.SendVoiceButton;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  聊天Activity
 */
public class ChatActivity extends BaseActivity<ChatActivityPresenter> implements IChatActivityView,
        View.OnTouchListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        SendVoiceButton.Up2SendMessageListener,
        ExpressionGridFragment.ExpressionClickListener,
        ExpressionGridFragment.ExpressionDeleteClickListener {
    RecyclerView rcv;
    TextView btnSend;
    ImageView ivExpend;
    ExpressionEditText etInput;
    LinearLayout llTextInput;
    SendVoiceButton btnVoiceInput;
    ImageView ivEmoji;
    private ImageView ivMore;
    private ImageView ivBack;
    private KPSwitchPanelLinearLayout rootPanel;
    /**
     * 对话类型
     */
    private String sessionType;
    private ChatMessageAdapter adapter;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        ivEmoji = findViewById(R.id.ivEmoji);
        rootPanel = findViewById(R.id.rootPanel);
        btnVoiceInput = findViewById(R.id.btnVoiceInput);
        llTextInput = findViewById(R.id.llTextInput);
        ivExpend = findViewById(R.id.ivExpend);
        btnSend = findViewById(R.id.btnSend);
        rcv = findViewById(R.id.rcv);
//        ivEmoji = findViewById(R.id.ivEmoji);
        ivMore = findViewById(R.id.ivMore);
        ivBack = findViewById(R.id.ivBack);
        etInput = findViewById(R.id.etInput);
        rootPanel = findViewById(R.id.rootPanel);

        btnSend.setOnClickListener(this);
        ivMore.setOnClickListener(this);
        ivExpend.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivExpend.setOnClickListener(this);
//        etInput.setOnClickListener(this);
//        ivEmoji.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止语音播放
        VoiceMessagePlayer.getInstenece().release();
    }

    /**
     * onCreate在方法前的配置
     */
    @Override
    public void onCreateBefore() {
        super.onCreateBefore();
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();
        getPresenter().requestChatRecord();
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
        adapter = new ChatMessageAdapter(new ArrayList<ChatMessageEntity>());
        adapter.setOnItemChildClickListener(getPresenter().getOnItemChildClickListener());
        adapter.setUpFetchEnable(true);
        adapter.setEmptyView(new ProgressBar(this));
        adapter.setOnLoadMoreListener(this, rcv);
        sessionType = getIntent().getExtras().getString("sessionType");
        closeRecyclerViewReflashAnimate();
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(RecyclerViewItemDecorationUtil.
                getInstance().
                getItemDecoration(0, 0, DensityUtils.dp2px(this, 18), 0));
        rcv.setOnTouchListener(this);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etInput.getText()) && (btnSend.getVisibility() == View.VISIBLE)) {//如果输入框为空且发送按钮可见
                    //隐藏发送按钮
                    showHideSendButton(1.0f, 0f, View.GONE, View.VISIBLE);
                } else if ((!TextUtils.isEmpty(etInput.getText())) && (btnSend.getVisibility() == View.GONE)) {//如果输入框不为空且发送按钮不可见
                    //显示发送按钮
                    showHideSendButton(0f, 1.0f, View.VISIBLE, View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //下拉刷新监听
        btnVoiceInput.setUp2SendMessageListener(this);

        setEmojiconFragment();

        //----------------------表情面板高度适配设置-----------------------//
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
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_chat;
    }

    /**
     * View层向Presenter层提供ChatActivity
     *
     * @return ChatActivity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * View层向Presenter层提供消息列表适配器
     *
     * @return
     */
    @Override
    public ChatMessageAdapter getAdapter() {
        return adapter;
    }


    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public ChatActivityPresenter createPresenter() {
        return new ChatActivityPresenter(this);
    }

    /**
     * 显示  隐藏发送按钮
     *
     * @param fromValue              发送按钮属性动画初始状态值
     * @param targetValue            发送按钮属性动画目的状态值
     * @param sendButtonVisibility   发送按钮可见性
     * @param expendButtonVisibility 展开按钮可见性
     */
    public void showHideSendButton(float fromValue, float targetValue, int sendButtonVisibility, int expendButtonVisibility) {
        getPresenter().viewScale(btnSend, fromValue, targetValue);
        getPresenter().viewScale(ivExpend, targetValue, fromValue);
        btnSend.setVisibility(sendButtonVisibility);
        ivExpend.setVisibility(expendButtonVisibility);
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
     * View层提供RecyclerView
     *
     * @return
     */
    @Override
    public RecyclerView getRecyclerView() {
        return rcv;
    }

    /**
     * 权限请求回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * Activity界面跳转回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //图片选择器回调
        if (requestCode == ImageUtil.getInstence().getREQUEST_CODE() && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra("result");
        }
    }

    /**
     * 监听recyclerView触摸事件
     *
     * @param v
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        KPSwitchConflictUtil.hidePanelAndKeyboard(rootPanel);
        return false;
    }

    /**
     * 发送语音消息
     *
     * @param chatMessageEntity
     */
    @Override
    public void sendVoiceMessage(ChatMessageEntity chatMessageEntity) {
        getPresenter().sendMessage(chatMessageEntity);
    }

    /**
     * 请求更多数据
     */
    @Override
    public void onLoadMoreRequested() {
        getPresenter().requestMoreChatRecord();
    }

    /**
     * 表情面板item点击事件
     *
     * @param str
     */
    @Override
    public void expressionClick(String str) {
        ExpressionShowFragment.input(etInput, str);
    }

    /**
     * 表情面板删除表情事件
     *
     * @param v
     */
    @Override
    public void expressionDeleteClick(View v) {
        ExpressionShowFragment.delete(etInput);
    }

    /**
     * 点击图片，查看放大图片
     *
     * @param position 被点击的图片消息在RecyclerView中的位置
     */
    @Override
    public void showEnlargeImageDailog(int position) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(((ChatMessageEntity) adapter.getItem(position)).getImageUrl());
        FullScreenImageDialog fullScreenImageDialog = new FullScreenImageDialog(this, strings);
        fullScreenImageDialog.show();
    }

    /**
     * 点击文字，查看放大文字
     *
     * @param position 被点击的文字消息在RecyclerView中的位置
     */
    @Override
    public void showEnlargeTextDailog(int position) {
        String content = ((ChatMessageEntity) adapter.getItem(position)).getTextContent();
        FullScreenTextDialog fullScreenTextDialog = new FullScreenTextDialog(this, content);
        fullScreenTextDialog.show();
    }

    /**
     * 点击事件
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.ivMore) {
            if (sessionType.equals("群聊天")) {
                startActivity(new Intent(this, ChatGroupDetailsActivity.class));
            } else if (sessionType.equals("一对一聊天")) {
                startActivity(new Intent(this, ChatOneOnOneDetailsActivity.class));
            }

        } else if (viewId == R.id.ivBack) {//点击返回按钮
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.ivExpend) {//点击展开按钮
            Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();

        } else if (viewId == R.id.btnSend) {//点击发送按钮
            ChatMessageEntity chatTextMessageEntity = new ChatMessageEntity(ChatMessageEntity.MESSAGE_TYPE_TEXT, ChatMessageEntity.RIGHT, 5L, "group#", ConstantValuesUtil.IMAGE_URL_EXAMPLE7, System.currentTimeMillis());
            chatTextMessageEntity.setTextContent(etInput.getText().toString().trim());
            getPresenter().sendMessage(chatTextMessageEntity);
            etInput.setText("");
        }

    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        StatusBarUtil.setLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {
        if (rootPanel.getVisibility() == View.VISIBLE) {
            KPSwitchConflictUtil.hidePanelAndKeyboard(rootPanel);
        } else {
            super.onBackPressed();
        }
    }
}
