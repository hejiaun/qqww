package com.example.module_chat.presenter;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lib_media.voice.VoiceMessagePlayer;
import com.example.module_chat.R;
import com.example.module_chat.model.ChatActivityModel;
import com.example.module_chat.view_interface.IChatActivityView;

import java.util.ArrayList;
import java.util.Collections;

import example.common_base.base.BasePresenter;
import example.common_base.entity.ChatMessageEntity;
import example.common_base.util.PermissionUtil;
import rx.functions.Action1;

/**
 * Created by Administrator on 2018/5/selector_white2fontgray.
 */
public class ChatActivityPresenter extends BasePresenter<IChatActivityView> {
    /**
     * Model层
     */
    private ChatActivityModel model;
    /**
     * 底部偏移量
     */
    private BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener;
    private ImageView ivSoundBg = null;
    private ImageView ivAnimate = null;
    private ChatMessageEntity chatVoiceMessageEntity;

    /**
     * 构造方法，初始化View层
     *
     * @param iChatActivityView View层接口
     */
    public ChatActivityPresenter(IChatActivityView iChatActivityView) {
        super(iChatActivityView);
        model = new ChatActivityModel();
    }


    /**
     * 获取模拟数据
     */
    public void requestChatRecord() {
        model.getChatRecord(new Action1<ArrayList<ChatMessageEntity>>() {
            @Override
            public void call(ArrayList<ChatMessageEntity> chatMessageEntities) {
                Collections.reverse(chatMessageEntities);
                getView().getAdapter().addData(chatMessageEntities);
            }
        });
    }

    /**
     * 获取更多聊天记录
     */
    public void requestMoreChatRecord() {
        model.getChatRecord(new Action1<ArrayList<ChatMessageEntity>>() {
            @Override
            public void call(ArrayList<ChatMessageEntity> chatMessageEntities) {
                Collections.reverse(chatMessageEntities);
                getView().getAdapter().addData(chatMessageEntities);
                getView().getAdapter().loadMoreComplete();
            }
        });

    }

    /**
     * 获取RecyclerView的Item的子控件点击监听器
     */
    public BaseQuickAdapter.OnItemChildClickListener getOnItemChildClickListener() {
        if (onItemChildClickListener == null) {
            onItemChildClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (((ChatMessageEntity) adapter.getItem(position)).getMessageContentType()) {
                        case ChatMessageEntity.MESSAGE_TYPE_IMAGE://图片消息
                            getView().showEnlargeImageDailog(position);
                            break;
                        case ChatMessageEntity.MESSAGE_TYPE_TEXT://文本消息
                            getView().showEnlargeTextDailog(position);
                            break;
                        case ChatMessageEntity.MESSAGE_TYPE_VOICE://语音消息
                            ChatMessageEntity chatMessageEntity = ((ChatMessageEntity) (adapter.getData().get(position)));
                            if (chatMessageEntity.getItemType() == ChatMessageEntity.LEFT) {
//                                    TextView textView=(TextView)view.findViewById(R.id)
//                                    playVoiceMessage(position);
                            } else {
                                ivAnimate = (ImageView) view.findViewById(R.id.ivAnimateRight);
                                ivSoundBg = (ImageView) view.findViewById(R.id.ivSoundBgRight);
                            }
                            playVoiceMessage(position);
                            break;
                    }
                }
            };
        }
        return onItemChildClickListener;
    }

    /**
     * 播放语音
     */
    private void playVoiceMessage(final int position) {
        chatVoiceMessageEntity = (ChatMessageEntity) getView().getAdapter().getData().get(position);
        if (chatVoiceMessageEntity.isPlaying()) return;
        VoiceMessagePlayer
                .getInstenece()
                .playVoiceMessage(position,
                        chatVoiceMessageEntity.getVoiceFilePath(),
                        new VoiceMessagePlayer.VoiceMessagePlayOnCompletionListener() {
                            @Override
                            public void onComplete() {//语音播放完成监听器   //停止动画播放
                                AnimationDrawable animation = (AnimationDrawable) ivAnimate.getBackground();
                                ivAnimate.setVisibility(View.INVISIBLE);
                                ivSoundBg.setVisibility(View.VISIBLE);
                                chatVoiceMessageEntity.setPlaying(false);
                                animation.stop();
                                //释放音乐播放器
                                VoiceMessagePlayer.getInstenece().release();
                                getView().getAdapter().notifyItemChanged(position);
                            }

                            @Override
                            public void onStart() {
                                //播放动画
                                ivSoundBg.setVisibility(View.INVISIBLE);
                                ivAnimate.setVisibility(View.VISIBLE);
                                AnimationDrawable animation = (AnimationDrawable) ivAnimate.getBackground();
                                animation.start();
                                //播放语音
                                chatVoiceMessageEntity.setPlaying(true);
                            }

                            @Override
                            public void onStop(int position) {
                                ((ChatMessageEntity) getView().getAdapter().getData().get(position)).setPlaying(false);
                                getView().getAdapter().notifyItemChanged(position);
                                VoiceMessagePlayer.getInstenece().release();
                            }
                        });
    }

    /**
     * 检查打开相机的权限
     */
    public void checkCameraPermission() {
        PermissionUtil.getInstance().chekPermissions(getView().getActivity(),
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, new PermissionUtil.IPermissionsResult() {
                    @Override
                    public void passPermissons() {
                    }

                    @Override
                    public void forbitPermissons() {
                    }
                });
    }

    /**
     * View缩放
     *
     * @param view        目标view
     * @param fromValue   初始值
     * @param targetValue 目标值
     */
    public void viewScale(View view, float fromValue, float targetValue) {
        //透明度变化
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", fromValue, targetValue);

        //x轴缩放大小
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", fromValue, targetValue);

        //y轴缩放大小
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", fromValue, targetValue);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200).play(alpha).with(scaleX).with(scaleY);
        animatorSet.start();
    }

    /**
     * 发送消息
     *
     * @param entity
     */
    public void sendMessage(ChatMessageEntity entity) {
        getView().getAdapter().addData(0, entity);
        //滚动到最后一条
        getView().getRecyclerView().scrollToPosition(0);
    }


}
