package com.example.module_chat.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.module_chat.R;

import java.util.List;

import example.common_base.entity.ChatMessageEntity;
import example.common_base.util.DensityUtils;
import example.common_base.util.TimeUtil;
import example.common_base.util.WindowUtil;

/**
 * 聊天消息适配器
 */
public class ChatMessageAdapter extends BaseMultiItemQuickAdapter<ChatMessageEntity, CustomBaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChatMessageAdapter(List<ChatMessageEntity> data) {
        super(data);
        //布局绑定
        //日期
        addItemType(ChatMessageEntity.DATE, R.layout.item_chat_date_center);
        //右布局
        addItemType(ChatMessageEntity.RIGHT, R.layout.item_chat_message_right);
        //左布局
        addItemType(ChatMessageEntity.LEFT, R.layout.item_chat_message_left);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, ChatMessageEntity item) {
        //设置点击事件
        switch (item.getItemType()) {//获取消息布局类型
            //时间日期布局
            case ChatMessageEntity.DATE:
                helper.setText(R.id.tvDate, TimeUtil.getInstence().secondTime2String((item.getSendDate()) / 1000));
                break;
            //左布局（接收消息）
            case ChatMessageEntity.LEFT:
                switch (item.getMessageContentType()) {//获取消息内容类型
                    //文本内容
                    case ChatMessageEntity.MESSAGE_TYPE_TEXT:
                        setTextMessage(helper, new int[]{R.id.ivMessageImageLeft}, R.id.tvMessagTextLeft, R.id.ivHeadLeft, item.getUserHeadURL(), item.getTextContent());
                        break;
                    //图片内容
                    case ChatMessageEntity.MESSAGE_TYPE_IMAGE:
                        setImageMessage(helper, new int[]{R.id.tvMessagTextLeft, R.id.ivMessageImageLeft}, R.id.ivMessageImageLeft, R.id.ivHeadLeft, item.getUserHeadURL(), item.getImageUrl());
                        break;
                    //视频内容
                    case ChatMessageEntity.MESSAGE_TYPE_VIDEO:
                        break;
                    //语音内容
                    case ChatMessageEntity.MESSAGE_TYPE_VOICE:
                        setViewVisibility(helper, new int[]{R.id.tvMessagTextRight, R.id.ivMessageImageRight}, R.id.llVoiceMessageRight);
                        break;
                }
                break;
            case ChatMessageEntity.RIGHT://右布局（发送消息）
                helper.setMessageState(R.id.lav, item.getMessageStatus());
                switch (item.getMessageContentType()) {//获取消息内容类型
                    //文本内容
                    case ChatMessageEntity.MESSAGE_TYPE_TEXT:
                        setTextMessage(helper, new int[]{R.id.tvDurationRight, R.id.ivMessageImageRight, R.id.llVoiceMessageRight}, R.id.tvMessagTextRight, R.id.ivHeadRight, item.getUserHeadURL(), item.getTextContent());
                        break;
                    //图片内容
                    case ChatMessageEntity.MESSAGE_TYPE_IMAGE:
                        setImageMessage(helper, new int[]{R.id.tvMessagTextRight, R.id.llVoiceMessageRight, R.id.tvDurationRight}, R.id.ivMessageImageRight, R.id.ivHeadRight, item.getUserHeadURL(), item.getImageUrl());
                        break;
                    //视频内容
                    case ChatMessageEntity.MESSAGE_TYPE_VIDEO:
                        break;
                    //语音内容
                    case ChatMessageEntity.MESSAGE_TYPE_VOICE:
                        setVoiceMessageRight(helper, item, new int[]{R.id.tvMessagTextRight, R.id.ivMessageImageRight}, R.id.llVoiceMessageRight);
                        break;
                }
                break;
        }
    }

    /**
     * 填充右布局的语音消息
     * *
     *
     * @param helper
     * @param item
     */
    public void setVoiceMessageRight(CustomBaseViewHolder helper, ChatMessageEntity item, int[] inVisibilityViewsId, int visibilityViewId) {
        setMessageBaseConfig(helper, inVisibilityViewsId, visibilityViewId, R.id.ivHeadRight, item.getUserHeadURL());

        if (item.getMessageStatus() == ChatMessageEntity.MESSAGE_STATUS_SENT) {//如果已经发送则显示录音时长
            helper.setText(R.id.tvDurationRight, item.getDuration() + "");
            helper.setVisible(R.id.tvDurationRight, true);
            helper.setVisible(R.id.lav, false);
        } else {
            helper.setVisible(R.id.tvDurationRight, false);
            helper.setVisible(R.id.lav, true);
        }

        //列表滚动时会失去item当前状态，因为列表滚动时的复用机制，所有要保持列表item状态
        if (item.isPlaying()) {//如果为正在播放状态
            helper.setVisible(R.id.ivSoundBgRight, false);
            helper.setVisible(R.id.ivAnimateRight, true);
            //播放动画
            ImageView ivAnimate = helper.getView(R.id.ivAnimateRight);
            AnimationDrawable animation = (AnimationDrawable) ivAnimate.getBackground();
            animation.start();
        } else {
            helper.setVisible(R.id.ivSoundBgRight, true);
            helper.setVisible(R.id.ivAnimateRight, false);
            //停止动画播放
            ImageView ivAnimate = helper.getView(R.id.ivAnimateRight);
            AnimationDrawable animation = (AnimationDrawable) ivAnimate.getBackground();
            animation.stop();
        }

        //设置语音控件长度
        setVoiceViewWith(helper, R.id.llVoiceMessageRight, item.getDuration());
    }

    /**
     * 设置控件可见度
     *
     * @param customBaseViewHolder
     * @param inVisibilityViewsId
     */
    public void setViewVisibility(CustomBaseViewHolder customBaseViewHolder, int[] inVisibilityViewsId, int visibilityViewId) {
        for (int i = 0; i < inVisibilityViewsId.length; i++) {
            customBaseViewHolder.setViewGone(inVisibilityViewsId[i]);
        }
        customBaseViewHolder.setVisible(visibilityViewId, true);
    }

    /**
     * 设置语音控件的长度
     *
     * @param helper
     * @param voiceMessageRightId
     * @param duration            语音长度
     */
    public void setVoiceViewWith(CustomBaseViewHolder helper, int voiceMessageRightId, int duration) {
        RelativeLayout relativeLayout = (RelativeLayout) helper.getView(voiceMessageRightId);
        //设置最大宽度
        int maxWidth = WindowUtil.getInstence().getWindowWidth(relativeLayout.getContext()) / 8 * 7 - 2 * DensityUtils.dp2px(relativeLayout.getContext(), 40);
        //设置最小宽度
        int minWidth = (int) (WindowUtil.getInstence().getWindowWidth(relativeLayout.getContext()) / 8 * 7 * 0.2f);
        //设置当前宽度
        int currentWidth = (int) (WindowUtil.getInstence().getWindowWidth(relativeLayout.getContext())) * duration / 20;
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (currentWidth < minWidth) {
            layoutParams.width = minWidth;
        } else if (currentWidth > maxWidth) {
            layoutParams.width = maxWidth;
        } else {
            layoutParams.width = currentWidth;
        }
        relativeLayout.setLayoutParams(layoutParams);
    }

    /**
     * 设置文本消息
     *
     * @param helper
     * @param inVisibilityViews 不可见view的id数组
     * @param visibilityView    可见view的id
     * @param content           文本内容
     */
    public void setTextMessage(CustomBaseViewHolder helper, int[] inVisibilityViews, int visibilityView, int headViewID, String userHeadURL, String content) {
        setMessageBaseConfig(helper, inVisibilityViews, visibilityView, headViewID, userHeadURL);
        helper.setText(visibilityView, content);
    }


    /**
     * 设置图片消息
     *
     * @param helper
     * @param inVisibilityViews 可见view的id数组
     * @param visibilityView    不可见view的id
     * @param imageUrl          图片地址
     */
    public void setImageMessage(CustomBaseViewHolder helper, int[] inVisibilityViews, int visibilityView, int headViewID, String userHeadURL, String imageUrl) {
        setMessageBaseConfig(helper, inVisibilityViews, visibilityView, headViewID, userHeadURL);
        helper.setImageViewResourceByGlide(visibilityView, imageUrl);
    }

    /**
     * 设置消息的基本配置
     *
     * @param helper
     * @param inVisibilityViews
     * @param visibilityView
     */
    public void setMessageBaseConfig(CustomBaseViewHolder helper, int[] inVisibilityViews, int visibilityView, int headViewID, String userHeadURL) {
        helper.addOnClickListener(visibilityView);
        setViewVisibility(helper, inVisibilityViews, visibilityView);
        //设置头像
        helper.setImageViewResourceByGlide(headViewID, userHeadURL);
    }
}

